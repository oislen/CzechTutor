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

    public static HashMap<String,Object> createQuestionPayload(String fromLanguage, String toLanguage, Integer questionIndex, ArrayList<Map<String, String>> recordSet){
        // randomly generate four indices to extract from the record set
        Integer upperIndexBound = recordSet.size();
        Integer nIndices = 4;
        ArrayList<Integer> indexArray = Main.randomDataIndices(nIndices, upperIndexBound);
        ArrayList<Map<String, String>> filteredRecordSet = new ArrayList<> (indexArray.stream().map(recordSet::get).collect(Collectors.toList()));
        // randomly determine the phase, answer and options
        Random phaseIndexGenerator = new Random();
        Integer phaseIndex = phaseIndexGenerator.nextInt(3);
        String phrase = filteredRecordSet.get(phaseIndex).get(fromLanguage);
        String answer = filteredRecordSet.get(phaseIndex).get(toLanguage);
        ArrayList<String> options = new ArrayList<>();
        for( Map<String, String> obj : filteredRecordSet){
            options.add(obj.get(toLanguage));
        }
        // construct question payload
        HashMap<String,Object> questionPayload = new HashMap<>();
        questionPayload.put("question", questionIndex);
        questionPayload.put("phrase", phrase);
        questionPayload.put("options", options);
        questionPayload.put("answer", answer);
        return questionPayload;
    }

    public static void main(String[] args) {
        String fromLanguage = "CZ";
        String toLanguage = "EN";
        Integer nQuestions = 2;
        // load czech / english language phrases from disk
        ArrayList<Map<String, String>> recordSet = Main.loadData("E:\\GitHub\\CzechTutor\\data\\ces-eng\\ces.txt");
        for (int i = 0; i<nQuestions; i++)
        {
            HashMap<String,Object> questionPayload = createQuestionPayload(fromLanguage, toLanguage, i, recordSet);
            System.out.println(questionPayload);
        }
    }
}    