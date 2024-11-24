package com.czechtutor.repository.crud;

import org.springframework.data.repository.CrudRepository;

import com.czechtutor.model.AnswerModel;

public interface AnswerCrudRepository extends CrudRepository<AnswerModel, Integer> {
}
