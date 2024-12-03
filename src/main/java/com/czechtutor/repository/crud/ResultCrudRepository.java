package com.czechtutor.repository.crud;

import org.springframework.data.repository.CrudRepository;

import com.czechtutor.model.ResultModel;

/**
 * <p>
 * The result crud repository class defines the available processes to interact
 * with the result table with</p>
 *
 * @author oislen
 */
public interface ResultCrudRepository extends CrudRepository<ResultModel, Integer> {

    /**
     * <p>
     * Finds a result model with a specified lesson id</p>
     *
     * @param LessonId the lesson id to find by
     * @return the result model
     */
    ResultModel findByLessonId(String LessonId);

}
