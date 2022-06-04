import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;
import java.util.regex.Pattern;

class Member {
    String type;

    LinkedHashMap<String, String> declarators;

    public Member(String type) {
        this.type = type;
        this.declarators = new LinkedHashMap<>();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("  type :  ").append(type);
        for (var entry : declarators.entrySet()) {
            sb.append("  declarator:  ").append(entry.getKey());
            if (!entry.getValue().isEmpty()) sb.append("  assign:  ").append(entry.getValue());
        }
        sb.append("\n");
        return sb.toString();
    }
}


class TreeNode {
    //spec
    public ArrayList<TreeNode> specification = new ArrayList<>();

    enum NODETYPE {
        NULL,
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
    public ArrayList<TreeNode> inner_structs = new ArrayList<>();

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        switch (nodetype) {
            case MODULE:
                sb.append("MODULE " + module_id + " :\n");
                for (var def : definitions) sb.append(def);
                break;
            case STRUCT:
                sb.append("STRUCT " + struct_id + " :\n");
                for (var mem : members) sb.append(mem.toString());
                break;
        }
        return sb.toString();
    }

    public String printAST(TreeNode root, int tab_times, StringBuilder output) {
        switch (root.nodetype) {
            case MODULE:
                output.append("\t".repeat(tab_times));
                output.append("Module ").append(root.module_id).append(" :\n");
                for (var def : root.definitions)
                    printAST(def, tab_times + 1, output);
                break;
            case SPEC:
                for (var spec : root.specification) printAST(spec, tab_times, output);
                break;
            case STRUCT:
                output.append("\t".repeat(tab_times));
                output.append("Struct ").append(root.struct_id).append(" :\n");
                for (var member : root.members) {
                    output.append("\t".repeat(tab_times + 1));
                    output.append(member.toString());
                }
                return output.toString();
        }
        return output.toString();
    }
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
//        for (var i : astLoader.structs_names) System.out.println(i);
        //  while (!astLoader.exp_queue.isEmpty()) System.out.print(astLoader.exp_queue.poll());
    }

    String simple_exp = "";
    LinkedHashMap<String, String> declarator_map = new LinkedHashMap<>();
    Stack<String> symbol_stack = new Stack<>();
    Queue<String> exp_queue = new LinkedList<>();//start from or_expr


    //semantic analysis
    ArrayList<String> valuables_names = new ArrayList<>(); //1.1 1.2
    ArrayList<String> structs_names = new ArrayList<>();//1.3
    String namespace = "";

    //type check
    ArrayList<String> signed_set = new ArrayList<>(List.of("short", "int16", "long", "int32", "long long", "int64", "int8"));
    ArrayList<String> unsigned_set = new ArrayList<>(List.of("unsigned short", "uint16", "unsigned long", "uint32", "unsigned long long", "uint64", "uint8"));
    ArrayList<String> floating_set = new ArrayList<>(List.of("float", "double", "long double"));

    /**
     * specification: (definition)+;
     *
     * @param ctx
     * @return
     */
    @Override
    public TreeNode visitSpecification(MIDLParser.SpecificationContext ctx) {
        TreeNode root = new TreeNode();
        root.nodetype = TreeNode.NODETYPE.SPEC;
        for (int i = 0; i < ctx.getChildCount(); i++) {
            root.specification.add(visit(ctx.getChild(i)));
        }
        new HxxGenerator().GenerateHxx(root);
        return root;
    }

    /**
     * definition: type_decl ';' | module ';';
     *
     * @param ctx
     * @return
     */
    @Override
    public TreeNode visitDefinition(MIDLParser.DefinitionContext ctx) {
        return visit(ctx.getChild(0));
    }

    /**
     * module: 'module' ID '{' definition+ '}';
     *
     * @param ctx
     * @return
     */
    @Override
    public TreeNode visitModule(MIDLParser.ModuleContext ctx) {
        namespace += ctx.ID().getText() + "::";
        TreeNode treeNode = new TreeNode();
        treeNode.nodetype = TreeNode.NODETYPE.MODULE;
        treeNode.module_id = ctx.ID().getText();
        for (int i = 0; i < ctx.getChildCount() - 4; i++) {
            treeNode.definitions.add(visit(ctx.definition(i)));
        }
        namespace = namespace.substring(0, namespace.indexOf(ctx.ID().getText()));
        return treeNode;
    }

    @Override
    public TreeNode visitType_decl(MIDLParser.Type_declContext ctx) {
        TreeNode treeNode = new TreeNode();
        if (ctx.getChildCount() > 1) {

            if (structs_names.contains(namespace + ctx.ID().getText())) {
                System.err.println(
                        "line " + ctx.start.getLine() + ":" + ctx.start.getCharPositionInLine() + " repeated variable '" + namespace + ctx.ID().getText() + "'" +
                                "    semantic error 1.3: " +
                                "In the same module space, there cannot be structs with the same name.  "
                );
                throw new RuntimeException("Semantic Error 1.3");
            }
            treeNode.struct_id = ctx.ID().getText();
            structs_names.add(namespace + treeNode.struct_id);
            treeNode.nodetype = TreeNode.NODETYPE.STRUCT;
        } else {
            return visit(ctx.struct_type());
        }

        return treeNode;
    }

    /**
     * struct_type: 'struct' ID '{'   member_list '}';
     *
     * @param ctx
     * @return
     */
    @Override
    public TreeNode visitStruct_type(MIDLParser.Struct_typeContext ctx) {
        if (structs_names.contains(namespace + ctx.ID().getText())) {
            System.err.println(
                    "line " + ctx.start.getLine() + ":" + ctx.start.getCharPositionInLine() + " repeated variable '" + namespace + ctx.ID().getText() + "'" +
                            "    semantic error 1.3: " +
                            "In the same module space, there cannot be structs with the same name.  "
            );
            throw new RuntimeException("Semantic Error 1.3");
        }
        TreeNode treeNode = new TreeNode();
        treeNode.struct_id = ctx.ID().getText();
        structs_names.add(namespace + treeNode.struct_id);
        treeNode.nodetype = TreeNode.NODETYPE.STRUCT;
        treeNode.members = visit(ctx.getChild(3)).members;
        valuables_names.clear();
        return treeNode;
    }

    /**
     * member_list: (type_spec declarators ';')*;
     * 问题:如何处理type_spec是struct类型的问题
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
            String type = symbol_stack.pop().strip();
            Member m = new Member(type);
            m.declarators.putAll(declarator_map);
            treeNode.members.add(m);
            declarator_map.clear();

        }
        return treeNode;
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
        if (ctx.getChild(0).getChild(0).getText().equals("struct")) {
            TreeNode treeNode = visit(ctx.struct_type());
            symbol_stack.push(treeNode.struct_id);
            structs_names.remove(structs_names.size() - 1);
            return treeNode;
        } else {
            return super.visitType_spec(ctx);
        }
    }

    /**
     * scoped_name: ('::')? ID ('::' ID )*;
     *
     * @param ctx
     * @return
     */
    @Override
    public TreeNode visitScoped_name(MIDLParser.Scoped_nameContext ctx) {
        StringBuilder dtype = new StringBuilder();
        dtype.append(namespace);
        for (int i = 0; i < ctx.getChildCount(); i++) {
            if (i == 0 && ctx.getChild(i).getText().equals("::")) continue;
            dtype.append(ctx.getChild(i).getText());
        }
        if (!structs_names.contains(dtype.toString())) {
            for (var name : structs_names) {
                if (name.contains(dtype.toString())) {
                    System.err.println(
                            "line " + ctx.start.getLine() + ":" + ctx.start.getCharPositionInLine() + " repeated variable '" + dtype + "'" +
                                    "    semantic error 2.2: " +
                                    "The structure is defined, but the namespace reference is incorrect. "
                    );
                    throw new RuntimeException("Semantic Error 2.2");
                }
            }
            System.err.println(
                    "line " + ctx.start.getLine() + ":" + ctx.start.getCharPositionInLine() + " repeated variable '" + dtype + "'" +
                            "    semantic error 2.1: " +
                            "The struct must be declared before it can be used.  "
            );
            throw new RuntimeException("Semantic Error 2.1");
        }

        symbol_stack.push(dtype.toString());
        return null;
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
        return null;
    }

    @Override
    public TreeNode visitFloating_pt_type(MIDLParser.Floating_pt_typeContext ctx) {
        symbol_stack.push(ctx.getChild(0).getText());
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
        for (int i = 0; i < (ctx.getChildCount() + 1) / 2; i++) {
            visit(ctx.declarator(i));

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
     *
     * @param ctx
     * @return
     */
    @Override
    public TreeNode visitSimple_declarator(MIDLParser.Simple_declaratorContext ctx) {
        String name = ctx.ID().getText();
        if (ctx.getChildCount() > 1) visit(ctx.or_expr());

        //todo: 2.1 检查同一个struct下是否有同名变量
        // 在检查declarator的时候,检查一下当前的name是否和已经有的map冲突
        if (valuables_names.contains(name)) {
            System.err.println(
                    "line " + ctx.start.getLine() + ":" + ctx.start.getCharPositionInLine() + " repeated variable '" + ctx.start.getText() + "'" +
                            "    semantic error 1.1: " +
                            "In the same struct space, there cannot be variables with the same name.  "
            );
            throw new RuntimeException("Semantic Error 1.1");
        }
        StringBuilder exp = new StringBuilder();
        while (!exp_queue.isEmpty()) {
            exp.append(exp_queue.poll()).append(" ");
        }
        //todo: 3 进行字面量类型检查
        String dtype = symbol_stack.peek().strip();

        if ((unsigned_set.contains(dtype) || signed_set.contains(dtype)) && !exp.toString().strip().isEmpty()) {
            //3.3 判断是否为浮点数
            Pattern pattern = Pattern.compile("[-+]?[0-9]*\\.[0-9]+");
            if (pattern.matcher(exp.toString().strip()).matches()) {
                System.err.println(
                        "line " + ctx.start.getLine() + ":" + ctx.start.getCharPositionInLine() + " repeated variable '" + ctx.start.getText() + "'" +
                                "    semantic error 3.3: " +
                                "It is an integer variable, but the literal is a floating-point type "
                );
                throw new RuntimeException("Semantic Error 3.3");
            }

            //3.1 整形变量,字面却是字符
            String answer = ExpressionCalculator.calculate(exp.toString());
//            System.out.println(name + " " + answer + " " + dtype);
            try {
                Long.parseLong(answer);
            } catch (NumberFormatException e) {
                System.err.println(
                        "line " + ctx.start.getLine() + ":" + ctx.start.getCharPositionInLine() + " repeated variable '" + ctx.start.getText() + "'" +
                                "    semantic error 3.1: " +
                                "It is an integer variable, but the literal is a wrong type  "
                );
                throw new RuntimeException("Semantic Error 3.1");
            }

            //3.2 short 越界
            if (dtype.equals("short")) {
                long tmp = Long.parseLong(answer);
                if (tmp > Short.MAX_VALUE || tmp < Short.MIN_VALUE) {
                    System.err.println(
                            "line " + ctx.start.getLine() + ":" + ctx.start.getCharPositionInLine() + " repeated variable '" + ctx.start.getText() + "'" +
                                    "    semantic error 3.2: " +
                                    "short is a signed short integer, the input number is out of bounds "
                    );
                    throw new RuntimeException("Semantic Error 3.2");
                }
            }


        }

        declarator_map.put(name, exp.toString());
        valuables_names.add(name);
        return null;
    }


    /**
     * array_declarator : ID '[' or_expr ']' ('=' exp_list )?;
     *
     * @param ctx
     * @return
     */
    @Override
    public TreeNode visitArray_declarator(MIDLParser.Array_declaratorContext ctx) {
        String name = ctx.ID().getText();
        visit(ctx.or_expr());
        StringBuilder or_expr = new StringBuilder();
        while (!exp_queue.isEmpty()) or_expr.append(exp_queue.poll());
        //处理数组大小方案,放进type里面
        symbol_stack.push(symbol_stack.pop() + "[" + or_expr + "]");

        visit(ctx.exp_list());

        if (valuables_names.contains(name)) {
            System.err.println(
                    "line " + ctx.start.getLine() + ":" + ctx.start.getCharPositionInLine() + " repeated variable '" + ctx.start.getText() + "'" +
                            "    semantic error 1.1: " +
                            "In the same struct space, there cannot be variables with the same name.  "
            );
            throw new RuntimeException("Semantic Error 1.1");
        }


        //hashmap
        StringBuilder exp = new StringBuilder();
        while (!exp_queue.isEmpty()) exp.append(exp_queue.poll()).append(" ");

        //3.4
        String dtype = symbol_stack.peek().strip();
        int num = Integer.parseInt(ExpressionCalculator.calculate(dtype.substring(dtype.indexOf("[") + 1, dtype.indexOf("]"))));
        dtype = dtype.substring(0, dtype.indexOf("[")).strip();
        String[] arrays = exp.toString().strip().split(" ");
        if (arrays.length != num) {
            System.err.println(
                    "line " + ctx.start.getLine() + ":" + ctx.start.getCharPositionInLine() + " repeated variable '" + ctx.start.getText() + "'" +
                            "    semantic error 3.4.1: " +
                            "The length of the array input is not the same as the declared length.  "
            );
            throw new RuntimeException("Semantic Error 3.4.1");
        }
        if ((unsigned_set.contains(dtype) || signed_set.contains(dtype)) && arrays.length != 0) {
            try {
                for (var a : arrays) {
                    String answer = ExpressionCalculator.calculate(a);
                    Integer.parseInt(answer);
                }
            } catch (NumberFormatException e) {
                System.err.println(
                        "line " + ctx.start.getLine() + ":" + ctx.start.getCharPositionInLine() + " repeated variable '" + ctx.start.getText() + "'" +
                                "    semantic error 3.4.2: " +
                                "The data entered in the array is not an integer variable.  "
                );
                throw new RuntimeException("Semantic Error 3.4.2");

            }

        }
        declarator_map.put(name, exp.toString());
        valuables_names.add(name);
        return null;
    }

    /**
     * exp_list : '[' or_expr ( ',' or_expr )* ']';
     *
     * @param ctx
     * @return
     */
    @Override
    public TreeNode visitExp_list(MIDLParser.Exp_listContext ctx) {
//        ArrayList<String> exp_list = new ArrayList<>();
        int n = (ctx.getChildCount() - 1) / 2;
        for (int i = 0; i < n; i++) {
            visit(ctx.getChild(2 * i + 1));
//            String exp = "";
//            while (!exp_queue.isEmpty()) exp += exp_queue.poll();
//            exp_list.add(exp);
        }

//        for (var i : exp_list) System.out.println(i);//todo:得到explist 之后干嘛呢
        return null;
    }

    /**
     * or_expr : xor_expr ('|' xor_expr )*;
     *
     * @param ctx
     * @return
     */
    @Override
    public TreeNode visitOr_expr(MIDLParser.Or_exprContext ctx) {
        visit(ctx.xor_expr(0));
        int n = (ctx.getChildCount() - 1) / 2;
        for (int i = 0; i < n; i++) {
            simple_exp += ctx.getChild(2 * i + 1).getText();
            visit(ctx.xor_expr(i + 1));
        }
        exp_queue.offer(simple_exp);
        simple_exp = "";
        return null;
    }

    @Override
    public TreeNode visitXor_expr(MIDLParser.Xor_exprContext ctx) {
        visit(ctx.and_expr(0));
        int n = (ctx.getChildCount() - 1) / 2;
        for (int i = 0; i < n; i++) {
            simple_exp += ctx.getChild(2 * i + 1).getText();
            visit(ctx.and_expr(i + 1));
        }
        return null;
    }

    @Override
    public TreeNode visitAnd_expr(MIDLParser.And_exprContext ctx) {
        visit(ctx.shift_expr(0));
        int n = (ctx.getChildCount() - 1) / 2;
        for (int i = 0; i < n; i++) {
            simple_exp += ctx.getChild(2 * i + 1).getText();
            visit(ctx.shift_expr(i + 1));
        }
        return null;
    }

    @Override
    public TreeNode visitShift_expr(MIDLParser.Shift_exprContext ctx) {
        visit(ctx.add_expr(0));
        int n = (ctx.getChildCount() - 1) / 2;
        for (int i = 0; i < n; i++) {
            simple_exp += ctx.getChild(2 * i + 1).getText();
            visit(ctx.add_expr(i + 1));
        }
        return null;
    }

    @Override
    public TreeNode visitAdd_expr(MIDLParser.Add_exprContext ctx) {
        visit(ctx.mult_expr(0));
        int n = (ctx.getChildCount() - 1) / 2;
        for (int i = 0; i < n; i++) {
            simple_exp += ctx.getChild(2 * i + 1).getText();
            visit(ctx.mult_expr(i + 1));
        }
        return null;
    }

    @Override
    public TreeNode visitMult_expr(MIDLParser.Mult_exprContext ctx) {
        visit(ctx.unary_expr(0));
        int n = (ctx.getChildCount() - 1) / 2;
        for (int i = 0; i < n; i++) {
            simple_exp += ctx.getChild(2 * i + 1).getText();
            visit(ctx.unary_expr(i + 1));
        }
        return null;
    }

    @Override
    public TreeNode visitUnary_expr(MIDLParser.Unary_exprContext ctx) {
        if (ctx.getChildCount() != 1) {
            simple_exp += ctx.getChild(0).getText();
        }
        visit(ctx.literal());
        return null;
    }

    @Override
    public TreeNode visitLiteral(MIDLParser.LiteralContext ctx) {
        simple_exp += ctx.getChild(0).getText();
        return null;
    }
}