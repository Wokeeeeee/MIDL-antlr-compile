import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

class ModuleNode {
    String ID;
    ArrayList<DefinitionNode> definitionNodes = new ArrayList<>();

    public ModuleNode(String ID) {
        this.ID = ID;
    }
}

class DefinitionNode {
    StructType structType;
    String struct_ID;
    Module module;

    public DefinitionNode(Module module) {
        this.module = module;
    }

    public DefinitionNode(String struct_ID) {
        this.struct_ID = struct_ID;
    }

    public DefinitionNode(StructType structType) {
        this.structType = structType;
    }
}

enum Base_type_spec {CHAR, STRING, BOOLEAN, FLOAT, DOUBLE, LONGDOUBLE, SHORT, INT16, LONG, INT32, LONGLONG, INT64, INT8, USHORT, UINT16, ULONG, UINT32, ULONGLONG, UINT64, UINT8}

class ScopeName {
    boolean isFirstExist;
    ArrayList<String> id_list;

    public ScopeName(boolean isFirstExist, ArrayList<String> id_list) {
        this.isFirstExist = isFirstExist;
        this.id_list = id_list;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        if (isFirstExist) stringBuilder.append("::");
        for (int i = 0; i < id_list.size(); i++) {
            stringBuilder.append(id_list.get(i));
            if (i != id_list.size() - 1) stringBuilder.append("::");
        }
        return stringBuilder.toString();
    }
}

class StructType {
    String ID;
    ArrayList<Member> members = new ArrayList<Member>();
}

class Member {
    Base_type_spec base_type_spec;
    ScopeName scopeName;
    StructType struct;


    DecNode decNode;
}

class DecNode {
    String name;

    SimpleExpNode array;

    enum Dec_type {Array, Simple, None}

    Dec_type type;

    ExpListNode expListNode;
    SimpleExpNode simpleExpNode;

    public DecNode(String name, SimpleExpNode array, Dec_type type, ExpListNode expListNode, SimpleExpNode simpleExpNode) {
        this.name = name;
        this.array = array;
        this.type = type;
        this.expListNode = expListNode;
        this.simpleExpNode = simpleExpNode;
    }
}

class ExpListNode {
    ArrayList<SimpleExpNode> simpleExpNodes = new ArrayList<>();
}

class SimpleExpNode {
    enum Exp_Type {
        OR("|"),
        XOR("^"),
        AND("&"),
        SHIFTR("<<"),
        SHIFTL(">>"),
        ADD("+"),
        MULTMUL("*"),
        MULTDIV("/"),
        MULTREM("%"),
        ;

        private String name;

        Exp_Type(String name) {
        }

        @Override
        public String toString() {
            return this.name;
        }
    }


    public Exp_Type exp_type;
    public ArrayList<UnaryExpr> exprs = new ArrayList<>();

    public SimpleExpNode(Exp_Type exp_type) {
        this.exp_type = exp_type;
    }
}

class UnaryExpr {
    enum SIGN {
        MINUS("-"),
        PLUS("+"),
        OPP("~"),
        EMPTY("");
        private String name;

        SIGN(String name) {
            this.name = name;
        }

        @Override
        public String toString() {
            return this.name;
        }
    }


    SIGN sign;
    String literal;

    public UnaryExpr(SIGN sign, String literal) {
        this.sign = sign;
        this.literal = literal;
    }
}

class Literal {
    enum DATA_TYPE {INTEGER, FLOATING_PT, CHAR, STRING, BOOLEAN}

    private DATA_TYPE data_type;
    private String value;

    public Literal(DATA_TYPE data_type, String value) {
        this.data_type = data_type;
        this.value = value;
    }
}

class TreeNode {
    public ArrayList<TreeNode> children = new ArrayList<>();

    enum NODETYPE {
        DEFINITION,
        MODULE,
        STRUCT,
        DECLARATION,
        TYPESPEC
    }

    public NODETYPE nodetype;
    //module
    public String module_id;
    public ArrayList<DefinitionNode> definitionNodes = new ArrayList<>();

    //struct
    public String struct_id;
    public ArrayList<Member> members = new ArrayList<>();//can be null


    //declaration
    public String dec_type;
//    public ArrayList<Declaration> declarations = new ArrayList<>();

}

class ASTLoader extends MIDLBaseVisitor<TreeNode> {
    public static void main(String[] args) throws IOException {
        InputStream is = new FileInputStream("test.txt");
        ANTLRInputStream input = new ANTLRInputStream(is);
        MIDLLexer lexer = new MIDLLexer(input);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        MIDLParser parser = new MIDLParser(tokens);
        ParseTree tree = parser.specification();
        ASTLoader astLoader = new ASTLoader();
        astLoader.visit(tree);
        for (var i : astLoader.symbol_stack) System.out.println(i);
//        for (var i : astLoader.exp_stack) System.out.print(i);
    }

