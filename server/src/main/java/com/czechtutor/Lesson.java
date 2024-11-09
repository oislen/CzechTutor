package com.czechtutor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Lesson {

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
        final String fromLanguage = "CZ";
        final String toLanguage = "EN";
        final Integer nQuestions = 2;
        ArrayList<HashMap<String,Object>> results = new ArrayList<>();
        try (Scanner reader = new Scanner(System.in)) {
            // load czech / english language phrases from disk
            //ArrayList<HashMap<String, String>> recordSet = Main.loadData("E:\\GitHub\\CzechTutor\\data\\ces-eng\\ces.txt");
            ArrayList<HashMap<String, String>> recordSet = Data.load("E:\\GitHub\\CzechTutor\\server\\src\\main\\resources\\ces_bkp.txt");
            for (int questionIndex = 0; questionIndex<nQuestions; questionIndex++) {
                // create question payload
                HashMap<String,Object> questionPayload = Question.create(fromLanguage, toLanguage, questionIndex, recordSet);
                // TODO: println question phrase with question index
                System.out.println(questionPayload);
                // prompt user for answer and determine if correct
                System.out.print("Enter an answer: ");
                String answer = reader.nextLine();
                // create answer payload and update results
                HashMap<String,Object> answerPayload = Answer.create(questionPayload, answer);
                results.add(answerPayload);
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