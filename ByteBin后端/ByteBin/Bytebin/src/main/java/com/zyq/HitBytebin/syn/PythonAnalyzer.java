package com.zyq.HitBytebin.syn;
import com.zyq.HitBytebin.syn.gen.C.CParser;
import com.zyq.HitBytebin.syn.gen.Python.PythonLexer;
import com.zyq.HitBytebin.syn.gen.Python.PythonParser;
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


public class PythonAnalyzer {
    public static final List<String> errorMessages = new ArrayList();

    public PythonAnalyzer() {
    }

    public static void main(String[] var0) {
        if (var0.length != 1) {
            System.err.println("Usage: java PythonAnalyzer <python-file-path>");
            System.exit(1);
        }

        String var1 = var0[0];

        try {
            String var2 = new String(Files.readAllBytes(Paths.get(var1)));
            PythonLexer var3 = new PythonLexer(CharStreams.fromString(var2));
            CommonTokenStream var4 = new CommonTokenStream(var3);
            PythonParser var5 = new PythonParser(var4);
            PythonParser.File_inputContext var6 = var5.file_input();
            System.out.println("Parse Tree: ");
            System.out.println(var6.toStringTree(var5));
        } catch (IOException var7) {
            System.err.println("Error reading file: " + var7.getMessage());
        } catch (RuntimeException var8) {
            System.err.println("Runtime error: " + var8.getMessage());
        }

    }

    public static void compileString(String[] var0, String var1) throws IOException {
        ByteArrayInputStream var2 = new ByteArrayInputStream(var1.getBytes(StandardCharsets.UTF_8));
        compile(var2);
    }

    public static void compile(InputStream var0) throws IOException {
        CharStream var1 = CharStreams.fromStream(var0);
        PythonLexer var2 = new PythonLexer(var1);
        var2.removeErrorListeners();
        var2.addErrorListener(new PythonLexerErrorListener());
        CommonTokenStream var3 = new CommonTokenStream(var2);
        PythonParser var4 = new PythonParser(var3);
        var4.removeErrorListeners();
        var4.addErrorListener(new PythonParserErrorListener());
        List<String> ruleNamesList = Arrays.asList(var4.getRuleNames());
        var4.setBuildParseTree(true);
        ParseTree tree = var4.file_input();
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
