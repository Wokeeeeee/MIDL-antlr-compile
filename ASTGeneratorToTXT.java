import java.io.*;

class ASTGeneratorToTXT extends MIDLBaseVisitor<String> {
    public PrintWriter out;
    public int tab_times;


    public ASTGeneratorToTXT() throws FileNotFoundException {
        File file = new File("ASTOut.txt");
        out = new PrintWriter(file);
        tab_times = 0;
    }


    @Override
    public String visitSpecification(MIDLParser.SpecificationContext ctx) {
        for (int i = 0; i < ctx.getChildCount(); i++) {
            visit(ctx.getChild(i));
        }
        out.close();//最后执行
        return null;
    }

    /**
     * definiton -> type_decl“;”| module “;”
     *
     * @param ctx
     * @return
     */
    @Override
    public String visitDefinition(MIDLParser.DefinitionContext ctx) {
        visit(ctx.getChild(0));
        out.println("\n");
        return null;
    }

    /***
     * module: 'module' ID '{' definition+ '}';
     * @param ctx
     * @return
     */
    @Override
    public String visitModule(MIDLParser.ModuleContext ctx) {
        String tab = "\t".repeat(tab_times);
        out.print(tab + ctx.getChild(0) + " ");
        out.print(ctx.ID() + "\n");
        for (int i = 3; i < ctx.getChildCount() - 1; i++) {
            visit(ctx.getChild(i));
        }
        return null;
    }

    /**
     * type_decl: struct_type | 'struct' ID;
     *
     * @param ctx
     * @return
     */
    @Override
    public String visitType_decl(MIDLParser.Type_declContext ctx) {
        tab_times++;
        if (ctx.getChildCount() == 1) {//struct_type
            out.print("\t".repeat(tab_times));
            visit(ctx.getChild(0));
        } else {
            out.print("\t".repeat(tab_times) + ctx.getChild(0).getText() + " ");
            out.print(ctx.ID() + "\n");
        }
        tab_times--;
        return null;
    }

    /***
     * struct_type: 'struct' ID '{'   member_list '}';
     * @param ctx
     * @return
     */
    @Override
    public String visitStruct_type(MIDLParser.Struct_typeContext ctx) {
        out.print(ctx.getChild(0).getText() + " ");
        out.print(ctx.ID() + "\n");
        tab_times++;
        visit(ctx.getChild(3));
        tab_times--;
        return null;
    }

    /**
     * member_list: (type_spec declarators ';')*;
     *
     * @param ctx
     * @return
     */
    @Override
    public String visitMember_list(MIDLParser.Member_listContext ctx) {
        int n = ctx.getChildCount();
        if (n == 0) {
            out.print("\t".repeat(tab_times)+"empty\n");
            return null;
        }

        for (int i = 0; i < n / 3; i++) {
            out.print("\t".repeat(tab_times)+"member\n");
            tab_times++;
            out.print("\t".repeat(tab_times)+"type_spec\n"+"\t".repeat(tab_times+1));
            visit(ctx.getChild(3 * i));
            out.print("\n"+"\t".repeat(tab_times)+"declarators\n"+"\t".repeat(tab_times+1));
            visit(ctx.getChild(3 * i + 1));
            out.print("\n\n");
            tab_times--;
        }

        return null;
    }

    /**
     * type_spec: scoped_name | base_type_spec | struct_type;
     *
     * @param ctx
     * @return
     */
    @Override
    public String visitType_spec(MIDLParser.Type_specContext ctx) {
        if (ctx.struct_type() != null) {
            visit(ctx.struct_type());
            out.print("\t".repeat(tab_times));
        }else {
            tab_times++;
            visit(ctx.getChild(0));
            tab_times--;
        }
        return null;
    }

    /**
     * scoped_name: ('::')? ID ('::' ID )*;
     *
     * @param ctx
     * @return
     */
    @Override
    public String visitScoped_name(MIDLParser.Scoped_nameContext ctx) {
        for (int i = 0; i < ctx.getChildCount(); i++) {
            out.print(ctx.getChild(i)+" ");
        }
        return null;
    }

    /**
     * base_type_spec: floating_pt_type|integer_type|'char'|'string'|'boolean';
     *
     * @param ctx
     * @return
     */
    @Override
    public String visitBase_type_spec(MIDLParser.Base_type_specContext ctx) {
        if (ctx.getChild(0).getChildCount() == 0) {
            out.print(ctx.getChild(0) + " ");
        } else {
            visit(ctx.getChild(0));
        }
        return null;
    }

    /**
     * floating_pt_type : 'float' | 'double' | 'long double';
     *
     * @param ctx
     * @return
     */
    @Override
    public String visitFloating_pt_type(MIDLParser.Floating_pt_typeContext ctx) {
        out.print(ctx.getChild(0).getText() + " ");
        return null;
    }

    /**
     * integer_type : signed_int | unsigned_int;
     *
     * @param ctx
     * @return
     */
    @Override
    public String visitInteger_type(MIDLParser.Integer_typeContext ctx) {
        visit(ctx.getChild(0));
        return null;
    }

    @Override
    public String visitSigned_int(MIDLParser.Signed_intContext ctx) {
        for (int i = 0; i < ctx.getChildCount(); i++) {
            out.print(ctx.getChild(i).getText()+" ");
        }
        return null;
    }

    @Override
    public String visitUnsigned_int(MIDLParser.Unsigned_intContext ctx) {
        for (int i = 0; i < ctx.getChildCount(); i++) {
            out.print(ctx.getChild(i).getText()+" ");
        }
        return null;
    }

