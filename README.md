# 编译原理实验:   构建完整的MIDL To C++的源到源的编译器



## 实验环境

idea下配置的maven环境,具体参数如下

| Dependency | version |
| ---------- | :------ |
| junit      | 4.11    |
| antlr4     | 4.10.1  |
| ST4        | 4.0.8   |
| java       | 11      |

## 实验内容

根据实验一和实验二的任务要求,我将本实验分为以下步骤:

1. 使用antlr进行**词法分析和语法分析**

   ​	a. g4文件设计

   ​    b. antlr生成词法语法树分析程序

2. 自定义**抽象语法树AST**

   ​	a. 自定义抽象语法树,对每一条语法规则设计其抽象语法树

   ​	b. 抽象语法树的代码定义TreeNode

   ​	c. 利用antlr的visitor访问者模式建立抽象语法树

3. 测试1: 测试g4文法设计和AST输出与设计相符合

4. **语义分析**:实现能够对要求的3条语义错误进行检查和报错。报错提示要给出错误位置，以及错误类型。

5. 测试2:测试语法分析是否能够处理要求的语义错误.报错提示是否包含错误位置和错误类型.

6. 使用string template模板进行**代码生成.**

7. 测试3:编写代码生成用例,测试是否符合抽象语法节点的生成规则.

本报告将在实验流程板块阐述上述步骤中的非测试步骤,在测试说明板块介绍三个测试的代码用例和设计思路.



## 实验流程

### 1.使用antlr进行词法分析和语法分析

g4文件定义详见MIDL.g4文件.

grammer的名字必须和g4文件名字相同,一个g4文件必须有一个header和至少一个词法或语法规则.Token tules大写字母开头, Parser rules小写字母开头.

然后使用命令`antlr MIDL.g4 -visitor` 生成词法分析和语法分析相关的类,并且因为后面构建抽象语法树采用的是visitor访问者模式,所以加上-visitor参数.

### 2.自定义抽象语法树AST

#### a. 自定义抽象语法树,对每一条语法规则设计其抽象语法树.详细设计详见附件抽象语法树设计.doc

#### b. 抽象语法树的代码定义TreeNode

```python
class Member {
    String type;

    LinkedHashMap<String, String> declarators;//<name,assign>

    public Member(String type) {
        this.type = type;
        this.declarators = new LinkedHashMap<>();
    }
}


class TreeNode {
    //spec
    public ArrayList<TreeNode> specification = new ArrayList<>();

    enum NODETYPE {
        MODULE,
        STRUCT,
        SPEC
    }

    public NODETYPE nodetype;
    //module
    public String module_id;
    public ArrayList<TreeNode> definitions = new ArrayList<>();

    //struct
    public String struct_id;
    public ArrayList<Member> members = new ArrayList<>();//can be null
```

叶节点利用自定义的枚举类型nodetype来确定叶节点的类型. 叶节点的类型分为3种:首先是spec类型,对应文法规则specification->(definition)+ ||  definition: type_decl ';' | module ';';definition的子树结构是module或者struct结构,这两个结构也是叶节点能够表示的另外两种结构,所以声明一个treeNode数组来存放第一层下的module和struct结构.

然后是module类型,因为module: 'module' ID '{' definition+ '}'; module的抽象语法树首先保存module关键字,然后保存module对应的ID和definition对应的子树,在代码实现中用nodetype置为MODULE表示该树为module结构,利用module_id存放该module的ID,再用一个treeNode数组来表示module模块下存放的module和struct结构(definition结构);



```
type_decl: struct_type | 'struct' ID;

struct_type: 'struct' ID '{'   member_list '}';

member_list: (type_spec declarators ';')*;
```

最后是struct类型.由上述文法可以看出struct类型需要记录的是:1.STRUCT关键字  2. Struct的ID 3.其后member_list中存储的声明语句,member_list也可以为空.利用nodetype置为STRUCT表示该树为struct结构. struct_id记录该struct结构对应的id,然后用member类型的数组存储struct结构下的声明语句.

因为一个type_spec可能对应多个declarator,并且declarator中无论是simple_declarator或者是array_declarator都可能会出现赋值,即出现('=' or_expr)?或者('=' exp_list )?. 所以member类中设计了一个链式哈希表,以加入哈希表先后顺序记录declarator的ID和其赋值式assign. 虽然在构建抽象语法树和进行语义分析的时候维护了符号表存储dtype和运算公式相关内容,但为了存储方便,这里的name和assign都采用String的形式存储.

#### c. 利用antlr的visitor访问者模式建立抽象语法树

具体代码详见ASTLoader.java

### 3.语义分析

