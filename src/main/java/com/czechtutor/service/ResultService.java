package com.czechtutor.service;

import java.util.ArrayList;
import java.util.HashMap;

import org.springframework.stereotype.Service;

import com.czechtutor.model.AnswerModel;
import com.czechtutor.model.LessonModel;
import com.czechtutor.model.ResultModel;
import com.czechtutor.model.custom.LessonQuestionsAnswers;
import com.czechtutor.repository.crud.ResultCrudRepository;

/**
 * <p>
 * The results service class defines the available processes for generating and
 * modifying the results model</p>
 *
 * @author oislen
 */
@Service
public class ResultService {

    private final ResultCrudRepository resultCrudRepository;

    public ResultService(ResultCrudRepository resultCrudRepository) {
        this.resultCrudRepository = resultCrudRepository;
    }

    /**
     * <p>
     * Finds a result model given a result id</p>
     *
     * @param id the result id to find by
     * @return the result model
     */
    public ResultModel get(Integer id) {
        return resultCrudRepository.findById(id).orElse(null);
    }

    /**
     * <p>
     * Finds all result models</p>
     *
     * @return all result models
     */
    public ArrayList<ResultModel> getAll() {
        ArrayList<ResultModel> resultModelArrayList = new ArrayList<>();
        for (ResultModel resultModel : resultCrudRepository.findAll()) {
            resultModelArrayList.add(resultModel);
        }
        return resultModelArrayList;
    }

    /**
     * <p>
     * Writes a result model to the database</p>
     *
     * @param resultModel the result model
     */
    public void save(ResultModel resultModel) {
        resultCrudRepository.save(resultModel);
    }

    /**
     * <p>
     * Counts the total number of correct answers within a lesson</p>
     *
     * @param lessonAnswers the answer models of a lesson
     * @return the total count of correct answers
     */
    public Integer countTotalCorrect(ArrayList<AnswerModel> lessonAnswers) {
        int totalCorrect = 0;
        for (AnswerModel answer : lessonAnswers) {
            if (answer.getCorrect()) {
                totalCorrect++;
            }
        }
        return totalCorrect;
    }

    /**
     * <p>
     * Creates a result model given a lesson id, n correct and score</p>
     *
     * @param lessonId the lesson id
     * @param nCorrect the n correct
     * @param score the score
     * @return the result model
     */
    public ResultModel create(Integer lessonId, Integer nCorrect, Float score) {
        // create a result
        ResultModel resultModel = new ResultModel();
        resultModel.setLessonId(lessonId);
        resultModel.setNCorrect(nCorrect);
        resultModel.setScore(score);
        return resultModel;
    }

    /**
     * <p>
     * Finds all result models with a specified lesson id as an array list</p>
     *
     * @param LessonId the lesson id to find by
     * @return the result models as an array list
     */
    public ResultModel findByLessonId(Integer lessonId) {
        return resultCrudRepository.findByLessonId(String.valueOf(lessonId));
    }

    /**
     * <p>
     * Creates a lesson summary given array lists of question models and answer
     * models, and n questions</p>
     *
     * @param lessonQuestions the lesson question models
     * @param lessonAnswers the lesson answer models
     * @param nQuestions the n questions
     * @return the lesson summary
     */
    public ArrayList<LessonQuestionsAnswers>  createLessonSummary(LessonModel lessonModel) {
        // pull all lesson results for lesson id
        ArrayList<LessonQuestionsAnswers> lessonQuestionsAnswers = resultCrudRepository.findAllQuestionsAnswersByLessonId(lessonModel);
        return lessonQuestionsAnswers;
    }

    /**
     * <p>
     * Creates a result summary given array lists of lesson models and results
     * models, and n lessons</p>
     *
     * @param lessons the lesson models
     * @param results the results models
     * @param nLessons the number of lessons
     * @return the results summary
     */
    public ArrayList<HashMap<String, Object>> createResultSummary(ArrayList<LessonModel> lessons, ArrayList<ResultModel> results, Long nLessons) {
        // initialise output object
        ArrayList<HashMap<String, Object>> lessonResultsArray = new ArrayList<>();
        // iterate over each question index
        for (int i = 0; i < nLessons; i++) {
            // create a temporary object to fill
            HashMap<String, Object> lessonQuestionsAnswers = new HashMap<>();
            // extract out lesson and result
            LessonModel lesson = lessons.get(i);
            ResultModel result = results.get(i);
            // put relevant data into temporary object
            lessonQuestionsAnswers.put("lessonId", lesson.getLessonId());
            lessonQuestionsAnswers.put("fromLanguage", lesson.getFromLanguage());
            lessonQuestionsAnswers.put("toLanguage", lesson.getToLanguage());
            lessonQuestionsAnswers.put("level", lesson.getLevel());
            lessonQuestionsAnswers.put("nQuestions", lesson.getNQuestions());
            lessonQuestionsAnswers.put("nCorrect", result.getNCorrect());
            lessonQuestionsAnswers.put("score", result.getScore());
            lessonResultsArray.add(lessonQuestionsAnswers);
        }
        return lessonResultsArray;
    }

}