    /**
     * declarators : declarator (',' declarator )*;
     *
     * @param ctx
     * @return
     */
    @Override
    public String visitDeclarators(MIDLParser.DeclaratorsContext ctx) {
        visit(ctx.getChild(0));
        int n = ctx.getChildCount() - 1;
        if (n != 0) {
            for (int i = 1; i < n; i = i + 2) {
                out.print(ctx.getChild(i) + " ");
                visit(ctx.getChild(i + 1));
            }
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
    public String visitDeclarator(MIDLParser.DeclaratorContext ctx) {
        visit(ctx.getChild(0));
        return null;
    }

    /**
     * simple_declarator : ID ('=' or_expr)?;
     *
     * @param ctx
     * @return
     */
    @Override
    public String visitSimple_declarator(MIDLParser.Simple_declaratorContext ctx) {
        out.print(ctx.ID());
        if (ctx.getChildCount() != 1) {
            tab_times++;
            out.print("\n"+"\t".repeat(tab_times+1)+"assign");
            out.print("\n"+"\t".repeat(tab_times+1));
            visit(ctx.or_expr());
            tab_times--;
        }
        return null;
    }

    /**
     * array_declarator : ID '[' or_expr ']' ('=' exp_list )?;
     *
     * @param ctx
     * @return
     */
    @Override
    public String visitArray_declarator(MIDLParser.Array_declaratorContext ctx) {
        out.print(ctx.ID() + " ");
        out.print("[ ");
        visit(ctx.or_expr());
        out.print(" ] ");
        if (ctx.getChildCount() != 4) {
            tab_times++;
            out.print("\n"+"\t".repeat(tab_times+1)+"assign");
            out.print("\n"+"\t".repeat(tab_times+1));
            visit(ctx.exp_list());
            tab_times--;
        }
        return null;
    }

    /**
     * exp_list : '[' or_expr ( ',' or_expr )* ']';
     *
     * @param ctx
     * @return
     */
    @Override
    public String visitExp_list(MIDLParser.Exp_listContext ctx) {
        out.print("[ ");
        visit(ctx.getChild(1));
        int n = (ctx.getChildCount() - 3) / 2;
        for (int i = 0; i < n; i++) {
            out.print(", ");
            visit(ctx.getChild(2 * i + 3));
        }
        out.print("] ");
        return null;

    }

    /**
     * or_expr : xor_expr ('|' xor_expr )*;
     *
     * @param ctx
     * @return
     */
    @Override
    public String visitOr_expr(MIDLParser.Or_exprContext ctx) {
        visit(ctx.xor_expr(0));
        int n = (ctx.getChildCount() - 1) / 2;
        for (int i = 0; i < n; i++) {
            out.print(ctx.getChild(2 * i + 1));
            visit(ctx.xor_expr(i + 1));
        }
        return null;
    }

    /**
     * xor_expr : and_expr ('^' and_expr )*;
     *
     * @param ctx
     * @return
     */
    @Override
    public String visitXor_expr(MIDLParser.Xor_exprContext ctx) {
        visit(ctx.and_expr(0));
        int n = (ctx.getChildCount() - 1) / 2;
        for (int i = 0; i < n; i++) {
            out.print(ctx.getChild(2 * i + 1));
            visit(ctx.and_expr(i + 1));
        }
        return null;
    }

    /**
     * and_expr : shift_expr ('&' shift_expr )*;
     *
     * @param ctx
     * @return
     */
    @Override
    public String visitAnd_expr(MIDLParser.And_exprContext ctx) {
        visit(ctx.shift_expr(0));
        int n = (ctx.getChildCount() - 1) / 2;
        for (int i = 0; i < n; i++) {
            out.print(ctx.getChild(2 * i + 1));
            visit(ctx.shift_expr(i + 1));
        }
        return null;
    }

    /**
     * shift_expr : add_expr ( ('>>' | '<<') add_expr )*;
     *
     * @param ctx
     * @return
     */
    @Override
    public String visitShift_expr(MIDLParser.Shift_exprContext ctx) {
        visit(ctx.add_expr(0));
        int n = (ctx.getChildCount() - 1) / 2;
        for (int i = 0; i < n; i++) {
            out.print(ctx.getChild(2 * i + 1));
            visit(ctx.add_expr(i + 1));
        }
        return null;
    }

    @Override
    public String visitAdd_expr(MIDLParser.Add_exprContext ctx) {
        visit(ctx.mult_expr(0));
        int n = (ctx.getChildCount() - 1) / 2;
        for (int i = 0; i < n; i++) {
            out.print(ctx.getChild(2 * i + 1));
            visit(ctx.mult_expr(i + 1));
        }
        return null;
    }

    @Override
    public String visitMult_expr(MIDLParser.Mult_exprContext ctx) {
        visit(ctx.unary_expr(0));
        int n = (ctx.getChildCount() - 1) / 2;
        for (int i = 0; i < n; i++) {
            out.print(ctx.getChild(2 * i + 1));
            visit(ctx.unary_expr(i + 1));
        }
        return null;
    }

    /**
     * unary_expr : ('-'| '+' | '~')? literal;
     *
     * @param ctx
     * @return
     */
    @Override
    public String visitUnary_expr(MIDLParser.Unary_exprContext ctx) {
        if (ctx.getChildCount() != 1) {
            out.print(ctx.getChild(0));
        }
//        out.print();ln(ctx.literal());
        visit(ctx.literal());
        return null;
    }

    @Override
    public String visitLiteral(MIDLParser.LiteralContext ctx) {
        out.print(ctx.getChild(0));
        return null;
    }
}