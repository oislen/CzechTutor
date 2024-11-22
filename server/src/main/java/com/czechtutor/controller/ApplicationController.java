package com.czechtutor.controller;

import java.util.ArrayList;
import java.util.HashMap;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.czechtutor.model.Lesson;
import com.czechtutor.model.Question;
import com.czechtutor.service.QuizService;

@Controller
public class ApplicationController {
     
    final public String czLanguage = "CZ";
    final public String enLanguage = "EN";
    final public Integer nQuestions = 6;
    final public Integer nOptions = 4;
    final public String checkButtonChecked = "checked";
    
    private final QuizService quizService;
    
    public ApplicationController(QuizService quizService) {
        this.quizService = quizService;
    }
           
    @GetMapping(value="/")
    public String redirectIndextoHomePage() {
        System.out.println("~~~~~ Redirecting home.");
        return "redirect:/home";
    }

    @GetMapping(value="/home")
    public String getHomePage(Model model) {
        System.out.println("~~~~~ At home.");
        model.addAttribute("czLanguage", czLanguage);
        model.addAttribute("enLanguage", enLanguage);
        model.addAttribute("checkButtonChecked", checkButtonChecked);
        model.addAttribute("lessonForm", new Lesson());
        System.out.println(model.toString());
        return "home";
    }

    @PostMapping(value="/home")
    public String createQuizPayload(@ModelAttribute Lesson lesson, Model model, RedirectAttributes redirectAttrs) {
        System.out.println("~~~~~ Redirecting to lesson");
        // generate a quiz
        lesson.setNQuestions(nQuestions);
        lesson.setNOptions(nOptions);
        System.out.println(lesson.getLessonPayload());
        //ArrayList<HashMap<String, Object>> quiz = quizService.create(lesson);
        ArrayList<Question> quiz = quizService.create(lesson);
        model.addAttribute("quiz", quiz);
        System.out.println(model.toString());
        // redirect to view
        String lessonId = String.valueOf(quiz.get(0).getLessonId());
        String view = "/lesson/" + lessonId;
        System.out.println(view);
        redirectAttrs.addFlashAttribute("lesson", lesson);
        redirectAttrs.addFlashAttribute("quiz", quiz);
        return "redirect:"+view;
    }

    @GetMapping(value="/lesson/{lessonId}")
    public String getLessonPage(@PathVariable("lessonId") Integer lessonId, Model model) {
        System.out.println("~~~~~ At Lesson.");
        System.out.println(model.toString());
        return "lesson";
    }

    @PostMapping(value="/lesson/{lessonId}", consumes="application/json")
    public String createResultsPayload(@RequestBody ArrayList<HashMap<String,Object>> payload, Model model, RedirectAttributes redirectAttrs) {
        // generate quiz results
        Integer totalCorrect = quizService.countTotalCorrect(payload);
        model.addAttribute("totalCorrect", totalCorrect);
        String lessonId = payload.get(0).get("lessonId").toString();
        // redirect to view
        String view = "/lesson/" + lessonId;
        return "redirect:"+view;
    }

    @GetMapping(value="/result/{lessonId}")
    public String getResultPage(@PathVariable("lessonId") Integer lessonId, Model model) {
        model.addAttribute("lessonId", lessonId);
        System.out.println("At result.");
        return "result";
    }

    @PostMapping(value="/result/{lessonId}")
    public String redirectResulttoHome(@PathVariable("lessonId") Integer lessonId, Model model, RedirectAttributes redirectAttrs) {
        System.out.println("Redirecting home.");
        return "redirect:/home";
    }
}