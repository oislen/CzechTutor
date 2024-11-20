package com.czechtutor.controller;

import java.util.ArrayList;
import java.util.HashMap;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.czechtutor.model.Question;
import com.czechtutor.service.QuizService;

@Controller
public class ApplicationController {
     
    final public Integer nQuestions = 6;
    final public Integer nOptions = 4;
    
    private final QuizService quizService;
    
    public ApplicationController(QuizService quizService) {
        this.quizService = quizService;
    }
           
    @GetMapping(path="/")
    public String redirectIndextoHomePage() {
        return "redirect:/home";
    }

    @GetMapping(path="/home")
    public String getHomePage(Model model) {
        model.addAttribute("CZ", "CZ");
        model.addAttribute("EN", "EN");
        return "home";
    }

    @PostMapping(path="/home", consumes="application/json")
    @ResponseBody
    public ArrayList<Question> createQuizPayload(@RequestBody HashMap<String, Object> payload) {
        payload.put("nQuestions", nQuestions);
        payload.put("nOptions", nOptions);
        // generate a quiz
        ArrayList<Question> quiz = quizService.create(payload);
        return quiz;
    }

    @GetMapping(path="/lesson")
    public String getLessonPage() {
        return "lesson";
    }

    @PostMapping(path="/lesson", consumes="application/json")
    @ResponseBody
    public Integer createResultsPayload(@RequestBody ArrayList<HashMap<String,Object>> payload) {
        // calculate results
        Integer totalCorrect = quizService.countTotalCorrect(payload);
        return totalCorrect;
    }

    @GetMapping(path="/results")
    public String getResultsPage() {
        return "results";
    }

    @PostMapping(path="/results")
    public String redirectResultstoHome() {
        return "redirect:/home";
    }
}