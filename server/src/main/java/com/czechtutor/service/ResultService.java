package com.czechtutor.service;

import org.springframework.stereotype.Service;

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
}