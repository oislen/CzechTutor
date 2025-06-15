package com.czechtutor.service;

import java.time.LocalDateTime;
import java.util.ArrayList;

import org.springframework.stereotype.Service;

import com.czechtutor.model.AnswerModel;
import com.czechtutor.model.ResultModel;
import com.czechtutor.repository.crud.ResultCrudRepository;
import com.czechtutor.service.custom.UtilityService;

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
    private final UtilityService utilityService;

    public ResultService(ResultCrudRepository resultCrudRepository, UtilityService utilityService) {
        this.resultCrudRepository = resultCrudRepository;
        this.utilityService = utilityService;
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
        resultModel.setDateTime(LocalDateTime.now());
        resultModel.setDateTimeHash(utilityService.MD5DateTimeHash(resultModel.getDateTime()));
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
     * Determines if a result already exists by a result id</p>
     *
     * @param resultId the result id to find if exists
     * @return whether the result model exists or not
     */
    public Boolean existsById(Integer resultId) {
        return resultCrudRepository.existsById(resultId);
    }

    /**
     * <p>
     * Determines if a result already exists by a lesson id</p>
     *
     * @param lessonId the lesson id to find if results exists for
     * @return whether the result model exists or not
     */
    public Boolean existsByLessonId(Integer lessonId) {
        return resultCrudRepository.existsByLessonId(String.valueOf(lessonId));
    }

}
