package com.czechtutor;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import com.czechtutor.model.AnswerModel;
import com.czechtutor.model.QuestionModel;
import com.czechtutor.service.AnswerService;

@ExtendWith(MockitoExtension.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class AnswerService_Test {
    
    @InjectMocks
    private AnswerService answerService;

    // defined shared attributes
    final Integer expQuestionId = 1;
    final Integer expLessonId = 1;

    // define question model attributes
    final String expPhrase = "Hi.";
    final String expOption1 = "Ahoj!";
    final String expOption2 = "Běž!";
    final String expOption3 = "Utíkej!";
    final String expOption4 = "Páni!";
    final String expSolution = "Ahoj!";
    QuestionModel questionModel = new QuestionModel();

    // define answer model attributes
    final Integer expAnswerId = 1;
    final String expAnswer = "Ahoj!";
    final Boolean expCorrect = true;
    AnswerModel answerModel = new AnswerModel();
    
    {
        // set question model attributes
        questionModel.setQuestionId(expQuestionId);
        questionModel.setLessonId(expLessonId);
        questionModel.setPhrase(expPhrase);
        questionModel.setOption1(expOption1);
        questionModel.setOption2(expOption2);
        questionModel.setOption3(expOption3);
        questionModel.setOption4(expOption4);
        questionModel.setSolution(expSolution);

        // set answer model attributes
        answerModel.setAnswerId(expAnswerId);
        answerModel.setLessonId(expLessonId);
        answerModel.setQuestionId(expQuestionId);
        answerModel.setAnswer(expAnswer);
        answerModel.setCorrect(expCorrect);
    }

    @Test
    void IsCorrect_Test() {
        Boolean obsIsCorrect = answerService.isCorrect(questionModel, answerModel);
        Assertions.assertEquals(expCorrect, obsIsCorrect);
    }

}