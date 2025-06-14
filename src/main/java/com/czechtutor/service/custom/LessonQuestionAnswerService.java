package com.czechtutor.service.custom;

import java.util.ArrayList;

import org.springframework.stereotype.Service;

import com.czechtutor.model.LessonModel;
import com.czechtutor.model.custom.LessonQuestionAnswer;
import com.czechtutor.repository.crud.custom.LessonQuestionAnswerCrudRepository;

/**
 * <p>
 * The custom lesson question answer service class defines the available processes for generating and
 * modifying the lesson question answer model</p>
 *
 * @author oislen
 */
@Service
public class LessonQuestionAnswerService {

    private final LessonQuestionAnswerCrudRepository lessonQuestionAnswerCrudRepository;

    public LessonQuestionAnswerService(LessonQuestionAnswerCrudRepository lessonQuestionAnswerCrudRepository) {
        this.lessonQuestionAnswerCrudRepository = lessonQuestionAnswerCrudRepository;
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
    public ArrayList<LessonQuestionAnswer>  createLessonSummary(LessonModel lessonModel) {
        // pull all lesson results for lesson id
        ArrayList<LessonQuestionAnswer> lessonQuestionsAnswers = lessonQuestionAnswerCrudRepository.findAllQuestionsAnswersByLesson(lessonModel);
        return lessonQuestionsAnswers;
    }

}
