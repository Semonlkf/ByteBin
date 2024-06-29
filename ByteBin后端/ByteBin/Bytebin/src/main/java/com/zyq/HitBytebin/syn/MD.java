package com.zyq.HitBytebin.syn;

import com.fasterxml.jackson.databind.JsonNode;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.*;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.regex.*;
import java.util.List;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.zyq.HitBytebin.syn.*;

public class MD {
    public static final List<String> errorMessages = new ArrayList();
    private static final Pattern CODE_BLOCK_PATTERN = Pattern.compile("```(\\w+)?\\s*([\\s\\S]*?)\\s*```");

    public static void main(String[] args) throws IOException {}

    public static JsonNode parse(String content) throws IOException {

        ObjectMapper mapper = new ObjectMapper();

        ObjectNode jsonNode = mapper.createObjectNode();


        if (!errorMessages.isEmpty())
            errorMessages.clear();


        String multilineString = "";
        jsonNode.put("multilineString", multilineString);

        int number=1;
        jsonNode.put("number", number);


        File jsonFile = new File("output.json");
        try {
            mapper.writerWithDefaultPrettyPrinter().writeValue(jsonFile, jsonNode);
            System.out.println("JSON file created: " + jsonFile.getAbsolutePath());
        } catch (IOException e) {
            e.printStackTrace();
        }
//        String markdownContent = new String(Files.readAllBytes(Paths.get(file_routine)), StandardCharsets.UTF_8);
        String markdownContent = new String(content);
        String[] args = new String[0];
        int ture_flag=1;


        Matcher matcher = CODE_BLOCK_PATTERN.matcher(markdownContent);
        while (matcher.find()) {
            String BlockNum="TempBlock"+"Block"+number;
            number++;
            String language = matcher.group(1);
            String code = matcher.group(2);
            errorMessages.add(BlockNum);

            AnalyzersCheck();
            if ("java".equalsIgnoreCase(language)) {

                Java20Analyzer.compileString(args,code);

                errorMessages.addAll(Java20Analyzer.errorMessages);

                jsonNode.put(BlockNum.toString(),Java20Analyzer.errorMessages.toString());

                ture_flag=AnalyzersCheck();
                Java20Analyzer.errorMessages.clear();
                continue;
            }
            if ("python3".equalsIgnoreCase(language)) {

                PythonAnalyzer.compileString(args,code);

                errorMessages.addAll(PythonAnalyzer.errorMessages);

                jsonNode.put(BlockNum.toString(),PythonAnalyzer.errorMessages.toString());

                ture_flag=AnalyzersCheck();
                PythonAnalyzer.errorMessages.clear();
                continue;
            }
            if ("c".equalsIgnoreCase(language)) {

                CAnalyzer.compileString(args,code);

                errorMessages.addAll(CAnalyzer.errorMessages);

                jsonNode.put(BlockNum.toString(),CAnalyzer.errorMessages.toString());

                ture_flag=AnalyzersCheck();
                CAnalyzer.errorMessages.clear();
                continue;
            }
            if ("cpp".equalsIgnoreCase(language)) {

                CPP14Analyzer.compileString(args,code);

                errorMessages.addAll(CPP14Analyzer.errorMessages);
                ture_flag=AnalyzersCheck();
                CPP14Analyzer.errorMessages.clear();
                continue;
            }
            errorMessages.add("Language: " + language+" is not supported");
            errorMessages.add("Code: \n" + code);


        }
//        ture_flag=AnalyzersCheck();


        multilineString = errorMessages.toString();

        if (multilineString.contains("line"))
            ture_flag = 0;
        else
            ture_flag=1;

        jsonNode.put("multilineString", multilineString);
        jsonNode.put("number",number-1);
        jsonNode.put("flag", ture_flag);
        try {
            mapper.writerWithDefaultPrettyPrinter().writeValue(jsonFile, jsonNode);
            System.out.println("JSON file created: " + jsonFile.getAbsolutePath());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return jsonNode;
    }
    public static int AnalyzersCheck(){
        if (!(Java20Analyzer.errorMessages.isEmpty() && PythonAnalyzer.errorMessages.isEmpty()
                && CAnalyzer.errorMessages.isEmpty() && CPP14Analyzer.errorMessages.isEmpty()
        ))
            return 0;
        else
            return 1;


    }

}