package example;

public class DirectiveListener extends CalcBaseListener {

    @Override
    public void exitPrintExpr(CalcParser.PrintExprContext ctx) {
        System.out.println("RET\n");
    }

    @Override
    public void exitAssign(CalcParser.AssignContext ctx) {
        String id = ctx.ID().getText();
        System.out.println("= " + id);
    }

    @Override
    public void exitMulDiv(CalcParser.MulDivContext ctx) {
        if (ctx.op.getType() == CalcParser.MUL) {
            System.out.println("*");
        } else {
            System.out.println("/");
        }
    }

    @Override
    public void exitAddSub(CalcParser.AddSubContext ctx) {
        if (ctx.op.getType() == CalcParser.ADD) {
            System.out.println("+");
        } else {
            System.out.println("-");
        }
    }

    @Override
    public void exitId(CalcParser.IdContext ctx) {
        System.out.println("LDV " + ctx.ID().getText());
    }

    @Override
    public void exitInt(CalcParser.IntContext ctx) {
        System.out.println("LDC " + ctx.INT().getText());
    }
}