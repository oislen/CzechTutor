package com.czechtutor.service.custom;

import java.util.ArrayList;

import org.springframework.stereotype.Service;

import com.czechtutor.model.custom.LessonResult;
import com.czechtutor.repository.crud.custom.LessonResultCrudRepository;

/**
 * <p>
 * The custom lesson result service class defines the available processes for generating and
 * modifying the lesson result model</p>
 *
 * @author oislen
 */
@Service
public class LessonResultService {

    private final LessonResultCrudRepository lessonResultCrudRepository;

    public LessonResultService(LessonResultCrudRepository lessonResultCrudRepository) {
        this.lessonResultCrudRepository = lessonResultCrudRepository;
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
    public ArrayList<LessonResult> createResultSummary() {
        // pull all lessons and results
        ArrayList<LessonResult> lessonsResults = lessonResultCrudRepository.findAllLessonsResults();
        return lessonsResults;
    }

}
