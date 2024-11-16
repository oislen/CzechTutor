package com.czechtutor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

import com.czechtutor.model.Answer;
import com.czechtutor.model.Question;
import com.czechtutor.service.Quiz;

public class Main {

    public static Integer countTotalCorrect(ArrayList<HashMap<String,Object>> results){
        int totalCorrect = 0;
        for (HashMap<String,Object> hashMapObject : results) {
            Boolean isCorrect = (Boolean) hashMapObject.get("correct");
            if (isCorrect) {
                totalCorrect++;
            }
        }
        return totalCorrect;
    }

    public static void main(String[] args) {
		// default settings (from client side)
        final Integer lessonId = 1;
        String fromLanguage = "CZ";
        String toLanguage = "EN";
        final Integer nQuestions = 5;
        final Integer nOptions = 4;
        // create a lesson payload
        HashMap<String, Object> lessonPayload = new HashMap<>();
        lessonPayload.put("lessonId", lessonId);
        lessonPayload.put("fromLanguage", fromLanguage);
        lessonPayload.put("toLanguage", toLanguage);
        lessonPayload.put("nQuestions", nQuestions);
        lessonPayload.put("nOptions", nOptions);
        // generate a quiz
        ArrayList<Question> quiz = Quiz.create(lessonPayload);
        ArrayList<HashMap<String,Object>> results = new ArrayList<>();
        try (Scanner reader = new Scanner(System.in)) {
            for (int questionIndex = 0; questionIndex<nQuestions; questionIndex++) {
                // create question payload
                Question question = quiz.get(questionIndex);
                HashMap<String,Object> questionPayload;
                questionPayload = question.getQuestionPayload();
                System.out.println(questionPayload);
                // prompt user for answer and determine if correct
                System.out.print("Enter an answer: ");
                String input = reader.nextLine();
                // create answer payload and update results
                Answer answer = new Answer();
                questionPayload.put("answer", input);
                answer.set(questionPayload);
                results.add(answer.getAnswerPayload());
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
