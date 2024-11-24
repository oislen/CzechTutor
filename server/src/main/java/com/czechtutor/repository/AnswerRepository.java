package com.czechtutor.repository;

import org.springframework.data.repository.CrudRepository;

import com.czechtutor.model.AnswerModel;

public interface AnswerRepository extends CrudRepository<AnswerModel, Integer> {
}
