package com.czechtutor.controller;

import java.util.ArrayList;
import java.util.HashMap;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.czechtutor.model.Question;
import com.czechtutor.service.QuizService;

@RestController
public class ApplicationRestController {
    
    final public Integer nQuestions = 6;
    final public Integer nOptions = 4;
    
    private final QuizService quizService;
    
    public ApplicationRestController(QuizService quizService) {
        this.quizService = quizService;
    }
    
    @PostMapping("/home")
    public ArrayList<Question> createQuizPayload(@RequestBody HashMap<String, Object> payload) {
        payload.put("nQuestions", nQuestions);
        payload.put("nOptions", nOptions);
        // generate a quiz
        ArrayList<Question> quiz = quizService.create(payload);
        return quiz;
    }
}