package com.czechtutor;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.czechtutor.model.ResultModel;

public class ResultModel_Test {
    
    final Integer expResultId = 1;
    final Integer expLessonId = 1;
    final Integer expNCorrect = 3;
    final Float expScore = 0.5f;
    ResultModel resultModel = new ResultModel();
    
    {
        resultModel.setResultId(expResultId);
        resultModel.setLessonId(expLessonId);
        resultModel.setNCorrect(expNCorrect);
        resultModel.setScore(expScore);
    }
    
    @Test
    void ResultId_Test() {
        Integer obsResultId = resultModel.getResultId();
        Assertions.assertEquals(expResultId, obsResultId);
    }

    @Test
    void LessonId_Test() {
        Integer obsLessonId = resultModel.getLessonId();
        Assertions.assertEquals(expLessonId, obsLessonId);
    }

    @Test
    void NCorrect_Test() {
        Integer obsNCorrect = resultModel.getNCorrect();
        Assertions.assertEquals(expNCorrect, obsNCorrect);
    }
   
    @Test
    void Score_Test() {
        Float obsScore = resultModel.getScore();
        Assertions.assertEquals(expScore, obsScore);
    }

}
