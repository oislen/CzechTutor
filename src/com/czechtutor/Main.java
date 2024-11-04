package com.czechtutor;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Main {

    public static ArrayList<Map<String, String>> loadData(String dataFilePath) {
        ArrayList<Map<String, String>> recordSet = new ArrayList<>();
        try (BufferedReader buf = new BufferedReader(new InputStreamReader(new FileInputStream(dataFilePath), "UTF-8"))) {
            String lineJustFetched;
            String[] arrayKeys = {"EN", "CZ", "REF"};
            // iterate over file lines and transform each line into a record set
            while(true){
                lineJustFetched = buf.readLine();
                if(lineJustFetched == null){  
                    break; 
                }else{
                    String[] arrayValues;
                    arrayValues = lineJustFetched.split("\t");
                    Map<String, String> mapObject = IntStream.range(0, arrayKeys.length).boxed().collect(Collectors.toMap(i -> arrayKeys[i], i -> arrayValues[i]));
                    recordSet.add(mapObject);
                }
            }
        }catch(IOException  e){
            System.out.println(e);
        }
        return recordSet;
    }
    
    public static ArrayList<Integer> randomDataIndices(Integer nIndices, Integer upperIndexBound) {
        // randomly select nIndices between 0 and upperIndexBound
        Random indexGenerator = new Random();
        ArrayList<Integer> indexArray = new ArrayList<>();
        for (int i = 0; i<nIndices; i++)
        {
            Integer index = indexGenerator.nextInt(upperIndexBound);
            indexArray.add(index);
        }
        return indexArray;
    }
    
    public static void main(String[] args) {
        String fromLanguage;
        String toLanguage;
        Boolean isCZtoEN = true;
        Integer nQuestions = 2;
        Random answerIndexGenerator = new Random();
        // load czech / english language phrases from disk
        ArrayList<Map<String, String>> recordSet = Main.loadData("E:\\GitHub\\CzechTutor\\data\\ces-eng\\ces.txt");
        // determine language translation direction
        if(isCZtoEN){
            fromLanguage = "CZ";
            toLanguage = "EN";
        }else{
            fromLanguage = "EN";
            toLanguage = "CZ";
        }
        for (int i = 0; i<nQuestions; i++)
        {
            // randomly generate four indices from data
            Integer upperIndexBound = recordSet.size();
            Integer nIndices = 4;
            Integer answerIndex = answerIndexGenerator.nextInt(3);
            ArrayList<Integer> indexArray = Main.randomDataIndices(nIndices, upperIndexBound);
            // extract randomly select index values from record set
            ArrayList<Map<String, String>> filteredRecordSet = new ArrayList<> (indexArray.stream().map(recordSet::get).collect(Collectors.toList()));
            // determine question phrase, answer and options
            String phrase = filteredRecordSet.get(answerIndex).get(fromLanguage);
            String answer = filteredRecordSet.get(answerIndex).get(toLanguage);
            //ArrayList<String> options = filteredRecordSet.get(answerIndex).get("CZ");
            // construct basic payload
            HashMap<String,Object> payload = new HashMap<>();
            payload.put("question", i);
            payload.put("phrase", phrase);
            payload.put("answer", answer);
            System.out.println(payload);
        }
    }
}    