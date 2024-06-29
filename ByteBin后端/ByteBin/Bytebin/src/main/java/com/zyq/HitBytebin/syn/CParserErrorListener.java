package com.zyq.HitBytebin.syn;
import org.antlr.v4.runtime.BaseErrorListener;
import org.antlr.v4.runtime.RecognitionException;
import org.antlr.v4.runtime.Recognizer;

public class CParserErrorListener extends BaseErrorListener {
    public CParserErrorListener() {
    }

    public void syntaxError(Recognizer<?, ?> var1, Object var2, int var3, int var4, String var5, RecognitionException var6) {
        //System.err.println("line " + var3 + ":" + var4 + " " + var5);
        String var10="line " + var3 + ":" + var4 + " " + var5;
        CAnalyzer.errorMessages.add(var10);
    }
}
