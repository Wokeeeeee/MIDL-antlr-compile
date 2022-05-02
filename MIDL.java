

import java.io.FileInputStream;
import java.io.InputStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.*;

public class MIDL {

    public static void main(String[] args) throws Exception {
        InputStream is = args.length > 0 ? new FileInputStream(args[0]) : System.in;

        ANTLRInputStream input = new ANTLRInputStream(is);
        MIDLLexer lexer = new MIDLLexer(input);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        MIDLParser parser = new MIDLParser(tokens);
        ParseTree tree = parser.specification();
        System.out.println(tree.toStringTree(parser));

        ASTGenerator eval = new ASTGenerator();
        // 开始遍历语法分析树
        eval.visit(tree);
    }
}
