import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;

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
        sb.append(type).append("   :\n");
        for (var entry : declarators.entrySet()) {
            sb.append(entry.getKey()).append(" ").append(entry.getValue()).append("\n");
        }
        return sb.toString();
    }
}


class TreeNode {
    public ArrayList<TreeNode> children = new ArrayList<>();

    enum NODETYPE {
        MODULE,
        STRUCT
    }

    public NODETYPE nodetype;
    //module
    public String module_id;
    public ArrayList<TreeNode> definitions = new ArrayList<>();

    //struct
    public String struct_id;
    public ArrayList<Member> members = new ArrayList<>();//can be null

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
        //  for (var i : astLoader.symbol_stack) System.out.println(i);
        //  while (!astLoader.exp_queue.isEmpty()) System.out.print(astLoader.exp_queue.poll());
    }

    TreeNode root = new TreeNode();
    String simple_exp = "";
    LinkedHashMap<String, String> declarator_map = new LinkedHashMap<>();
    Stack<String> symbol_stack = new Stack<>();
    Queue<String> exp_queue = new LinkedList<>();//start from or_expr


    //semantic analysis
    ArrayList<String> struct_names = new ArrayList<>();

    /**
     * specification: (definition)+;
     *
     * @param ctx
     * @return
     */
    @Override
    public TreeNode visitSpecification(MIDLParser.SpecificationContext ctx) {
        ArrayList<TreeNode> specification = new ArrayList<>();
        for (int i = 0; i < ctx.getChildCount(); i++) {
            specification.add(visit(ctx.getChild(i)));
        }
        for (var spec : specification) System.out.println(spec);
        return null;
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
        TreeNode treeNode = new TreeNode();
        treeNode.nodetype = TreeNode.NODETYPE.MODULE;
        treeNode.module_id = ctx.ID().getText();
        for (int i = 0; i < ctx.getChildCount() - 4; i++) {
            treeNode.definitions.add(visit(ctx.definition(i)));
        }
        return treeNode;
    }

    @Override
    public TreeNode visitType_decl(MIDLParser.Type_declContext ctx) {
        TreeNode treeNode = new TreeNode();
        if (ctx.getChildCount() > 1) {
            treeNode.struct_id = ctx.ID().getText();
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
        TreeNode treeNode = new TreeNode();
        treeNode.struct_id = ctx.ID().getText();
        treeNode.nodetype = TreeNode.NODETYPE.STRUCT;
        treeNode.members = visit(ctx.getChild(3)).members;
        struct_names.clear();
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
            String type = symbol_stack.pop();
            Member m = new Member(type);
            m.declarators.putAll(declarator_map);
            declarator_map.get(0);
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
            symbol_stack.push(ctx.getChild(0).getText().substring(0, ctx.getChild(0).getText().indexOf("{")));
        }
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
        if (struct_names.contains(name)) {
            System.err.println(
                    "line " + ctx.start.getLine() + ":" + ctx.start.getCharPositionInLine() + " repeated variable '" + ctx.start.getText() + "'" +
                            "    semantic error 1.1: " +
                            "In the same struct space, there cannot be variables with the same name.  "
            );
            throw new RuntimeException("Semantic Error 1.1");
        }
        StringBuilder exp = new StringBuilder();
        while (!exp_queue.isEmpty()) exp.append(exp_queue.poll()).append(" ");
        declarator_map.put(name, exp.toString());
        struct_names.add(name);
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

        if (struct_names.contains(name)) {
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
        declarator_map.put(name, exp.toString());
        struct_names.add(name);
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