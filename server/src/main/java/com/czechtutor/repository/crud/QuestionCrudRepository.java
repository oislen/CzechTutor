package com.czechtutor.repository.crud;

import org.springframework.data.repository.CrudRepository;

import com.czechtutor.model.QuestionModel;

public interface QuestionCrudRepository extends CrudRepository<QuestionModel, Integer> {
}
