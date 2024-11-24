package com.czechtutor.service;

import org.springframework.stereotype.Service;

import com.czechtutor.model.ResultModel;
import com.czechtutor.repository.ResultRepository;

@Service
public class ResultService {

    private final ResultRepository resultRepository;

    public ResultService(ResultRepository resultRepository) {
        this.resultRepository = resultRepository;
    }   

    public ResultModel get(Integer id) {
        return resultRepository.findById(id).orElse(null);
    }

    public void save(ResultModel resultModel) {
        resultRepository.save(resultModel);
    }
}