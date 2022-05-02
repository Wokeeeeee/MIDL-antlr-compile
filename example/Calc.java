package example;

import java.io.FileInputStream;
import java.io.InputStream;


import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.*;
public class Calc {

    public static void main(String[] args) throws Exception {
        InputStream is = args.length > 0 ? new FileInputStream(args[0]) : System.in;

        ANTLRInputStream input = new ANTLRInputStream(is);
        CalcLexer lexer = new CalcLexer(input);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        CalcParser parser = new CalcParser(tokens);
        ParseTree tree = parser.prog();
        System.out.println(tree.toStringTree(parser));

        EvalVisitor eval = new EvalVisitor();
        // 开始遍历语法分析树
        eval.visit(tree);

    }
}
/*
public class Calc {

    public static void main(String[] args) throws Exception {
        InputStream is = args.length > 0 ? new FileInputStream(args[0]) : System.in;

        ANTLRInputStream input = new ANTLRInputStream(is);
        CalcLexer lexer = new CalcLexer(input);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        CalcParser parser = new CalcParser(tokens);
        ParseTree tree = parser.prog();

        ParseTreeWalker walker = new ParseTreeWalker();
        walker.walk(new DirectiveListener(), tree);

        // print LISP-style tree
        System.out.println(tree.toStringTree(parser));
    }
}*/