本次实验的语义分析的主要内容有3条,以下是实现思路:

1.命名冲突.

在构建语法树的过程当中,维护了以下两个数组,分别存储同一个struct类型下的变量名和所有的struct名字.

```python
    ArrayList<String> valuables_names = new ArrayList<>(); //1.1 1.2
    ArrayList<String> structs_names = new ArrayList<>();//1.3
```

当struct类型的treeNode构建完成之后将会清空valuables_name数组,以保证valuables_name数组下永远存储的是当前struct结构中的变量.



2.未定义即使用.

自定义的type类型主要来源于文法规则`scoped_name: ('::')? ID ('::' ID )*;` 因为1中维护了一个structs_names存储所有struct名字,这里的存储形式为module_id::struct_id,即struct对应id及其所属命名空间都会被存储与struct_names当中.

所以可以在构建ast时,当一个结构体名字作为类型出现时,检查该名字及其所属命名空间是否存在于struct_names当中,如果不存在则报错2.1. 如果该struct的名字存在但其所属的命名空间不对,则报错2.2.



3.字面类型检查



```
simple_declarator : ID ('=' or_expr)?;

array_declarator : ID '[' or_expr ']' ('=' exp_list )?;
```

字面类型检查主要在访问以上两个文法规则时检查. 因为在访问上述文法规则之前,该declarator的dtype已被压入符号栈中,所以可以通过访问符号栈栈顶得到该declarator的声明类型.然后根据要求判断赋值公式或语句是否符合该类型要求.

值得一提的是, 因为exp是以String类型存储的,例如long double b2 = -4 | 2 ^ 2 & 2 >> 2 + 2 * 2; 检查时得到三个元素:dtype为long double, name为b2, exp为"-4 | 2 ^ 2 & 2 >> 2 + 2 * 2"的字符串. 因为在判断类型和赋值是否冲突时,判断的是计算式子的最终结果,所以在这里写了一个从表达式字符串得到其计算结果数值的函数.该例子最终判断的也是表达式结果"-2" 是否符合 long double.





### 4.代码生成

这里使用的是stringTemplate生成.模板设计文件为hxxTemplate.stg. 代码生成文件为HxxGenerator.java. 因为在代码生成时已经构建完毕抽象语法树和完成语义分析检查等操作,所以这里直接采用迭代语法树来生成传入stringTemplate模板功能的参数.

因为在构建语法树的时候,如果是struct类型的ID作为类型去声明一个对象,合法的情况下存储的ID就是带有其命名空间的. 所以在代码生成时对于struct名称不需要做其他的操作.

这里最复杂的问题是struct_nest.idl中的嵌套问题,即在Struct B中声明一个Struct A的对象, 在初始化的时候也需要对该对象中所有成员变量赋值. 为了解决这个问题,我设计了一个`findStructByname(TreeNode root, String name)`函数,该函数可以通过一个结构体名称,返回其对应的treeNode节点,这样就可以在处理struct B时得到struct A对应的treeNode节点,并对其存储的声明语句进行访问.







## 测试说明

### 1.g4文法设计和AST树设计

###### g4文法

测试代码详见`test.java`文件.测试用例test.txt文件

经过测试和debug,修改前期g4文法出现的错误,最终测试用例均无报错,且visit函数覆盖率为100%.

![image-20220502205458090](/home/lxy/.config/Typora/typora-user-images/image-20220502205458090.png)

然后,下图是部分测试用例的完整分析树.该分析树格式化输出与antlr生成分析树相同. 证明g4文法没有出现错误.

![ast ](/home/lxy/schoolstuff/实验1-MIDL/代码附件/ast .jpg)



###### 格式化AST输出和设计是否相同

将测试样例分为以下几个类别进行分析,每个类别都有多个不同变量类型的测试用例.

1.module和struct结构体 + 变量声明

测试样例

```
module mm{
struct test2{
int m1;
};};
```

测试输出

```
module mm
	struct test2
		member
			type_spec
				int 
			declarators
				m1
```

推导:module->module ID {difinition}->module ID {type_decl}->module ID { struct_type}->module ID {struct ID {member_list}}->module ID {struct ID {member}}->module ID {struct ID {member{type_spec, declarators}}}...   type_spec->int  declarators->m1

设计时省略了{}等符号,也省略了definition, type_decl等父节点,只留下了member,type_spec,declarators等关键字分别表示一行声明,变量类型和变量名称.所以输出与设计相符合.

2.声明变量时赋值

在结构体中输入`string s1="hello world!";`

测试输出(此处省略前面结构体的输出,只留下声明赋值的ast输出)

