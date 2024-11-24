package com.czechtutor.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.czechtutor.model.AnswerModel;
import com.czechtutor.model.LessonModel;
import com.czechtutor.model.QuestionModel;
import com.czechtutor.service.AnswerService;
import com.czechtutor.service.LessonService;
import com.czechtutor.service.QuestionService;

@Controller
public class ApplicationController {
     
    final public String czLanguage = "CZ";
    final public String enLanguage = "EN";
    final public Integer nQuestions = 6;
    final public Integer nOptions = 4;
    final public String checkButtonChecked = "checked";
    
    private final LessonService lessonService;
    private final QuestionService questionService;
    private final AnswerService answerService;
    
    public ApplicationController(LessonService lessonService, QuestionService questionService, AnswerService answerService) {
        this.lessonService = lessonService;
        this.questionService = questionService;
        this.answerService = answerService;
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
        model.addAttribute("lessonModel", new LessonModel());
        System.out.println(model.toString());
        return "home";
    }

    @PostMapping(value="/home")
    public String createLesson(@ModelAttribute LessonModel lessonModel) {
        System.out.println("~~~~~ Creating lesson");
        // generate a lesson
        lessonModel.setNQuestions(nQuestions);
        lessonModel.setNOptions(nOptions);
        lessonService.save(lessonModel);
        // redirect to view
        String path = String.valueOf(lessonModel.getLessonId());
        String view = "/lesson/" + path;
        System.out.println(lessonModel.getLessonPayload());
        return "redirect:"+view;
    }

    @GetMapping(value="/lesson/{lessonId}")
    public String createQuestion(@PathVariable("lessonId") Integer lessonId) {
        System.out.println("~~~~~ Creating question.");
        // generate a question
        LessonModel lessonModel = lessonService.get(lessonId);
        //Integer nOptions = lessonModel.getNOptions();
        QuestionModel questionModel = questionService.create(lessonModel);
        questionService.save(questionModel);
        Integer questionId = questionModel.getQuestionId();
        // redirect to view
        String path = String.valueOf(lessonId) + "/" + String.valueOf(questionId);
        String view = "/lesson/" + path;
        System.out.println(view);
        System.out.println(questionModel.getQuestionPayload());
        return "redirect:"+view;
    }

    @GetMapping(value="/lesson/{lessonId}/{questionId}")
    public String getLessonPage(@PathVariable("lessonId") Integer lessonId, @PathVariable("questionId") Integer questionId, Model model) {
        System.out.println("~~~~~ Redirecting to lesson.");
        QuestionModel questionModel = questionService.get(questionId);
        String path = String.valueOf(lessonId) + "/" + String.valueOf(questionId);
        model.addAttribute("questionModel", questionModel);
        model.addAttribute("answerModel", new AnswerModel());
        model.addAttribute("path", path);
        System.out.println(model.toString());
        return "lesson";
    }

    @PostMapping(value="/lesson/{lessonId}/{questionId}")
    public String createAnswer(@PathVariable("lessonId") Integer lessonId, @PathVariable("questionId") Integer questionId, @ModelAttribute AnswerModel answerModel) {
        System.out.println("~~~~~ Creating answer");
        // generate a answer
        QuestionModel questionModel = questionService.get(questionId);
        answerModel.setCorrect(answerService.isCorrect(questionModel, answerModel));
        answerService.save(answerModel);
        // redirect to view
        String path = String.valueOf(lessonId);
        String view = "/lesson/" + path;
        System.out.println(view);
        System.out.println(answerModel.getAnswerPayload());
        return "redirect:"+view;
    }

    @GetMapping(value="/result/{lessonId}")
    public String getResultPage(@PathVariable("lessonId") Integer lessonId, Model model) {
        model.addAttribute("lessonId", lessonId);
        System.out.println("At result.");
        return "result";
    }

    @PostMapping(value="/result/{lessonId}")
    public String redirectResulttoHome(@PathVariable("lessonId") Integer lessonId, Model model) {
        System.out.println("~~~~~ Redirecting home.");
        return "redirect:/home";
    }
}