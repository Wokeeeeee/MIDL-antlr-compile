class ASTGenerator extends MIDLBaseVisitor<String> {
    @Override
    public String visitSpecification(MIDLParser.SpecificationContext ctx) {
        System.out.print("[specification ");
        for (int i = 0; i < ctx.getChildCount(); i++) {
            visit(ctx.getChild(i));
        }
        System.out.print("]");
        return null;
    }

    @Override
    public String visitDefinition(MIDLParser.DefinitionContext ctx) {
        System.out.print("[definition ");
        visit(ctx.getChild(0));
        System.out.print(ctx.getChild(1).getText());
        System.out.print("]");
        return null;
    }

    /***
     * module: 'module' ID '{' definition+ '}';
     * @param ctx
     * @return
     */
    @Override
    public String visitModule(MIDLParser.ModuleContext ctx) {
        System.out.print("[module ");
        System.out.print(ctx.getChild(0) + " ");
        System.out.print(ctx.ID() + " ");
        System.out.print(ctx.getChild(2) + " ");
        for (int i = 3; i < ctx.getChildCount() - 1; i++) {
            visit(ctx.getChild(i));
        }
        System.out.print("}");
        System.out.print("]");
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
        System.out.print("[type_decl ");
        if (ctx.getChildCount() == 1) {//struct_type
            visit(ctx.getChild(0));
        } else {
            System.out.print(ctx.getChild(0).getText() + " ");
            System.out.print(ctx.ID());
        }
        System.out.print("]");
        return null;
    }

    /***
     * struct_type: 'struct' ID '{'   member_list '}';
     * @param ctx
     * @return
     */
    @Override
    public String visitStruct_type(MIDLParser.Struct_typeContext ctx) {
        System.out.print("[struct_type ");
        System.out.print(ctx.getChild(0).getText()+" ");
        System.out.print(ctx.ID()+" ");
        System.out.print(ctx.getChild(2).getText()+" ");
        visit(ctx.getChild(3));
        System.out.print(ctx.getChild(4).getText()+" ");
        System.out.print("]");
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
        System.out.print("[member_list ");
        int n = ctx.getChildCount();
        if (n == 0) {
            System.out.print("]");
            return null;
        } else {
            for (int i = 0; i < n / 3; i++) {
                visit(ctx.getChild(3 * i));
                visit(ctx.getChild(3 * i + 1));
                System.out.print(ctx.getChild(3 * i + 2).getText());
            }
        }
        System.out.print("]");
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
        System.out.print("[type_spec ");
        visit(ctx.getChild(0));
        System.out.print("]");
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
        System.out.print("[scoped_name ");
        for (int i = 0; i < ctx.getChildCount(); i++) {
            System.out.print(ctx.getChild(i));
        }
        System.out.print("]");
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
        System.out.print("[base_type_spec ");
        if (ctx.getChild(0).getChildCount() == 0) {
            System.out.print(ctx.getChild(0));
        } else {
            visit(ctx.getChild(0));
        }
        System.out.print("]");
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
        System.out.print("[floating_pt_type ");
        System.out.print(ctx.getChild(0).getText());
        System.out.print("]");
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
        System.out.print("[integer_type ");
        visit(ctx.getChild(0));
        System.out.print("]");
        return null;
    }

    @Override
    public String visitSigned_int(MIDLParser.Signed_intContext ctx) {
        System.out.print("[signed_int ");
        for (int i = 0; i < ctx.getChildCount(); i++) {
            System.out.print(ctx.getChild(i).getText());
        }
        System.out.print("]");
        return null;
    }

    @Override
    public String visitUnsigned_int(MIDLParser.Unsigned_intContext ctx) {
        System.out.print("[unsigned_int ");
        for (int i = 0; i < ctx.getChildCount(); i++) {
            System.out.print(ctx.getChild(i).getText());
        }
        System.out.print("]");
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
        System.out.print("[declarators ");
        visit(ctx.getChild(0));
        int n = ctx.getChildCount() - 1;
        if (n != 0) {
            for (int i = 1; i < n; i = i + 2) {
                System.out.print(ctx.getChild(i) + " ");
                visit(ctx.getChild(i + 1));
            }
        }
        System.out.print("]");
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
        System.out.print("[declarator ");
        visit(ctx.getChild(0));
        System.out.print("]");
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
        System.out.print("[simple_declarator ");
        System.out.print(ctx.ID());
        if (ctx.getChildCount() != 1) {
            System.out.print("= ");
            visit(ctx.or_expr());
        }
        System.out.print("]");
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
        System.out.print("[array_declarator ");
        System.out.print(ctx.ID() + " ");
        System.out.print("[ ");
        visit(ctx.or_expr());
        System.out.print(" ] ");
        if (ctx.getChildCount() != 4) {
            System.out.print("= ");
            visit(ctx.exp_list());
        }
        System.out.print("]");
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
        System.out.print("[exp_list ");
        System.out.print("[ ");
        visit(ctx.getChild(1));
        int n = ctx.getChildCount() - 3;
        for (int i = 0; i < n / 2; i += 2) {
            System.out.print(", ");
            visit(ctx.getChild(i + 1));
        }
        System.out.print("] ");
        System.out.print("]");
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
        System.out.print("[or_expr ");
        visit(ctx.xor_expr(0));
        int n = (ctx.getChildCount() - 1) / 2;
        for (int i = 0; i < n; i++) {
            System.out.print(ctx.getChild(2 * i + 1));
            visit(ctx.xor_expr(i + 1));
        }
        System.out.print("]");
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
        System.out.print("[xor_expr ");
        visit(ctx.and_expr(0));
        int n = (ctx.getChildCount() - 1) / 2;
        for (int i = 0; i < n; i++) {
            System.out.print(ctx.getChild(2 * i + 1));
            visit(ctx.and_expr(i + 1));
        }
        System.out.print("]");
        System.out.print("]");
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
        System.out.print("[and_expr ");
        visit(ctx.shift_expr(0));
        int n = (ctx.getChildCount() - 1) / 2;
        for (int i = 0; i < n; i++) {
            System.out.print(ctx.getChild(2 * i + 1));
            visit(ctx.shift_expr(i + 1));
        }
        System.out.print("]");
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
        System.out.print("[shift_expr ");
        visit(ctx.add_expr(0));
        int n = (ctx.getChildCount() - 1) / 2;
        for (int i = 0; i < n; i++) {
            System.out.print(ctx.getChild(2 * i + 1));
            visit(ctx.add_expr(i + 1));
        }
        System.out.print("]");
        return null;
    }

    @Override
    public String visitAdd_expr(MIDLParser.Add_exprContext ctx) {
        System.out.print("[add_expr ");
        visit(ctx.mult_expr(0));
        int n = (ctx.getChildCount() - 1) / 2;
        for (int i = 0; i < n; i++) {
            System.out.print(ctx.getChild(2 * i + 1));
            visit(ctx.mult_expr(i + 1));
        }
        System.out.print("]");
        return null;
    }

    @Override
    public String visitMult_expr(MIDLParser.Mult_exprContext ctx) {
        System.out.print("[mult_expr ");
        visit(ctx.unary_expr(0));
        int n = (ctx.getChildCount() - 1) / 2;
        for (int i = 0; i < n; i++) {
            System.out.print(ctx.getChild(2 * i + 1));
            visit(ctx.unary_expr(i + 1));
        }
        System.out.print("]");
        return null;
    }

    /**
     *unary_expr : ('-'| '+' | '~')? literal;
     * @param ctx
     * @return
     */
    @Override
    public String visitUnary_expr(MIDLParser.Unary_exprContext ctx) {
        System.out.print("[unary_expr ");
        if (ctx.getChildCount() != 1) {
            System.out.print(ctx.getChild(0));
        }
//        System.out.println(ctx.literal());
        visit(ctx.literal());
        return null;
    }

    @Override
    public String visitLiteral(MIDLParser.LiteralContext ctx) {
        System.out.print("[literal ");
        System.out.print(ctx.getChild(0));
        return null;
    }
}