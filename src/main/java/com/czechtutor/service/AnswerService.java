package com.czechtutor.service;

import java.util.ArrayList;

import org.springframework.stereotype.Service;

import com.czechtutor.model.AnswerModel;
import com.czechtutor.model.QuestionModel;
import com.czechtutor.repository.crud.AnswerCrudRepository;

/**
 * <p>
 * The answer service class defines the available processes for generating and
 * modifying the answer model</p>
 *
 * @author oislen
 */
@Service
public class AnswerService {

    private final AnswerCrudRepository answerCrudRepository;

    public AnswerService(AnswerCrudRepository answerCrudRepository) {
        this.answerCrudRepository = answerCrudRepository;
    }

    /**
     * <p>
     * Finds an answer model given an answer id</p>
     *
     * @param id the answer id to find by
     * @return the answer model
     */
    public AnswerModel get(Integer id) {
        return answerCrudRepository.findById(id).orElse(null);
    }

    /**
     * <p>
     * Writes an answer model to the database</p>
     *
     * @param answerModel the answer model
     */
    public void save(AnswerModel answerModel) {
        answerCrudRepository.save(answerModel);
    }

    /**
     * <p>
     * Determines whether an answer of an answer model is correct given it's
     * question model</p>
     *
     * @param questionModel the question model
     * @param answerModel the answer model
     * @return whether the answer of the answer model is correct
     */
    public Boolean isCorrect(QuestionModel questionModel, AnswerModel answerModel) {
        return (Boolean) answerModel.getAnswer().toLowerCase().trim().equals(questionModel.getSolution().toLowerCase().trim());
    }

    /**
     * <p>
     * Finds all answer models with a specified lesson id as an array list</p>
     *
     * @param LessonId the lesson id to find by
     * @return the answer models as an array list
     */
    public ArrayList<AnswerModel> findByLessonId(Integer lessonId) {
        return answerCrudRepository.findByLessonId(String.valueOf(lessonId));
    }

    /**
     * <p>
     * Determines if an answer already exists by an answer id</p>
     *
     * @param answerId the answer id to find if exists
     * @return whether the answer model exists or not
     */
    public Boolean existsById(Integer answerId) {
        return answerCrudRepository.existsById(answerId);
    }

}
