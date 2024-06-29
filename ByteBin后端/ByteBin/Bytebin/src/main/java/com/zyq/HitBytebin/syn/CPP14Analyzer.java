package com.zyq.HitBytebin.syn;
import com.zyq.HitBytebin.syn.gen.CPP14.*;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;

public class CPP14Analyzer {
    public static final List<String> errorMessages = new ArrayList();

    public CPP14Analyzer() {
    }

    public static void main(String[] var0) {
        if (var0.length != 1) {
            System.err.println("Usage: java PythonAnalyzer <python-file-path>");
            System.exit(1);
        }

        String var1 = var0[0];

        try {
            String var2 = new String(Files.readAllBytes(Paths.get(var1)));
            CPP14Lexer var3 = new CPP14Lexer(CharStreams.fromString(var2));
            CommonTokenStream var4 = new CommonTokenStream(var3);
            new CPP14Parser(var4);
        } catch (IOException var6) {
            System.err.println("Error reading file: " + var6.getMessage());
        } catch (RuntimeException var7) {
            System.err.println("Runtime error: " + var7.getMessage());
        }

    }

    public static void compileString(String[] var0, String var1) throws IOException {
        ByteArrayInputStream var2 = new ByteArrayInputStream(var1.getBytes(StandardCharsets.UTF_8));
        compile(var2);
    }

    public static void compile(InputStream var0) throws IOException {
        CharStream var1 = CharStreams.fromStream(var0);
        CPP14Lexer var2 = new CPP14Lexer(var1);
        var2.removeErrorListeners();
        var2.addErrorListener(new CPP14LexerErrorListener());
        CommonTokenStream var3 = new CommonTokenStream(var2);
        CPP14Parser var4 = new CPP14Parser(var3);
        var4.removeErrorListeners();
        var4.addErrorListener(new CPP14ParserErrorListener());
        List<String> ruleNamesList = Arrays.asList(var4.getRuleNames());
        var4.setBuildParseTree(true);
        ParseTree tree = var4.translationUnit();
        String prettyTree = TreeUtils.toPrettyTree(tree, ruleNamesList);

        if (errorMessages.isEmpty()) {
            System.out.println("pass");
            MD.errorMessages.add("pass");
        }
        /*else {
            System.out.println(prettyTree);
        }*/

    }
}
