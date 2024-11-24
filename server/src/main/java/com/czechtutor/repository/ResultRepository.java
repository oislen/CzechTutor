package com.czechtutor.repository;

import org.springframework.data.repository.CrudRepository;

import com.czechtutor.model.ResultModel;

public interface ResultRepository extends CrudRepository<ResultModel, Integer> {
}
