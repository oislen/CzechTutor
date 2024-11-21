package com.czechtutor.controller;

import java.util.ArrayList;
import java.util.HashMap;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.czechtutor.service.QuizService;

@Controller
public class ApplicationController {
     
    final public Integer nQuestions = 6;
    final public Integer nOptions = 4;
    
    private final QuizService quizService;
    
    public ApplicationController(QuizService quizService) {
        this.quizService = quizService;
    }
           
    @GetMapping(value="/")
    public String redirectIndextoHomePage() {
        System.out.println("Redirecting home.");
        return "redirect:/home";
    }

    @GetMapping(value="/home")
    public String getHomePage(Model model) {
        model.addAttribute("CZ", "CZ");
        model.addAttribute("EN", "EN");
        System.out.println("At home.");
        return "home";
    }

    @PostMapping(value="/home", consumes="application/json")
    public String createQuizPayload(@RequestBody HashMap<String, Object> payload, Model model) {
        // generate a quiz
        payload.put("nQuestions", nQuestions);
        payload.put("nOptions", nOptions);
        ArrayList<HashMap<String, Object>> quiz = quizService.create(payload);
        model.addAttribute("quiz", quiz);
        String lessonId = quiz.get(0).get("lessonId").toString();
        // redirect to view
        System.out.println("Redirecting to lesson");
        String view = "/lesson/" + lessonId;
        return "redirect:"+view;
    }

    @GetMapping(value="/lesson/{lessonId}")
    public String getLessonPage(@PathVariable("lessonId") Integer lessonId, Model model) {
        System.out.println("At Lesson.");
        return "lesson";
    }

    @PostMapping(value="/lesson/{lessonId}", consumes="application/json")
    public Integer createResultsPayload(@RequestBody ArrayList<HashMap<String,Object>> payload) {
        // calculate results
        Integer totalCorrect = quizService.countTotalCorrect(payload);
        return totalCorrect;
    }

    @GetMapping(value="/result/{lessonId}")
    public String getResultPage(@PathVariable("lessonId") Integer lessonId, Model model) {
        System.out.println("At result.");
        return "result";
    }

    @PostMapping(value="/result/{lessonId}")
    public String redirectResulttoHome(@PathVariable("lessonId") Integer lessonId, Model model) {
        System.out.println("Redirecting home.");
        return "redirect:/home";
    }
}