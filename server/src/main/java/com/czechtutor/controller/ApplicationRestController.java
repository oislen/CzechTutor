package com.czechtutor.controller;

import java.util.ArrayList;
import java.util.HashMap;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.czechtutor.model.Question;
import com.czechtutor.service.Quiz;

@RestController
public class ApplicationRestController {

    final public Integer lessonId = 1;
    final public String fromLanguage = "CZ";
    final public String toLanguage = "EN";
    final public Integer nQuestions = 6;
    final public Integer nOptions = 4;

    @PostMapping("/home")
    //public Photo create(@RequestPart("data") MultipartFile file) throws IOException {
    public ArrayList<Question> createQuizPayload() {
        // create a lesson payload
        HashMap<String, Object> lessonPayload = new HashMap<>();
        lessonPayload.put("lessonId", lessonId);
        lessonPayload.put("fromLanguage", fromLanguage);
        lessonPayload.put("toLanguage", toLanguage);
        lessonPayload.put("nQuestions", nQuestions);
        lessonPayload.put("nOptions", nOptions);
        // generate a quiz
        ArrayList<Question> quiz = Quiz.create(lessonPayload);
        return quiz;
    }
}