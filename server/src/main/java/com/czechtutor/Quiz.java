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
        // load czech / english language phrases from disk
        ArrayList<Question> quiz = new ArrayList<>();
        ArrayList<HashMap<String, String>> recordSet = Data.load("E:\\GitHub\\CzechTutor\\server\\src\\main\\resources\\ces_bkp.txt");
        for (int questionIndex = 0; questionIndex<nQuestions; questionIndex++) {
            // create question payload
            Question question = new Question();
            question.set(lesson.getHashMap(), recordSet);
            quiz.add(question);
        }
        return quiz;

    
    }
}