    TreeNode root = new TreeNode();
    Stack<String> symbol_stack = new Stack<>();
    Queue<String> exp_queue = new LinkedList<>();//start from or_expr

    @Override
    public TreeNode visitSpecification(MIDLParser.SpecificationContext ctx) {
        return super.visitSpecification(ctx);
    }

    @Override
    public TreeNode visitDefinition(MIDLParser.DefinitionContext ctx) {
        return super.visitDefinition(ctx);
    }

    @Override
    public TreeNode visitModule(MIDLParser.ModuleContext ctx) {
        return super.visitModule(ctx);
    }

    @Override
    public TreeNode visitType_decl(MIDLParser.Type_declContext ctx) {
        return super.visitType_decl(ctx);
    }

    @Override
    public TreeNode visitStruct_type(MIDLParser.Struct_typeContext ctx) {
        TreeNode treeNode = new TreeNode();
        treeNode.nodetype = TreeNode.NODETYPE.STRUCT;
        visit(ctx.getChild(3));
//        treeNode.members = visit(ctx.getChild(3)).members;
        return treeNode;
    }

    /**
     * member_list: (type_spec declarators ';')*;
     *
     * @param ctx
     * @return
     */
    @Override
    public TreeNode visitMember_list(MIDLParser.Member_listContext ctx) {
        TreeNode treeNode = new TreeNode();
        for (int i = 0; i < ctx.getChildCount() / 3; i++) {
            visit(ctx.getChild(3 * i));//type_spec
            visit(ctx.getChild(3 * i + 1));//declaration
        }
        return null;
    }

    /**
     * type_spec: scoped_name | base_type_spec | struct_type;
     * 用全局变量加叉符号表表示
     *
     * @param ctx
     * @return
     */
    @Override
    public TreeNode visitType_spec(MIDLParser.Type_specContext ctx) {
        return super.visitType_spec(ctx);
    }

    /**
     * scoped_name: ('::')? ID ('::' ID )*;
     *
     * @param ctx
     * @return
     */
    @Override
    public TreeNode visitScoped_name(MIDLParser.Scoped_nameContext ctx) {
        String dtype = "";
        for (int i = 0; i < ctx.getChildCount(); i++) {
            dtype += ctx.getChild(i).getText();
        }
        symbol_stack.push(dtype);
        return super.visitScoped_name(ctx);
    }

    /**
     * base_type_spec: floating_pt_type|integer_type|'char'|'string'|'boolean';
     *
     * @param ctx
     * @return
     */
    @Override
    public TreeNode visitBase_type_spec(MIDLParser.Base_type_specContext ctx) {
        if (ctx.getChild(0).getChildCount() == 0) {
            String dtype = ctx.getChild(0).getText();
            symbol_stack.push(dtype);
        } else {
            visit(ctx.getChild(0));
        }
        return super.visitBase_type_spec(ctx);
    }

    @Override
    public TreeNode visitFloating_pt_type(MIDLParser.Floating_pt_typeContext ctx) {
        String dtype = ctx.getChild(0).getText();
        symbol_stack.push(dtype);
        return null;
    }

    @Override
    public TreeNode visitInteger_type(MIDLParser.Integer_typeContext ctx) {
        return super.visitInteger_type(ctx);
    }

    @Override
    public TreeNode visitSigned_int(MIDLParser.Signed_intContext ctx) {
        String dtype = "";
        for (int i = 0; i < ctx.getChildCount(); i++) {
            dtype += ctx.getChild(i).getText() + " ";
        }
        symbol_stack.push(dtype);
        return null;
    }

    @Override
    public TreeNode visitUnsigned_int(MIDLParser.Unsigned_intContext ctx) {
        String dtype = "";
        for (int i = 0; i < ctx.getChildCount(); i++) {
            dtype += ctx.getChild(i).getText() + " ";
        }
        symbol_stack.push(dtype);
        return null;
    }

    /**
     * declarators : declarator (',' declarator )*;
     *
     * @param ctx
     * @return
     */
    @Override
    public TreeNode visitDeclarators(MIDLParser.DeclaratorsContext ctx) {
        TreeNode treeNode = new TreeNode();
      //  treeNode.dec_type = symbol_stack.pop();
        for (int i = 1; i < ctx.getChildCount() - 1; i = i + 2) {
//            out.print(ctx.getChild(i) + " ");
            visit(ctx.getChild(i + 1));
        }
        return null;
    }