```
		member
			type_spec
				string 
			declarators
				s1
					assign
					"hello world!"
```

分析

![image-20220506215729855](/home/lxy/.config/Typora/typora-user-images/image-20220506215729855.png)

declarators->declarator->simple_declarator->ID = or_expr 按照AST设计这里应该为declaratos{s1{assign,"hello world"}}.所以输出与设计相符合

3.数组类型变量及其赋值

输出

```
		member
			type_spec
				int 
			declarators
				array1 [ 4 ] 
					assign
					[ 1, 2, 3] 
```

![image-20220506220735484](/home/lxy/.config/Typora/typora-user-images/image-20220506220735484.png)

declarators->declarator->arrary_declarator->ID '[' or_expr ']' ('=' exp_list ) ast输出应为ID [or_expr]{assign,exp_list}.输出与设计相符合

4.结构体作为变量类型

输入

```
module mm{
struct test2{
struct test3{
int8 test;
} m1;
...
}
```

输出

```
module mm
	struct test2
		member
			type_spec
				struct test3
				member
					type_spec
						int8 
					declarators
						test
			
			declarators
				m1
	...
```

这里struct test3作为一个type_spec表示m1的变量类型.输出和设计相符

5.赋值时存在计算符号

输出

```
		member
			type_spec
				long double 
			declarators
				b2
					assign
					-4|2^2&2>>2+2*2
```

因为在处理or_expr : xor_expr ('|' xor_expr )*;xor_expr : and_expr ('^' and_expr )*;and_expr : shift_expr ('&' shift_expr )*;等文法规则时没有保留父节点的关键字,所以会一直递推下去只留下计算过程中的计算符号.输出和设计相符合.





2.语义分析测试用例

1.1 同一个struct空间下，不能有同名变量。

输入:

```
struct test{
   float a1;
   int8 a1=5;
};
```

输出

```
line 3:8 repeated variable 'a1'    semantic error 1.1: In the same struct space, there cannot be variables with the same name.  
Exception in thread "main" java.lang.RuntimeException: Semantic Error 1.1
```

1.2 同一个module下，不同的struct可以有同名变量。

输入:

```
module m1{
struct test{
   float a1;
   int8 a2=5;
};
struct test2{
    float a1;
};
};
```

无报错

1.3 同一个module下，不能出现同名的struct。

输入

```
module m1{
struct test{
   float a1;
   int8 a2=5;
};
struct test;
};
```

输出:

```
Warning: Nashorn engine is planned to be removed from a future JDK release
line 6:0 repeated struct name 'm1::test'    semantic error 1.3: In the same module space, there cannot be structs with the same name.  
```

2.1 B结构应该先定义才能引用类型

输入

```
struct A{
short a;
B b;
};
```

输出:

```
line 3:0 undeclarated name 'B'    semantic error 2.1: The struct must be declared before it can be used.  
```

2.2 虽然B结构定义了，但是命名空间的引用不对。应该是 space1::B

输入

```
module space1{
struct B{
    float a1;
};
};
module space2{
struct A{
short a;
B b;
};
};
```

输出:

```
line 9:0 undeclarated name 'B'    semantic error 2.2: The structure is defined, but the namespace reference is incorrect. 
```

3.1a是整型变量，字面量却是字符类型

```
struct A{
short a=’a’;
};
```

输出

```
line 2:6 wrong assign 'a'    semantic error 3.1: It is an integer variable, but the literal is a wrong type  
Exception in thread "main" java.lang.RuntimeException: Semantic Error 3.1
```



3.2short为有符号短整型

```
struct A{
short a=100000;
};
```

输出:

```
line 2:6 wrong assign 'a'    semantic error 3.2: short is a signed short integer, the input number is out of bounds 
Exception in thread "main" java.lang.RuntimeException: Semantic Error 3.2
```

3.3 a是整型变量，字面量却是浮点类型

```
struct A{
short a=15.24;
};
```

输出

```
line 2:6 wrong assign 'a'    semantic error 3.3: It is an integer variable, but the literal is a floating-point type 
Exception in thread "main" java.lang.RuntimeException: Semantic Error 3.3
```

3.4a是整型数组，数组字面量里必须保证数据类型的统一

```
struct A{
short a[4]=[10,12,45.34,'a'];
};
```

输出:

```
line 2:6 wrong assign 'a'    semantic error 3.4.2: The data entered in the array is not an integer variable.  
Exception in thread "main" java.lang.RuntimeException: Semantic Error 3.4.2
```



3.代码生成测试用例

代码生成测试用例可以直接使用任务二中的case1-5进行验证,这里也自定义了代码用例详见文件代码生成用例.txt

