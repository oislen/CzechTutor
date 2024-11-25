package com.czechtutor.service;

import java.util.ArrayList;

import org.springframework.stereotype.Service;

import com.czechtutor.model.AnswerModel;
import com.czechtutor.model.QuestionModel;
import com.czechtutor.repository.crud.AnswerCrudRepository;

@Service
public class AnswerService {
    
    private final AnswerCrudRepository answerCrudRepository;

    public AnswerService(AnswerCrudRepository answerCrudRepository) {
        this.answerCrudRepository = answerCrudRepository;
    }   

    public AnswerModel get(Integer id) {
        return answerCrudRepository.findById(id).orElse(null);
    }

    public void save(AnswerModel answerModel) {
        answerCrudRepository.save(answerModel);
    }
    
    public Boolean isCorrect(QuestionModel questionModel, AnswerModel answerModel){
        return (Boolean) answerModel.getAnswer().toLowerCase().trim().equals(questionModel.getSolution().toLowerCase().trim());  
    }

    public ArrayList<AnswerModel>  findByLessonId(Integer lessonId) {
        return answerCrudRepository.findByLessonId(String.valueOf(lessonId));
    }

}
