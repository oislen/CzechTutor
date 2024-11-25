package com.czechtutor.controller;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.czechtutor.model.AnswerModel;
import com.czechtutor.model.LessonModel;
import com.czechtutor.model.QuestionModel;
import com.czechtutor.model.ResultModel;
import com.czechtutor.service.AnswerService;
import com.czechtutor.service.LessonService;
import com.czechtutor.service.QuestionService;
import com.czechtutor.service.ResultService;

@Controller
public class ApplicationController {
     
    final public String czLanguage = "CZ";
    final public String enLanguage = "EN";
    final public Integer nOptions = 4;
    
    private final LessonService lessonService;
    private final QuestionService questionService;
    private final AnswerService answerService;
    private final ResultService resultService;
    
    public ApplicationController(LessonService lessonService, QuestionService questionService, AnswerService answerService, ResultService resultService) {
        this.lessonService = lessonService;
        this.questionService = questionService;
        this.answerService = answerService;
        this.resultService = resultService;
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
        model.addAttribute("lessonModel", new LessonModel());
        System.out.println(model.toString());
        return "home";
    }

    @PostMapping(value="/home")
    public String createLesson(@ModelAttribute LessonModel lessonModel) {
        System.out.println("~~~~~ Creating lesson");
        // generate a lesson
        if (lessonModel.getFromLanguage().equals(czLanguage)){
            lessonModel.setToLanguage(enLanguage);
        } else {
            lessonModel.setToLanguage(czLanguage);
        }
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
        // check NQuestions for lessonId against database
        Integer nQuestions = lessonService.get(lessonId).getNQuestions();
        Integer nLessonQuestions = questionService.findByLessonId(lessonId).size();
        if (nLessonQuestions < nQuestions) {
            System.out.println("~~~~~ Creating question.");
            // generate a question
            LessonModel lessonModel = lessonService.get(lessonId);
            QuestionModel questionModel = questionService.create(lessonModel);
            questionService.save(questionModel);
            Integer questionId = questionModel.getQuestionId();
            // redirect to view
            String path = String.valueOf(lessonId) + "/" + String.valueOf(questionId);
            String view = "/lesson/" + path;
            System.out.println(view);
            System.out.println(questionModel.getQuestionPayload());
            return "redirect:"+view;
        } else {
            System.out.println("~~~~~ Creating result.");
            // generate the results of the lesson
            ArrayList<AnswerModel> lessonAnswers = answerService.findByLessonId(lessonId);
            Integer nCorrect = resultService.countTotalCorrect(lessonAnswers);
            Float score = Float.valueOf(nCorrect) / Float.valueOf(nQuestions);
            // create a result
            ResultModel resultModel = resultService.create(lessonId, nCorrect, score);
            resultService.save(resultModel);
            // redirect to view
            String path = String.valueOf(lessonId);
            String view = "/result/" + path;
            System.out.println(view);
            System.out.println(resultModel.getResultPayload());
            return "redirect:"+view;
        }
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
        System.out.println("~~~~~ Creating result.");
        Integer nQuestions = lessonService.get(lessonId).getNQuestions();
        // create results messages
        ResultModel resultModel = resultService.findByLessonId(lessonId);
        DecimalFormat decimalFormatter = new DecimalFormat("#.##");
        decimalFormatter.setRoundingMode(RoundingMode.HALF_EVEN);
        String scoreMessage = String.valueOf(decimalFormatter.format(resultModel.getScore() * 100)) + "%";
        String nCorrectMessage = "Answered " + String.valueOf(resultModel.getNCorrect()) + " out of " + String.valueOf(nQuestions) + " questions correctly";
        String path = String.valueOf(lessonId);
        // generate combined lesson questions and answers
        ArrayList<QuestionModel> lessonQuestions = questionService.findByLessonId(lessonId);
        ArrayList<AnswerModel> lessonAnswers = answerService.findByLessonId(lessonId);
        ArrayList<HashMap<String, Object>> lessonQuestionsAnswersArray = resultService.createLessonSummary(lessonQuestions, lessonAnswers, nQuestions);
        // add attributes to model object
        model.addAttribute("scoreMessage", scoreMessage);
        model.addAttribute("nCorrectMessage", nCorrectMessage);
        model.addAttribute("lessonQuestionsAnswersArray", lessonQuestionsAnswersArray);
        model.addAttribute("path", path);
        System.out.println(model.toString());
        return "result";
    }

    @PostMapping(value="/resultHome/{lessonId}")
    public String redirectResulttoHome(@PathVariable("lessonId") Integer lessonId) {
        System.out.println("~~~~~ Redirecting home.");
        return "redirect:/home";
    }

    @PostMapping(value="/resultRedo/{lessonId}")
    public String redirectResulttoLesson(@PathVariable("lessonId") Integer lessonId) {
        System.out.println("~~~~~ Creating lesson");
        // generate a new lesson from the current lesson
        LessonModel currentLessonModel = lessonService.get(lessonId);
        LessonModel newLessonModel = new LessonModel();
        newLessonModel.setFromLanguage(currentLessonModel.getFromLanguage());
        newLessonModel.setToLanguage(currentLessonModel.getToLanguage());
        newLessonModel.setNQuestions(currentLessonModel.getNQuestions());
        newLessonModel.setNOptions(currentLessonModel.getNOptions());
        lessonService.save(newLessonModel);
        // redirect to view
        String path = String.valueOf(newLessonModel.getLessonId());
        String view = "/lesson/" + path;
        System.out.println(newLessonModel.getLessonPayload());
        return "redirect:"+view;
    }

}