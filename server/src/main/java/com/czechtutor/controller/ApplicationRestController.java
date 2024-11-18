package com.czechtutor.controller;

import java.util.ArrayList;
import java.util.HashMap;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.czechtutor.model.Question;
import com.czechtutor.service.QuizService;

@RestController
public class ApplicationRestController {

    public String fromLanguage = "CZ";
    public String toLanguage = "EN";
    final public Integer nQuestions = 6;
    final public Integer nOptions = 4;

    private final QuizService quizService;

    public ApplicationRestController(QuizService quizService) {
        this.quizService = quizService;
    }

    @PostMapping("/home")
    //public Photo create(@RequestPart("data") MultipartFile file) throws IOException {
    public ArrayList<Question> createQuizPayload() {
        // create a lesson payload
        HashMap<String, Object> lessonPayload = new HashMap<>();
        lessonPayload.put("fromLanguage", fromLanguage);
        lessonPayload.put("toLanguage", toLanguage);
        lessonPayload.put("nQuestions", nQuestions);
        lessonPayload.put("nOptions", nOptions);
        // generate a quiz
        ArrayList<Question> quiz = quizService.create(lessonPayload);
        return quiz;
    }
}