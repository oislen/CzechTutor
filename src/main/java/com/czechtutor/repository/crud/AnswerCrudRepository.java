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
     * @param LessonId the lesson id to find by
     * @return the answer models as an array list
     */
    ArrayList<AnswerModel> findByLessonId(String LessonId);

}
