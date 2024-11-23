package com.czechtutor.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.czechtutor.model.Lesson;
import com.czechtutor.model.Question;
import com.czechtutor.repository.QuestionRepository;
import com.czechtutor.service.LessonService;
import com.czechtutor.service.QuestionService;

@Controller
public class ApplicationController {
     
    final public String czLanguage = "CZ";
    final public String enLanguage = "EN";
    final public Integer nQuestions = 6;
    final public Integer nOptions = 4;
    final public String checkButtonChecked = "checked";
    
    private final QuestionRepository questionRepository;
    private final QuestionService questionService;
    private final LessonService lessonService;
    
    public ApplicationController(LessonService lessonService, QuestionRepository questionRepository, QuestionService questionService) {
        this.questionRepository = questionRepository;
        this.questionService = questionService;
        this.lessonService = lessonService;
    }
           
    @GetMapping(value="/")
    public String redirectIndextoHomePage() {
        System.out.println("~~~~~ Redirecting home.");
        return "redirect:/home";
    }

    @GetMapping(value="/home")
    public String getHomePage(Model model, RedirectAttributes redirectAttrs) {
        System.out.println("~~~~~ At home.");
        model.addAttribute("czLanguage", czLanguage);
        model.addAttribute("enLanguage", enLanguage);
        model.addAttribute("checkButtonChecked", checkButtonChecked);
        model.addAttribute("lessonForm", new Lesson());
        System.out.println(model.toString());
        return "home";
    }

    @PostMapping(value="/home")
    public String createLesson(@ModelAttribute Lesson lesson, Model model, RedirectAttributes redirectAttrs) {
        System.out.println("~~~~~ Creating lesson");
        // generate a lesson
        lesson.setNQuestions(nQuestions);
        lesson.setNOptions(nOptions);
        lessonService.save(lesson);
        System.out.println(lesson.getLessonPayload());
        // persist model attributes
        redirectAttrs.addFlashAttribute("lesson", lesson);
        // redirect to view
        String view = "/lesson/" + String.valueOf(lesson.getLessonId());
        return "redirect:"+view;
    }

    @GetMapping(value="/lesson/{lessonId}")
    public String createQuestion(@PathVariable("lessonId") Integer lessonId, Model model, RedirectAttributes redirectAttrs) {
        System.out.println("~~~~~ Creating question.");
        // generate a question
        Lesson lesson = (Lesson) model.getAttribute("lesson");
        Question question = questionService.create(lesson);
        questionRepository.save(question);
        Integer questionId = question.getQuestionId();
        // persist model attributes
        model.addAttribute("lesson", lesson);
        model.addAttribute("question", question);
        redirectAttrs.addFlashAttribute("lesson", lesson);
        redirectAttrs.addFlashAttribute("question", question);
        System.out.println(model.toString());
        // redirect to view
        String view = "/lesson/" + String.valueOf(lessonId) + "/" + String.valueOf(questionId);
        System.out.println(view);
        return "redirect:"+view;
    }

    @GetMapping(value="/lesson/{lessonId}/{questionId}")
    public String getLessonPage(@PathVariable("lessonId") Integer lessonId, @PathVariable("questionId") Integer questionId, Model model, RedirectAttributes redirectAttrs) {
        System.out.println("~~~~~ Redirecting to lesson.");
        System.out.println(model.toString());
        return "lesson";
    }

    /*
    @PostMapping(value="/lesson/{lessonId}", consumes="application/json")
    public String createResultsPayload(@ModelAttribute Answer answer, Model model, RedirectAttributes redirectAttrs) {
        System.out.println("~~~~~ Redirecting to result");
        System.out.println(model.toString());
        // generate quiz results
        Integer totalCorrect = quizService.countTotalCorrect(answer.getAnswerPayload());
        model.addAttribute("totalCorrect", totalCorrect);
        String lessonId = payload.get(0).get("lessonId").toString();
        // redirect to view
        String view = "/result/" + lessonId;
        return "redirect:"+view;
    }
    */

    @GetMapping(value="/result/{lessonId}")
    public String getResultPage(@PathVariable("lessonId") Integer lessonId, Model model) {
        model.addAttribute("lessonId", lessonId);
        System.out.println("At result.");
        return "result";
    }

    @PostMapping(value="/result/{lessonId}")
    public String redirectResulttoHome(@PathVariable("lessonId") Integer lessonId, Model model, RedirectAttributes redirectAttrs) {
        System.out.println("~~~~~ Redirecting home.");
        return "redirect:/home";
    }
}