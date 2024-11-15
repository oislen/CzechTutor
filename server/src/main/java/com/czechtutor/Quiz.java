package com.czechtutor;

import java.util.ArrayList;
import java.util.HashMap;

import com.czechtutor.model.Lesson;
import com.czechtutor.model.Question;

public class Quiz {

    public static ArrayList<Question> create(Integer lessonId, String fromLanguage, String toLanguage, Integer nQuestions, Integer nOptions) {
        // create a lesson payload to pass to lesson class
        HashMap<String, Object> lessonPayload = new HashMap<>();
        lessonPayload.put("lessonId", lessonId);
        lessonPayload.put("fromLanguage", fromLanguage);
        lessonPayload.put("toLanguage", toLanguage);
        lessonPayload.put("nQuestions", nQuestions);
        lessonPayload.put("nOptions", nOptions);
        Lesson lesson = new Lesson();
        lesson.set(lessonPayload);
        // create an array for holding the questions
        ArrayList<Question> quiz = new ArrayList<>();
        // set the initial questionId
        Integer questionId = 1;
        // load czech / english language phrases from disk
        ArrayList<HashMap<String, String>> recordSet = Data.load("E:\\GitHub\\CzechTutor\\server\\src\\main\\resources\\ces_bkp.txt");
        for (int questionSubId = 1; questionSubId<=nQuestions; questionSubId++) {
            // create question payload
            HashMap<String, Object> questionPayload;
            questionPayload = lesson.getHashMap();
            questionPayload.put("questionId", questionId);
            questionPayload.put("questionSubId", questionSubId);
            // set question
            Question question = new Question();
            question.set(questionPayload, recordSet);
            quiz.add(question);
            // increment questionId
            questionId++;
        }
        return quiz;

    
    }
}
