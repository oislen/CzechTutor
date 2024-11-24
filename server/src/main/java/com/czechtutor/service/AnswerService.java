package com.czechtutor.service;

import org.springframework.stereotype.Service;

import com.czechtutor.model.AnswerModel;
import com.czechtutor.model.QuestionModel;
import com.czechtutor.repository.AnswerRepository;

@Service
public class AnswerService {
    
    private final AnswerRepository answerRepository;

    public AnswerService(AnswerRepository answerRepository) {
        this.answerRepository = answerRepository;
    }   

    public AnswerModel get(Integer id) {
        return answerRepository.findById(id).orElse(null);
    }

    public void save(AnswerModel answerModel) {
        answerRepository.save(answerModel);
    }
    
    public Boolean isCorrect(QuestionModel questionModel, AnswerModel answerModel){
        return (Boolean) answerModel.getAnswer().toLowerCase().trim().equals(questionModel.getSolution().toLowerCase().trim());  
    }

}
