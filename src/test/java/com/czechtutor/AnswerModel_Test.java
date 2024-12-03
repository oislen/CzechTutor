package com.czechtutor;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.czechtutor.model.AnswerModel;

public class AnswerModel_Test {
    
    final Integer expAnswerId = 1;
    final Integer expLessonId = 1;
    final Integer expQuestionId = 1;
    final String expAnswer = "Hi.";
    final Boolean expCorrect = true;
    AnswerModel answerModel = new AnswerModel();
    
    {
        answerModel.setAnswerId(expAnswerId);
        answerModel.setLessonId(expLessonId);
        answerModel.setQuestionId(expQuestionId);
        answerModel.setAnswer(expAnswer);
        answerModel.setCorrect(expCorrect);
    }

    @Test
    void AnswerId_Test() {
        Integer obsAnswerId = answerModel.getAnswerId();
        Assertions.assertEquals(expAnswerId, obsAnswerId);
    }

    @Test
    void LessonId_Test() {
        Integer obsLessonId = answerModel.getLessonId();
        Assertions.assertEquals(expLessonId, obsLessonId);
    }

    @Test
    void QuestionId_Test() {
        Integer obsQuestionId = answerModel.getQuestionId();
        Assertions.assertEquals(expQuestionId, obsQuestionId);
    }
    
    @Test
    void Answer_Test() {
        String obsAnswer = answerModel.getAnswer();
        Assertions.assertEquals(expAnswer, obsAnswer);
    }

    @Test
    void correct_Test() {
        Boolean obsCorrect = answerModel.getCorrect();
        Assertions.assertEquals(expCorrect, obsCorrect);
    }
}