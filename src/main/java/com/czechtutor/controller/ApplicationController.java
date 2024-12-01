package com.czechtutor.controller;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
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
    
    private static final Logger logger = LogManager.getLogger(ApplicationController.class);
    final public String czLanguage = "czech";
    final public String enLanguage = "english";
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
           
    @GetMapping(value="*")
    public String redirectIndextoHomePage() {
        logger.info("~~~~~ Redirecting home.");
        return "redirect:/home";
    }

    @GetMapping(value="/home")
    public String getHomePage(Model model) {
        // set difficulty levels
        ArrayList<String> levels = new ArrayList<>();
        levels.add("Beginner");
        levels.add("Easy");
        levels.add("Medium");
        levels.add("Hard");
        levels.add("Expert");
        logger.info("~~~~~ At home.");
        model.addAttribute("czLanguage", czLanguage);
        model.addAttribute("enLanguage", enLanguage);
        model.addAttribute("levels", levels);
        model.addAttribute("lessonModel", new LessonModel());
        logger.info(model.toString());
        return "home";
    }

    @PostMapping(value="/home")
    public String createLesson(@ModelAttribute LessonModel lessonModel) {
        logger.info("~~~~~ Creating lesson");
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
        logger.info(lessonModel.getLessonPayload());
        return "redirect:"+view;
    }

    @GetMapping(value="/lesson/{lessonId}")
    public String createQuestion(@PathVariable("lessonId") Integer lessonId) {
        // check NQuestions for lessonId against database
        Integer nQuestions = lessonService.get(lessonId).getNQuestions();
        Integer nLessonQuestions = questionService.findByLessonId(lessonId).size();
        if (nLessonQuestions < nQuestions) {
            logger.info("~~~~~ Creating question.");
            // generate a question
            LessonModel lessonModel = lessonService.get(lessonId);
            QuestionModel questionModel = questionService.create(lessonModel);
            questionService.save(questionModel);
            Integer questionId = questionModel.getQuestionId();
            // redirect to view
            String path = String.valueOf(lessonId) + "/" + String.valueOf(questionId);
            String view = "/lesson/" + path;
            logger.info(view);
            logger.info(questionModel.getQuestionPayload());
            return "redirect:"+view;
        } else {
            logger.info("~~~~~ Creating result.");
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
            logger.info(view);
            logger.info(resultModel.getResultPayload());
            return "redirect:"+view;
        }
    }

    @GetMapping(value="/lesson/{lessonId}/{questionId}")
    public String getLessonPage(@PathVariable("lessonId") Integer lessonId, @PathVariable("questionId") Integer questionId, Model model) {
        logger.info("~~~~~ Redirecting to lesson.");
        QuestionModel questionModel = questionService.get(questionId);
        String path = String.valueOf(lessonId) + "/" + String.valueOf(questionId);
        model.addAttribute("questionModel", questionModel);
        model.addAttribute("answerModel", new AnswerModel());
        model.addAttribute("path", path);
        logger.info(model.toString());
        return "lesson";
    }

    @PostMapping(value="/lesson/{lessonId}/{questionId}")
    public String createAnswer(@PathVariable("lessonId") Integer lessonId, @PathVariable("questionId") Integer questionId, @ModelAttribute AnswerModel answerModel) {
        logger.info("~~~~~ Creating answer");
        // generate a answer
        QuestionModel questionModel = questionService.get(questionId);
        answerModel.setCorrect(answerService.isCorrect(questionModel, answerModel));
        answerService.save(answerModel);
        // redirect to view
        String path = String.valueOf(lessonId);
        String view = "/lesson/" + path;
        logger.info(view);
        logger.info(answerModel.getAnswerPayload());
        return "redirect:"+view;
    }

    @GetMapping(value="/result/{lessonId}")
    public String getResultPage(@PathVariable("lessonId") Integer lessonId, Model model) {
        logger.info("~~~~~ Creating result.");
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
        logger.info(model.toString());
        return "result";
    }

    @PostMapping(value="/resultHome/{lessonId}")
    public String redirectResulttoHome(@PathVariable("lessonId") Integer lessonId) {
        logger.info("~~~~~ Redirecting home.");
        return "redirect:/home";
    }

    @PostMapping(value="/resultRedo/{lessonId}")
    public String redirectResulttoLesson(@PathVariable("lessonId") Integer lessonId) {
        logger.info("~~~~~ Creating lesson");
        // generate a new lesson from the current lesson
        LessonModel currentLessonModel = lessonService.get(lessonId);
        LessonModel newLessonModel = new LessonModel();
        newLessonModel.setFromLanguage(currentLessonModel.getFromLanguage());
        newLessonModel.setToLanguage(currentLessonModel.getToLanguage());
        newLessonModel.setNQuestions(currentLessonModel.getNQuestions());
        newLessonModel.setNOptions(currentLessonModel.getNOptions());
        newLessonModel.setLevel(currentLessonModel.getLevel());
        lessonService.save(newLessonModel);
        // redirect to view
        String path = String.valueOf(newLessonModel.getLessonId());
        String view = "/lesson/" + path;
        logger.info(newLessonModel.getLessonPayload());
        return "redirect:"+view;
    }

}