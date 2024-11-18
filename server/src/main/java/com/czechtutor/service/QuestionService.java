package com.czechtutor.service;

import org.springframework.stereotype.Service;

import com.czechtutor.model.Question;
import com.czechtutor.repository.QuestionRepository;

@Service
public class QuestionService {
    
    private final QuestionRepository questionRepository;

    public QuestionService(QuestionRepository questionRepository) {
        this.questionRepository = questionRepository;
    }   

    public Question get(Integer id) {
        return questionRepository.findById(id).orElse(null);
    }

    public void save(Question question) {
        questionRepository.save(question);
    }
}
