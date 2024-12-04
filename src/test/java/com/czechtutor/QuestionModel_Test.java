package com.czechtutor;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.czechtutor.model.QuestionModel;

public class QuestionModel_Test {
    
    final Integer expQuestionId = 1;
    final Integer expLessonId = 1;
    final String expPhrase = "Hi.";
    final String expOption1 = "Ahoj!";
    final String expOption2 = "Běž!";
    final String expOption3 = "Utíkej!";
    final String expOption4 = "Páni!";
    final String expSolution = "Kdo?";
    QuestionModel questionModel = new QuestionModel();
    
    {
        questionModel.setQuestionId(expQuestionId);
        questionModel.setLessonId(expLessonId);
        questionModel.setPhrase(expPhrase);
        questionModel.setOption1(expOption1);
        questionModel.setOption2(expOption2);
        questionModel.setOption3(expOption3);
        questionModel.setOption4(expOption4);
        questionModel.setSolution(expSolution);
    }
    
    @Test
    void QuestionId_Test() {
        Integer obsQuestionId = questionModel.getQuestionId();
        Assertions.assertEquals(expQuestionId, obsQuestionId);
    }

    @Test
    void LessonId_Test() {
        Integer obsLessonId = questionModel.getLessonId();
        Assertions.assertEquals(expLessonId, obsLessonId);
    }

    @Test
    void Phrase_Test() {
        String obsPhrase = questionModel.getPhrase();
        Assertions.assertEquals(expPhrase, obsPhrase);
    }
   
    @Test
    void Option1_Test() {
        String obsOption1 = questionModel.getOption1();
        Assertions.assertEquals(expOption1, obsOption1);
    }

    @Test
    void Option2_Test() {
        String obsOption2 = questionModel.getOption2();
        Assertions.assertEquals(expOption2, obsOption2);
    }

    @Test
    void Option3_Test() {
        String obsOption3 = questionModel.getOption3();
        Assertions.assertEquals(expOption3, obsOption3);
    }

    @Test
    void Option4_Test() {
        String obsOption4 = questionModel.getOption4();
        Assertions.assertEquals(expOption4, obsOption4);
    }

    @Test
    void Solution_Test() {
        String obsSolution = questionModel.getSolution();
        Assertions.assertEquals(expSolution, obsSolution);
    }

}
