package com.czechtutor.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.czechtutor.model.LessonModel;

public class LessonModel_Test {
    
    final Integer expLessonId = 1;
    final String expFromLanguage = "CZ";
    final String expToLanguage = "EN";
    final Integer expNQuestions = 2;
    final Integer expNOptions = 4;
    final String expLevel = "Beginner";
    LessonModel lessonModel = new LessonModel();
    
    {
        lessonModel.setLessonId(expLessonId);
        lessonModel.setFromLanguage(expFromLanguage);
        lessonModel.setToLanguage(expToLanguage);
        lessonModel.setNQuestions(expNQuestions);
        lessonModel.setNOptions(expNOptions);
        lessonModel.setLevel(expLevel);
    }
    

    @Test
    void LessonId_Test() {
        Integer obsLessonId = lessonModel.getLessonId();
        Assertions.assertEquals(expLessonId, obsLessonId);
    }

    @Test
    void FromLanguage_Test() {
        String obsFromLanguage = lessonModel.getFromLanguage();
        Assertions.assertEquals(expFromLanguage, obsFromLanguage);
    }
    
    @Test
    void ToLanguage_Test() {
        String obsToLanguage = lessonModel.getToLanguage();
        Assertions.assertEquals(expToLanguage, obsToLanguage);
    }

    @Test
    void NQuestions_Test() {
        Integer obsNQuestions = lessonModel.getNQuestions();
        Assertions.assertEquals(expNQuestions, obsNQuestions);
    }

    @Test
    void NOptions_Test() {
        Integer obsNOptions = lessonModel.getNOptions();
        Assertions.assertEquals(expNOptions, obsNOptions);
    }

    @Test
    void Level_Test() {
        String obsLevel = lessonModel.getLevel();
        Assertions.assertEquals(expLevel, obsLevel);
    }
}
