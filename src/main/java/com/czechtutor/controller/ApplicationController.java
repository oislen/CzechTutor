package com.czechtutor.controller;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;

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
import com.czechtutor.model.custom.LessonQuestionAnswer;
import com.czechtutor.model.custom.LessonResult;
import com.czechtutor.service.AnswerService;
import com.czechtutor.service.LessonService;
import com.czechtutor.service.QuestionService;
import com.czechtutor.service.ResultService;
import com.czechtutor.service.custom.LessonQuestionAnswerService;
import com.czechtutor.service.custom.LessonResultService;
import com.czechtutor.service.custom.UtilityService;

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
    private final LessonQuestionAnswerService lessonQuestionAnswerService;
    private final LessonResultService lessonResultService;
    private final UtilityService utilityService;

    public ApplicationController(LessonService lessonService, QuestionService questionService, AnswerService answerService, ResultService resultService, LessonQuestionAnswerService lessonQuestionAnswerService, LessonResultService lessonResultService, UtilityService utilityService) {
        this.lessonService = lessonService;
        this.questionService = questionService;
        this.answerService = answerService;
        this.resultService = resultService;
        this.lessonQuestionAnswerService = lessonQuestionAnswerService;
        this.lessonResultService = lessonResultService;
        this.utilityService = utilityService;
    }

    /**
     * <p>
     * Creates a question model for a specified lesson id</p>
     *
     * @param lessonId the lesson id to create a new question model for
     * @return redirects to the lesson template with the generated lesson id
     */
    public String createLessonQuestion(Integer lessonId) {
        logger.info("~~~~~ Creating lesson question.");
        // generate a question
        LessonModel lessonModel = lessonService.get(lessonId);
        QuestionModel questionModel = questionService.create(lessonModel, null);
        questionService.save(questionModel);
        Integer lessonQuestionId = questionModel.getLessonQuestionId();
        // redirect to view
        String path = String.valueOf(lessonId) + "/" + String.valueOf(lessonQuestionId);
        String view = "/lesson/" + path;
        logger.info(view);
        logger.info(questionModel.getQuestionPayload());
        return "redirect:" + view;
    }

    /**
     * <p>
     * Creates a result for a completed lesson id</p>
     *
     * @param lessonId the lesson id to create a result model for
     * @return redirects to the result template with the generated result model
     */
    public String createLessonResults(Integer lessonId) {
        logger.info("~~~~~ Creating lesson result.");
        // pull nQuestions from lesson model
        Integer nQuestions = lessonService.get(lessonId).getNQuestions();
        // define decimal formatter
        DecimalFormat decimalFormatter = new DecimalFormat("#.##");
        decimalFormatter.setRoundingMode(RoundingMode.HALF_EVEN);
        // generate the results of the lesson
        ArrayList<AnswerModel> lessonAnswers = answerService.findByLessonId(lessonId);
        Integer nCorrect = resultService.countTotalCorrect(lessonAnswers);
        Float score = Float.valueOf(decimalFormatter.format(Float.valueOf(nCorrect) / Float.valueOf(nQuestions) * 100));
        // create a result
        ResultModel resultModel = resultService.create(lessonId, nCorrect, score);
        // check if an existing results model already exists for the lesson id
        if (resultService.existsByLessonId(lessonId)) {
            // overwrite the result id with the result id from the result model corresponding to the existing lesson id
            resultModel.setResultId(resultService.findByLessonId(lessonId).getResultId());
        }
        resultService.save(resultModel);
        // redirect to view
        String path = String.valueOf(lessonId);
        String view = "/result/" + path;
        logger.info(view);
        logger.info(resultModel.getResultPayload());
        return "redirect:" + view;
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
        // generate summary of all lessons and results
        ArrayList<LessonResult> lessonsResults = lessonResultService.createResultSummary();
        // add attributes to model object
        model.addAttribute("lessonsResults", lessonsResults);
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
        lessonModel.setDateTime(LocalDateTime.now());
        lessonModel.setDateTimeHash(utilityService.MD5DateTimeHash(lessonModel.getDateTime()));
        lessonService.save(lessonModel);
        // redirect to view
        logger.info(lessonModel.getLessonPayload());
        return createLessonQuestion(lessonModel.getLessonId());
    }

    /**
     * <p>
     * Gets the lesson template page for a specified lesson id and question
     * id</p>
     *
     * @param lessonId the generated lesson id path variable
     * @param lessonQuestionId the generated lesson question id path variable
     * @param model the Model ui object for populating the lesson template with
     * Thymeleaf
     * @return the lesson template for the given lesson id and question id
     */
    @GetMapping(value = "/lesson/{lessonId}/{lessonQuestionId}")
    public String getLessonPage(@PathVariable("lessonId") Integer lessonId, @PathVariable("lessonQuestionId") Integer lessonQuestionId, Model model) {
        logger.info("~~~~~ Redirecting to lesson.");
        // pull lesson model for lesson id and lesson question id
        QuestionModel questionModel = questionService.getQuestionByLessonQuestionIds(lessonId, lessonQuestionId);
        String path = String.valueOf(lessonId) + "/" + String.valueOf(lessonQuestionId);
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
     * @param lessonQuestionId the generated lesson question id path variable
     * @param answerModel the completed answer model form
     * @return redirects to the lesson template with the lesson id
     */
    @PostMapping(value = "/lesson/{lessonId}/{lessonQuestionId}")
    public String createAnswer(@PathVariable("lessonId") Integer lessonId, @PathVariable("lessonQuestionId") Integer lessonQuestionId, @ModelAttribute AnswerModel answerModel) {
        logger.info("~~~~~ Creating answer");
        // pull lesson model for lesson id and lesson question id
        QuestionModel questionModel = questionService.getQuestionByLessonQuestionIds(lessonId, lessonQuestionId);
        // generate a answer
        answerModel.setQuestionId(questionModel.getQuestionId());
        answerModel.setCorrect(answerService.isCorrect(questionModel, answerModel));
        answerModel.setDateTime(LocalDateTime.now());
        answerModel.setDateTimeHash(utilityService.MD5DateTimeHash(answerModel.getDateTime()));
        // check if answer already exists for the question id
        if (answerService.existsByQuestionId(questionModel.getQuestionId())) {
            // if ot does assign answer id and overwrite existing result
            answerModel.setAnswerId(answerService.findByQuestionId(questionModel.getQuestionId()).getAnswerId());
        }
        answerService.save(answerModel);
        logger.info(answerModel.getAnswerPayload());
        // check NQuestions for lessonId against database
        Integer nQuestions = lessonService.get(lessonId).getNQuestions();
        Integer nLessonQuestions = questionService.findByLessonId(lessonId).size();
        // if the lesson question id is greater than the number of available questions, and also less than the total required questions
        if (lessonQuestionId >= nLessonQuestions && lessonQuestionId < nQuestions) {
            //  create a new question for the lesson
            return createLessonQuestion(lessonId);
        // otherwise if the lesson question id is less than the number of available question, and also less the total required questions
        } else if (lessonQuestionId < nQuestions && lessonQuestionId < nQuestions) {
            logger.info("~~~~~ Redirecting to next question.");
            // redirect to the the new question in the lesson view
            Integer nextLessonQuestionId = lessonQuestionId + 1;
            QuestionModel nextQuestionModel = questionService.getQuestionByLessonQuestionIds(lessonId, nextLessonQuestionId);
            // redirect to view
            String path = String.valueOf(lessonId) + "/" + String.valueOf(lessonQuestionId + 1);
            String view = "/lesson/" + path;
            logger.info(view);
            logger.info(nextQuestionModel.getQuestionPayload());
            return "redirect:" + view;
        // otherwise all questions have been completed and redirect to results view
        } else {
            // generate the lesson results
            return createLessonResults(lessonId);
        }
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
        String scoreMessage = "This lesson is incomplete.";
        String nCorrectMessage = "Complete using 'Retry Lesson' below.";
        if (resultService.existsByLessonId(lessonId)) {
            scoreMessage = "Score: " + String.valueOf(resultModel.getScore()) + "%";
            nCorrectMessage = "Answered " + String.valueOf(resultModel.getNCorrect()) + " out of " + String.valueOf(nQuestions) + " questions correctly";
        }
        String path = String.valueOf(lessonId);
        // generate combined lesson questions and answers
        ArrayList<LessonQuestionAnswer> lessonQuestionsAnswers = lessonQuestionAnswerService.createLessonSummary(lessonModel);
        // map solution to null if answers have not been provided
        for (LessonQuestionAnswer lessonQuestionAnswer : lessonQuestionsAnswers) {
            if (lessonQuestionAnswer.getAnswerId() == null) {
                lessonQuestionAnswer.setSolution(null);
            }
        }
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
     * @return redirects to the lesson template with the same lesson
     * configurations
     */
    @PostMapping(value = "/resultNew/{lessonId}")
    public String redirectResultToNewLesson(@PathVariable("lessonId") Integer lessonId) {
        logger.info("~~~~~ Creating new lesson");
        // generate a new lesson from the current lesson
        LessonModel currentLessonModel = lessonService.get(lessonId);
        LessonModel newLessonModel = new LessonModel();
        newLessonModel.setFromLanguage(currentLessonModel.getFromLanguage());
        newLessonModel.setToLanguage(currentLessonModel.getToLanguage());
        newLessonModel.setNQuestions(currentLessonModel.getNQuestions());
        newLessonModel.setNOptions(currentLessonModel.getNOptions());
        newLessonModel.setLevel(currentLessonModel.getLevel());
        newLessonModel.setDateTime(LocalDateTime.now());
        newLessonModel.setDateTimeHash(utilityService.MD5DateTimeHash(newLessonModel.getDateTime()));
        lessonService.save(newLessonModel);
        // redirect to view
        logger.info(newLessonModel.getLessonPayload());
        return createLessonQuestion(newLessonModel.getLessonId());
    }

    /**
     * <p>
     * Posts user input from the result template page</p>
     *
     * @param lessonId the generated lesson id path variable
     * @return redirects to the lesson template with the same lesson
     * configurations
     */
    @PostMapping(value = "/resultRetry/{lessonId}")
    public String redirectResultToRetryLesson(@PathVariable("lessonId") Integer lessonId) {
        logger.info("~~~~~ Retrying lesson");
        // generate a new lesson from the current lesson
        LessonModel currentLessonModel = lessonService.get(lessonId);
        // redirect to view
        String path = String.valueOf(lessonId) + "/1";
        String view = "/lesson/" + path;
        logger.info(currentLessonModel.getLessonPayload());
        return "redirect:" + view;
    }

}
