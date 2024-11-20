package com.czechtutor.service;

import org.springframework.stereotype.Service;

import com.czechtutor.model.Result;
import com.czechtutor.repository.ResultRepository;

@Service
public class ResultService {

    private final ResultRepository resultRepository;

    public ResultService(ResultRepository resultRepository) {
        this.resultRepository = resultRepository;
    }   

    public Result get(Integer id) {
        return resultRepository.findById(id).orElse(null);
    }

    public void save(Result results) {
        resultRepository.save(results);
    }
}