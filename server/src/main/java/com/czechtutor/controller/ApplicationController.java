package com.czechtutor.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ApplicationController {

    final public Integer lessonId = 1;
    public String fromLanguage = "CZ";
    public String toLanguage = "EN";
    final public Integer nQuestions = 6;
    final public Integer nOptions = 4;
    
    @GetMapping("/home")
    public String getHomePage() {
        return "home";
    }

}