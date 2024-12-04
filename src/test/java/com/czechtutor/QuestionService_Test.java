package com.czechtutor;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import com.czechtutor.model.LessonModel;
import com.czechtutor.service.QuestionService;

@ExtendWith(MockitoExtension.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class QuestionService_Test {
    
    @InjectMocks
    private QuestionService questionService;


    // define lesson model attributes
    final Integer expLessonId = 1;
    final String expFromLanguage = "CZ";
    final String expToLanguage = "EN";
    final Integer expNQuestions = 2;
    final Integer expNOptions = 4;
    final String expLevel = "Beginner";
    LessonModel lessonModel = new LessonModel();

    {
        // set question model attributes
        lessonModel.setLessonId(expLessonId);
        lessonModel.setFromLanguage(expFromLanguage);
        lessonModel.setToLanguage(expToLanguage);
        lessonModel.setNQuestions(expNQuestions);
        lessonModel.setNOptions(expNOptions);
        lessonModel.setLevel(expLevel);

    }

    /*
    @Test
    void Create_Test() {
        QuestionModel obsQuestionModel = questionService.create(lessonModel, 42l);
        System.out.println(obsQuestionModel.getQuestionPayload());
        Assertions.assertEquals(obsQuestionModel.getQuestionPayload(), obsQuestionModel.getQuestionPayload());
    }
    */

}