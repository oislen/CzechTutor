package com.czechtutor;

import java.util.HashMap;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.czechtutor.model.LessonModel;

public class Lesson_Test {
    
    final Integer expLessonId = 1;
    final String expFromLanguage = "CZ";
    final String expToLanguage = "EN";
    final Integer expNQuestions = 2;
    LessonModel lessonModel = new LessonModel();
    
    {
        HashMap<String, Object> lessonPayload = new HashMap<>();
        lessonPayload.put("lessonId", expLessonId);
        lessonPayload.put("fromLanguage", expFromLanguage);
        lessonPayload.put("toLanguage", expToLanguage);
        lessonPayload.put("nQuestions", expNQuestions);
        lessonModel.set(lessonPayload);
    }
    

    @Test
    void LessonId_Test() {
        Integer obsLessonId = (Integer) lessonModel.getLessonPayload().get("lessonId");
        Assertions.assertEquals(expLessonId, obsLessonId);
    }

    @Test
    void FromLanguage_Test() {
        String obsFromLanguage = (String) lessonModel.getLessonPayload().get("fromLanguage");
        Assertions.assertEquals(expFromLanguage, obsFromLanguage);
    }
    
    @Test
    void ToLanguage_Test() {
        String obsToLanguage = (String) lessonModel.getLessonPayload().get("toLanguage");
        Assertions.assertEquals(expToLanguage, obsToLanguage);
    }

    @Test
    void NQuestions_Test() {
        Integer obsNQuestions = (Integer) lessonModel.getLessonPayload().get("nQuestions");
        Assertions.assertEquals(expNQuestions, obsNQuestions);
    }

}
