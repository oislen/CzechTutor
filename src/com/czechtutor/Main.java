package com.czechtutor;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Random;
import java.util.Scanner;

public class Main {

    public static ArrayList<HashMap<String, String>> loadData(String dataFilePath) {
        ArrayList<HashMap<String, String>> recordSet = new ArrayList<>();
        try (BufferedReader buf = new BufferedReader(new InputStreamReader(new FileInputStream(dataFilePath), "UTF-8"))) {
            String lineJustFetched;
            String[] strKeys = {"EN", "CZ", "REF"};
            ArrayList<String> arrayKeys = new ArrayList<>(Arrays.asList(strKeys)); 
            // iterate over file lines and transform each line into a record set
            while(true){
                lineJustFetched = buf.readLine();
                if (lineJustFetched == null) {
                    break; 
                } else {
                    String[] strSplit = lineJustFetched.split("\t");
                    ArrayList<String> arrayValues = new ArrayList<>( Arrays.asList(strSplit)); 
                    HashMap<String, String> hashMapObject = new HashMap<>();
                    for (int i = 0; i<3; i++) {
                        hashMapObject.put(arrayKeys.get(i), arrayValues.get(i));
                    }
                    recordSet.add(hashMapObject);
                }
            }
        }catch(Exception e){  
            e.printStackTrace();
        }
        return recordSet;
    }
    
    public static ArrayList<Integer> randomDataIndices(Integer nIndices, Integer upperIndexBound) {
        // randomly select nIndices between 0 and upperIndexBound
        Random indexGenerator = new Random();
        ArrayList<Integer> indexArray = new ArrayList<>();
        for (int i = 0; i<nIndices; i++) {
            Integer index = indexGenerator.nextInt(upperIndexBound);
            indexArray.add(index);
        }
        return indexArray;
    }

    public static HashMap<String,Object> createQuestionPayload(String fromLanguage, String toLanguage, Integer questionIndex, ArrayList<HashMap<String, String>> recordSet){
        // randomly generate four indices to extract from the record set
        Integer upperIndexBound = recordSet.size();
        Integer nIndices = 4;
        ArrayList<Integer> indexArray = Main.randomDataIndices(nIndices, upperIndexBound);
        //ArrayList<Map<String, String>> filteredRecordSet = new ArrayList<> (indexArray.stream().map(recordSet::get).collect(Collectors.toList()));
        ArrayList<HashMap<String, String>> filteredRecordSet = new ArrayList<>();
        for (int i = 0; i<recordSet.size(); i++) {
            if (indexArray.contains(i)) {
                filteredRecordSet.add(recordSet.get(i));
            }
        }
        // randomly determine the phase, answer and options
        Random phaseIndexGenerator = new Random();
        Integer phaseIndex = phaseIndexGenerator.nextInt(3);
        String phrase = filteredRecordSet.get(phaseIndex).get(fromLanguage);
        String solution = filteredRecordSet.get(phaseIndex).get(toLanguage);
        ArrayList<String> options = new ArrayList<>();
        for (HashMap<String, String> hashMapObject : filteredRecordSet) {
            options.add(hashMapObject.get(toLanguage));
        }
        // construct question payload
        HashMap<String,Object> questionPayload = new HashMap<>();
        questionPayload.put("question", questionIndex);
        questionPayload.put("phrase", phrase);
        questionPayload.put("options", options);
        questionPayload.put("solution", solution);
        return questionPayload;
    }

    public static Integer countTotalCorrect(ArrayList<HashMap<String,Object>> results){
        int totalCorrect = 0;
        for (HashMap<String,Object> hashMapObject : results) {
            Boolean isCorrect = (Boolean) hashMapObject.get("correct");
            if (isCorrect) {
                totalCorrect=totalCorrect+1;
            }
        }
        return totalCorrect;
    }

    public static void main(String[] args) {
        String fromLanguage = "CZ";
        String toLanguage = "EN";
        Integer nQuestions = 2;
        ArrayList<HashMap<String,Object>> results = new ArrayList<>();
        try (Scanner reader = new Scanner(System.in)) {
            // load czech / english language phrases from disk
            //ArrayList<HashMap<String, String>> recordSet = Main.loadData("E:\\GitHub\\CzechTutor\\data\\ces-eng\\ces.txt");
            ArrayList<HashMap<String, String>> recordSet = Main.loadData("E:\\GitHub\\CzechTutor\\data\\ces-eng\\ces_bkp.txt");
            for (int questionIndex = 0; questionIndex<nQuestions; questionIndex++) {
                // create question payload
                HashMap<String,Object> questionPayload = createQuestionPayload(fromLanguage, toLanguage, questionIndex, recordSet);
                System.out.println(questionPayload);
                // prompt user for answer
                System.out.println("Enter an answer: ");
                String answer = reader.nextLine();
                // update results
                questionPayload.put("answer", answer);
                questionPayload.put("correct", questionPayload.get("answer").equals(questionPayload.get("solution")));
                results.add(questionPayload);
            }
            // calculate total correct
            Integer totalCorrect = countTotalCorrect(results);
            System.out.println("Results: " + results);
            System.out.println("Total Correct Answer: " + totalCorrect);
        }catch(Exception e){  
            e.printStackTrace();
        }
    }
}    