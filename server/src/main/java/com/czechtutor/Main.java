package com.czechtutor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        final String fromLanguage = "CZ";
        final String toLanguage = "EN";
        final Integer nQuestions = 2;
        ArrayList<HashMap<String,Object>> results = new ArrayList<>();
        try (Scanner reader = new Scanner(System.in)) {
            // load czech / english language phrases from disk
            //ArrayList<HashMap<String, String>> recordSet = Main.loadData("E:\\GitHub\\CzechTutor\\data\\ces-eng\\ces.txt");
            ArrayList<HashMap<String, String>> recordSet = Data.load("E:\\GitHub\\CzechTutor\\src\\main\\resources\\ces_bkp.txt");
            for (int questionIndex = 0; questionIndex<nQuestions; questionIndex++) {
                // create question payload
                HashMap<String,Object> questionPayload = Payload.create(fromLanguage, toLanguage, questionIndex, recordSet);
                System.out.println(questionPayload);
                // prompt user for answer and determine if correct
                System.out.print("Enter an answer: ");
                String answer = reader.nextLine();
                Boolean correct = answer.toLowerCase().trim().equals(questionPayload.get("solution").toString().toLowerCase().trim());
                // create results payload and update results
                HashMap<String,Object> answerPayload = new HashMap<>(questionPayload);
                answerPayload.put("answer", answer);
                answerPayload.put("correct", correct);
                answerPayload.remove("options");
                results.add(answerPayload);
            }
            // calculate total correct
            Integer totalCorrect = Evaluate.countTotalCorrect(results);
            System.out.println("Results: " + results);
            System.out.println("Total Correct Answer: " + totalCorrect);
        }catch(Exception e){  
            e.printStackTrace();
        }
    }
}    