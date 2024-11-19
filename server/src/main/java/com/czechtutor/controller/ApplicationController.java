package com.czechtutor.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ApplicationController {
    
    @GetMapping("/home")
    public String getHomePage() {
        return "home";
    }

    @GetMapping("/lesson")
    public String getLessonPage() {
        return "lesson";
    }

    @GetMapping("/results")
    public String getResultsPage() {
        return "results";
    }

}