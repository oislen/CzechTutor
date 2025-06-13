package com.czechtutor.service;

import java.util.ArrayList;

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
     * Finds all lesson models</p>
     *
     * @return all lesson models
     */
    public ArrayList<LessonModel> getAll() {
        ArrayList<LessonModel> lessonModelArrayList = new ArrayList<>();
        for (LessonModel lessonModel : lessonCrudRepository.findAll()) {
            lessonModelArrayList.add(lessonModel);
        }
        return lessonModelArrayList;
    }

    /**
     * <p>
     * Writes a lesson model to the database</p>
     *
     * @param LessonModel the lesson model
     */
    public void save(LessonModel lessonModel) {
        lessonCrudRepository.save(lessonModel);
    }

    /**
     * <p>
     * Counts the number of records in the lesson table</p>
     *
     * @return lessonCrudRepository.count() the count of lesson table records
     */
    public Long countLessons() {
        return lessonCrudRepository.count();
    }
}