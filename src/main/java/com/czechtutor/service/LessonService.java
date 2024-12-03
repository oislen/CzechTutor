package com.czechtutor.service;

import org.springframework.stereotype.Service;

import com.czechtutor.model.LessonModel;
import com.czechtutor.repository.crud.LessonCrudRepository;

/**
 * <p>
 * The lesson service class defines the available processes for generating and
 * modifying the lesson model</p>
 *
 * @author oislen
 */
@Service
public class LessonService {

    private final LessonCrudRepository lessonCrudRepository;

    public LessonService(LessonCrudRepository lessonCrudRepository) {
        this.lessonCrudRepository = lessonCrudRepository;
    }   

    /**
     * <p>
     * Finds a lesson model given a lesson id</p>
     *
     * @param id the lesson id to find by
     * @return the lesson model
     */
    public LessonModel get(Integer id) {
        return lessonCrudRepository.findById(id).orElse(null);
    }

    /**
     * <p>
     * Writes an lesson model to the database</p>
     *
     * @param LessonModel the lesson model
     */
    public void save(LessonModel lessonModel) {
        lessonCrudRepository.save(lessonModel);
    }
}