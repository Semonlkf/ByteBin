package com.zyq.HitBytebin.syn;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.*;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import com.zyq.HitBytebin.syn.gen.C.CLexer;
import com.zyq.HitBytebin.syn.gen.C.CParser;
public class CAnalyzer {
    public static final List<String> errorMessages = new ArrayList();

    public CAnalyzer() {
    }

    public void main(String[] var1) throws IOException {
        this.compileFile(var1);
        compileString(var1, "a");
        Scanner var2 = new Scanner(System.in);
        System.out.print("Please enter the path to the C source file: ");
        String var3 = var2.nextLine();
        System.out.println("The lisp style ast of : ");
    }

    public void compileFile(String[] var1) throws IOException {
        Scanner var2 = new Scanner(System.in);
        System.out.print("Please enter the path to the C source file: ");
        String var3 = var2.nextLine();
        String var4 = new String(Files.readAllBytes(Paths.get(var3)));
        ByteArrayInputStream var5 = new ByteArrayInputStream(var4.getBytes(StandardCharsets.UTF_8));
        compile(var5);
    }

    public static void compileString(String[] var0, String var1) throws IOException {
        ByteArrayInputStream var2 = new ByteArrayInputStream(var1.getBytes(StandardCharsets.UTF_8));
        compile(var2);
    }

    public static void compile(InputStream var0) throws IOException {
        CharStream var1 = CharStreams.fromStream(var0);
        CLexer var2 = new CLexer(var1);
        var2.removeErrorListeners();
        var2.addErrorListener(new CLexerErrorListener());
        CommonTokenStream var3 = new CommonTokenStream(var2);
        CParser var4 = new CParser(var3);
        var4.removeErrorListeners();
        var4.addErrorListener(new CParserErrorListener());
        CParser.CompilationUnitContext var5 = var4.compilationUnit();
        new ParseTreeWalker();
        if (errorMessages.isEmpty()) {
            System.out.println("pass");
            MD.errorMessages.add("pass");
        } /*else {
            System.out.println("The lisp style ast of : " + var1);
            System.out.println(var5.toStringTree(var4));
            List var7 = Arrays.asList(var4.getRuleNames());
            String var8 = TreeUtils.toPrettyTree(var5, var7);
            System.out.println(var8);
        }*/

    }
}
