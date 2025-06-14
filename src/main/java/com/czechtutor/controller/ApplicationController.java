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
import com.czechtutor.model.custom.LessonQuestionsAnswers;
import com.czechtutor.service.AnswerService;
import com.czechtutor.service.LessonService;
import com.czechtutor.service.QuestionService;
import com.czechtutor.service.ResultService;

/**
 * <p>
 * The application controller class controls the routing of get and post
 * requests within the CzechTutor webapp</p>
 *
 * @author oislen
 */
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

    /**
     * <p>
     * Redirects any out of scope / undefined get requests to the home template
     * page</p>
     *
     * @return redirects to the home template
     */
    @GetMapping(value = "*")
    public String redirectIndextoHomePage() {
        logger.info("~~~~~ Redirecting home.");
        return "redirect:/home";
    }

    /**
     * <p>
     * Gets the home template page</p>
     *
     * @param model the Model ui object for populating the home template with
     * Thymeleaf
     * @return the home template
     */
    @GetMapping(value = "/home")
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

    /**
     * <p>
     * Gets the about template page</p>
     *
     * Thymeleaf
     * @return the about template
     */
    @GetMapping(value = "/about")
    public String getAboutPage() {
        logger.info("~~~~~ At about.");
        return "about";
    }

    /**
     * <p>
     * Gets the scores template page</p>
     *
     * Thymeleaf
     * @return the scores template
     */
    @GetMapping(value = "/scores")
    public String getScoresPage(Model model) {
        logger.info("~~~~~ Creating scores.");
        Long nLessons = lessonService.countLessons();
        // generate combined lesson questions and answers
        ArrayList<LessonModel> lessons = lessonService.getAll();
        ArrayList<ResultModel> results = resultService.getAll();
        ArrayList<HashMap<String, Object>> lessonResultsArray = resultService.createResultSummary(lessons, results, nLessons);
        // add attributes to model object
        model.addAttribute("lessonResultsArray", lessonResultsArray);
        logger.info(model.toString());
        return "scores";
    }

    /**
     * <p>
     * Posts user input from the home template page</p>
     *
     * @param lessonModel the completed lesson model form
     * @return redirects to the lesson template with the generated lesson id
     */
    @PostMapping(value = "/home")
    public String createLesson(@ModelAttribute LessonModel lessonModel) {
        logger.info("~~~~~ Creating lesson");
        // generate a lesson
        if (lessonModel.getFromLanguage().equals(czLanguage)) {
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
        return "redirect:" + view;
    }

    /**
     * <p>
     * Creates a question model for a specified lesson id</p>
     *
     * @param lessonId the generated lesson id path variable
     * @return redirects to the lesson template with the generated lesson id
     */
    @GetMapping(value = "/lesson/{lessonId}")
    public String createQuestion(@PathVariable("lessonId") Integer lessonId) {
        // check NQuestions for lessonId against database
        Integer nQuestions = lessonService.get(lessonId).getNQuestions();
        Integer nLessonQuestions = questionService.findByLessonId(lessonId).size();
        if (nLessonQuestions < nQuestions) {
            logger.info("~~~~~ Creating question.");
            // generate a question
            LessonModel lessonModel = lessonService.get(lessonId);
            QuestionModel questionModel = questionService.create(lessonModel, null);
            questionService.save(questionModel);
            Integer questionId = questionModel.getQuestionId();
            // redirect to view
            String path = String.valueOf(lessonId) + "/" + String.valueOf(questionId);
            String view = "/lesson/" + path;
            logger.info(view);
            logger.info(questionModel.getQuestionPayload());
            return "redirect:" + view;
        } else {
            logger.info("~~~~~ Creating result.");
            // define decimal formatter
            DecimalFormat decimalFormatter = new DecimalFormat("#.####");
            decimalFormatter.setRoundingMode(RoundingMode.HALF_EVEN);
            // generate the results of the lesson
            ArrayList<AnswerModel> lessonAnswers = answerService.findByLessonId(lessonId);
            Integer nCorrect = resultService.countTotalCorrect(lessonAnswers);
            Float score = Float.valueOf(decimalFormatter.format(Float.valueOf(nCorrect) / Float.valueOf(nQuestions)));
            // create a result
            ResultModel resultModel = resultService.create(lessonId, nCorrect, score);
            resultService.save(resultModel);
            // redirect to view
            String path = String.valueOf(lessonId);
            String view = "/result/" + path;
            logger.info(view);
            logger.info(resultModel.getResultPayload());
            return "redirect:" + view;
        }
    }

    /**
     * <p>
     * Gets the lesson template page for a specified lesson id and question
     * id</p>
     *
     * @param lessonId the generated lesson id path variable
     * @param questionId the generated question id path variable
     * @param model the Model ui object for populating the lesson template with
     * Thymeleaf
     * @return the lesson template for the given lesson id and question id
     */
    @GetMapping(value = "/lesson/{lessonId}/{questionId}")
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

    /**
     * <p>
     * Posts user input from the lesson template page</p>
     *
     * @param lessonId the generated lesson id path variable
     * @param questionId the generated question id path variable
     * @param answerModel the completed answer model form
     * @return redirects to the lesson template with the lesson id
     */
    @PostMapping(value = "/lesson/{lessonId}/{questionId}")
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
        return "redirect:" + view;
    }

    /**
     * <p>
     * Gets the result template page for a specified lesson id and question
     * id</p>
     *
     * @param lessonId the generated lesson id path variable
     * @param model the Model ui object for populating the result template with
     * Thymeleaf
     * @return the result template for the given lesson id
     */
    @GetMapping(value = "/result/{lessonId}")
    public String getResultPage(@PathVariable("lessonId") Integer lessonId, Model model) {
        logger.info("~~~~~ Creating result.");
        Integer nQuestions = lessonService.get(lessonId).getNQuestions();
        LessonModel lessonModel = lessonService.get(lessonId);
        // create results messages
        ResultModel resultModel = resultService.findByLessonId(lessonId);
        DecimalFormat decimalFormatter = new DecimalFormat("#.##");
        decimalFormatter.setRoundingMode(RoundingMode.HALF_EVEN);
        String scoreMessage = "Score: " + String.valueOf(decimalFormatter.format(resultModel.getScore() * 100)) + "%";
        String nCorrectMessage = "Answered " + String.valueOf(resultModel.getNCorrect()) + " out of " + String.valueOf(nQuestions) + " questions correctly";
        String path = String.valueOf(lessonId);
        // generate combined lesson questions and answers
        ArrayList<LessonQuestionsAnswers> lessonQuestionsAnswers = resultService.createLessonSummary(lessonModel);
        // add attributes to model object
        model.addAttribute("scoreMessage", scoreMessage);
        model.addAttribute("nCorrectMessage", nCorrectMessage);
        model.addAttribute("lessonQuestionsAnswers", lessonQuestionsAnswers);
        model.addAttribute("path", path);
        logger.info(model.toString());
        return "result";
    }

    /**
     * <p>
     * Posts user input from the result template page</p>
     *
     * @param lessonId the generated lesson id path variable
     * @return redirects to the home template
     */
    @PostMapping(value = "/resultHome/{lessonId}")
    public String redirectResulttoHome(@PathVariable("lessonId") Integer lessonId) {
        logger.info("~~~~~ Redirecting home.");
        return "redirect:/home";
    }

    /**
     * <p>
     * Posts user input from the result template page</p>
     *
     * @param lessonId the generated lesson id path variable
     * @return redirects to the lesson template with the same lesson
     * configurations
     */
    @PostMapping(value = "/resultRedo/{lessonId}")
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
        return "redirect:" + view;
    }

}
