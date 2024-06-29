package com.zyq.HitBytebin.syn;
import org.antlr.v4.runtime.BaseErrorListener;
import org.antlr.v4.runtime.RecognitionException;
import org.antlr.v4.runtime.Recognizer;

public class CLexerErrorListener extends BaseErrorListener {
    public CLexerErrorListener() {
    }

    public void syntaxError(Recognizer<?, ?> var1, Object var2, int var3, int var4, String var5, RecognitionException var6) {
        String var10="line " + var3 + ":" + var4 + " " + var5;
        CAnalyzer.errorMessages.add(var10);
    }
}
