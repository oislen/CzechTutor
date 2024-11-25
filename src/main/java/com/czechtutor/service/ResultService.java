package com.czechtutor.service;

import java.util.ArrayList;

import org.springframework.stereotype.Service;

import com.czechtutor.model.AnswerModel;
import com.czechtutor.model.ResultModel;
import com.czechtutor.repository.crud.ResultCrudRepository;

@Service
public class ResultService {

    private final ResultCrudRepository resultCrudRepository;

    public ResultService(ResultCrudRepository resultCrudRepository) {
        this.resultCrudRepository = resultCrudRepository;
    }   

    public ResultModel get(Integer id) {
        return resultCrudRepository.findById(id).orElse(null);
    }

    public void save(ResultModel resultModel) {
        resultCrudRepository.save(resultModel);
    }
    
    public Integer countTotalCorrect(ArrayList<AnswerModel> lessonAnswers){
        int totalCorrect = 0;
        for (AnswerModel answer : lessonAnswers) {
            if (answer.getCorrect()) {
                totalCorrect++;
            }
        }
        return totalCorrect;
    }

    public ResultModel create(Integer lessonId, Integer nCorrect, Float score){
        // create a result
        ResultModel resultModel = new ResultModel();
        resultModel.setLessonId(lessonId);
        resultModel.setNCorrect(nCorrect);
        resultModel.setScore(score);
        return resultModel;
    }

    public ResultModel  findByLessonId(Integer lessonId) {
        return resultCrudRepository.findByLessonId(String.valueOf(lessonId));
    }

}