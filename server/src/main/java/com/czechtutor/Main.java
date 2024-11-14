package com.czechtutor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

import com.czechtutor.model.Answer;
import com.czechtutor.model.Question;

public class Main {

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
        final Integer lessonId = 1;
        final String fromLanguage = "CZ";
        final String toLanguage = "EN";
        final Integer nQuestions = 2;
        ArrayList<Question> quiz = Quiz.create(lessonId, fromLanguage, toLanguage, nQuestions);
        ArrayList<HashMap<String,Object>> results = new ArrayList<>();
        try (Scanner reader = new Scanner(System.in)) {
            for (int questionIndex = 0; questionIndex<nQuestions; questionIndex++) {
                // create question payload
                Question question = quiz.get(questionIndex);
                HashMap<String,Object> questionPayload;
                questionPayload = question.getHashMap();
                System.out.println(questionPayload);
                // prompt user for answer and determine if correct
                System.out.print("Enter an answer: ");
                String input = reader.nextLine();
                // create answer payload and update results
                Answer answer = new Answer();
                questionPayload.put("answer", input);
                answer.set(questionPayload);
                results.add(answer.getHashMap());
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
