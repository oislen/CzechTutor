package com.czechtutor.repository.crud;

import java.util.ArrayList;

import org.springframework.data.repository.CrudRepository;

import com.czechtutor.model.AnswerModel;

/**
 * <p>
 * The answer crud repository class defines the available processes to interact
 * with the answer table with</p>
 *
 * @author oislen
 */
public interface AnswerCrudRepository extends CrudRepository<AnswerModel, Integer> {

    /**
     * <p>
     * Finds all answer models with a specified lesson id as an array list</p>
     *
     * @param lessonId the lesson id to find by
     * @return the answer models as an array list
     */
    ArrayList<AnswerModel> findByLessonId(String lessonId);

    /**
     * <p>
     * Finds a answer model by a question id</p>
     *
     * @param questionId the question id to find an answer by
     * @return the answer model
     */
    AnswerModel findByQuestionId(String questionId);

    /**
     * <p>
     * Determines if an answer model exists for a question id</p>
     *
     * @param questionId the question id to find if answer exist for
     * @return whether the answer model exists
     */
    Boolean existsByQuestionId(String questionId);

}
