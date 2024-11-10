package com.czechtutor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ApplicationController {

    final public String fromLanguage = "CZ";
    final public String toLanguage = "EN";
    final public Integer nQuestions = 2;

    @GetMapping("/")
    public String hello() {
        return "Hello World";
    }

    @GetMapping("/home")
    public ArrayList<Object> home() {
        ArrayList<Object> options = new ArrayList<>(Arrays.asList(fromLanguage, toLanguage, nQuestions));
        return options;
    }

    @GetMapping("/lesson")
    public ArrayList<HashMap<String,Object>> lesson() {
        ArrayList<HashMap<String,Object>> quiz = Quiz.create(fromLanguage, toLanguage, nQuestions);
        return quiz;
    }
}