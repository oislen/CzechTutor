package com.czechtutor.repository.crud;

import org.springframework.data.repository.CrudRepository;

import com.czechtutor.model.LessonModel;

/**
 * <p>
 * The lesson crud repository class defines the available processes to interact
 * with the lesson table with</p>
 *
 * @author oislen
 */
public interface LessonCrudRepository extends CrudRepository<LessonModel, Integer> {
}
