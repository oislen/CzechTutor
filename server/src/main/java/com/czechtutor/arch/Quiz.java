package com.czechtutor.arch;

import java.util.ArrayList;
import java.util.HashMap;

import com.czechtutor.model.Lesson;
import com.czechtutor.arch.Question;
import com.czechtutor.service.DataService;

public class Quiz {

    public static ArrayList<Question> create(HashMap<String, Object> payload) {
        Lesson lesson = new Lesson();
        lesson.set(payload);
        // create an array for holding the questions
        ArrayList<Question> quiz = new ArrayList<>();
        // set the initial questionId
        Integer questionId = 1;
        // load czech / english language phrases from disk
        ArrayList<HashMap<String, String>> recordSet = DataService.load("E:\\GitHub\\CzechTutor\\server\\src\\main\\resources\\data\\ces_bkp.txt");
        for (int questionSubId = 1; questionSubId<=lesson.getNQuestions(); questionSubId++) {
            // create question payload
            HashMap<String, Object> lessonPayload;
            lessonPayload = lesson.getLessonPayload();
            lessonPayload.put("questionId", questionId);
            lessonPayload.put("questionSubId", questionSubId);
            // set question
            Question question = new Question();
            question.set(lessonPayload, recordSet);
            quiz.add(question);
            // increment questionId
            questionId++;
        }
        return quiz;

    
    }
}