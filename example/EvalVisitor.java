package example;

import java.util.HashMap;
import java.util.Map;

public class EvalVisitor extends CalcBaseVisitor<Integer>{
    /** "memory" for our calculator; variable/value pairs go here */
    Map<String, Integer> memory = new HashMap<String, Integer>();

    /** ID '=' expr */
    @Override
    public Integer visitAssign(CalcParser.AssignContext ctx) {
        String id = ctx.ID().getText();  // id is left-hand side of '='
        int value = visit(ctx.expr());   // compute value of expression on right
        memory.put(id, value);           // store it in our memory
        System.out.println(id+" = "+value);
        return value;
    }

    /** expr */
    @Override
    public Integer visitPrintExpr(CalcParser.PrintExprContext ctx) {
        Integer value = visit(ctx.expr()); // evaluate the expr child
        System.out.println(value);         // print the result
        System.out.println("end");
        return 0;                          // return dummy value
    }

    /** INT */
    @Override
    public Integer visitInt(CalcParser.IntContext ctx) {
        System.out.println(Integer.valueOf(ctx.INT().getText()));return Integer.valueOf(ctx.INT().getText());
    }

    /** ID */
    @Override
    public Integer visitId(CalcParser.IdContext ctx) {
        String id = ctx.ID().getText();
        System.out.println(id);
        if ( memory.containsKey(id) ) return memory.get(id);
        return 0;
    }

    /** expr op=('*'|'/') expr */
    @Override
    public Integer visitMulDiv(CalcParser.MulDivContext ctx) {
        int left = visit(ctx.expr(0));  // get value of left subexpression
        int right = visit(ctx.expr(1)); // get value of right subexpression
        if ( ctx.op.getType() == CalcParser.MUL ) {
            System.out.println("*");
            return left * right;
        }
        System.out.println("/");
        return left / right; // must be DIV
    }

    /** expr op=('+'|'-') expr */
    @Override
    public Integer visitAddSub(CalcParser.AddSubContext ctx) {
        int left = visit(ctx.expr(0));  // get value of left subexpression
        int right = visit(ctx.expr(1)); // get value of right subexpression
        if ( ctx.op.getType() == CalcParser.ADD ) {
            System.out.println("+");
            return left + right;
        }
        System.out.println("-");
        return left - right; // must be SUB
    }

    /** '(' expr ')' */
    @Override
    public Integer visitParens(CalcParser.ParensContext ctx) {
        return visit(ctx.expr()); // return child expr's value
    }
}