    /**
     * declarator : simple_declarator | array_declarator;
     *
     * @param ctx
     * @return
     */
    @Override
    public TreeNode visitDeclarator(MIDLParser.DeclaratorContext ctx) {
        return super.visitDeclarator(ctx);
    }

    /**
     * simple_declarator : ID ('=' or_expr)?;
     * @param ctx
     * @return
     */
    @Override
    public TreeNode visitSimple_declarator(MIDLParser.Simple_declaratorContext ctx) {
        return super.visitSimple_declarator(ctx);
    }

    /**
     * array_declarator : ID '[' or_expr ']' ('=' exp_list )?;
     * @param ctx
     * @return
     */
    @Override
    public TreeNode visitArray_declarator(MIDLParser.Array_declaratorContext ctx) {
        String name=ctx.ID().getText();
        visit(ctx.or_expr());
        StringBuilder or_expr= new StringBuilder();
        while (!exp_queue.isEmpty()) or_expr.append(exp_queue.poll());
        name+="["+or_expr.toString()+"]";
        visit(ctx.exp_list());
        return null;
    }

    /**
     * exp_list : '[' or_expr ( ',' or_expr )* ']';
     * @param ctx
     * @return
     */
    @Override
    public TreeNode visitExp_list(MIDLParser.Exp_listContext ctx) {
        ArrayList<String> exp_list=new ArrayList<>();
        int n = (ctx.getChildCount() - 1) / 2;
        for (int i = 0; i < n; i++) {
            visit(ctx.getChild(2 * i + 1));
            String exp="";
            while (!exp_queue.isEmpty()) exp+=exp_queue.poll();
            exp_list.add(exp);
        }

        for (var i:exp_list) System.out.println(i);//todo:得到explist 之后干嘛呢
        return null;
    }

    @Override
    public TreeNode visitOr_expr(MIDLParser.Or_exprContext ctx) {
        visit(ctx.xor_expr(0));
        int n = (ctx.getChildCount() - 1) / 2;
        for (int i = 0; i < n; i++) {
            exp_queue.offer(ctx.getChild(2 * i + 1).getText());
            visit(ctx.xor_expr(i + 1));
        }
        return null;
    }

    @Override
    public TreeNode visitXor_expr(MIDLParser.Xor_exprContext ctx) {
        visit(ctx.and_expr(0));
        int n = (ctx.getChildCount() - 1) / 2;
        for (int i = 0; i < n; i++) {
            exp_queue.offer(ctx.getChild(2 * i + 1).getText());
            visit(ctx.and_expr(i + 1));
        }
        return null;
    }

    @Override
    public TreeNode visitAnd_expr(MIDLParser.And_exprContext ctx) {
        visit(ctx.shift_expr(0));
        int n = (ctx.getChildCount() - 1) / 2;
        for (int i = 0; i < n; i++) {
            exp_queue.offer(ctx.getChild(2 * i + 1).getText());
            visit(ctx.shift_expr(i + 1));
        }
        return null;
    }

    @Override
    public TreeNode visitShift_expr(MIDLParser.Shift_exprContext ctx) {
        visit(ctx.add_expr(0));
        int n = (ctx.getChildCount() - 1) / 2;
        for (int i = 0; i < n; i++) {
            exp_queue.offer(ctx.getChild(2 * i + 1).getText());
            visit(ctx.add_expr(i + 1));
        }
        return null;
    }

    @Override
    public TreeNode visitAdd_expr(MIDLParser.Add_exprContext ctx) {
        visit(ctx.mult_expr(0));
        int n = (ctx.getChildCount() - 1) / 2;
        for (int i = 0; i < n; i++) {
            exp_queue.offer(ctx.getChild(2 * i + 1).getText());

            visit(ctx.mult_expr(i + 1));
        }
        return null;
    }

    @Override
    public TreeNode visitMult_expr(MIDLParser.Mult_exprContext ctx) {
        visit(ctx.unary_expr(0));
        int n = (ctx.getChildCount() - 1) / 2;
        for (int i = 0; i < n; i++) {
            exp_queue.offer(ctx.getChild(2 * i + 1).getText());
            visit(ctx.unary_expr(i + 1));
        }
        return null;
    }

    @Override
    public TreeNode visitUnary_expr(MIDLParser.Unary_exprContext ctx) {
        if (ctx.getChildCount() != 1) {
            exp_queue.offer(ctx.getChild(0).getText());
        }
        visit(ctx.literal());
        return null;
    }

    @Override
    public TreeNode visitLiteral(MIDLParser.LiteralContext ctx) {
        exp_queue.offer(ctx.getChild(0).getText());
        return null;
    }
}