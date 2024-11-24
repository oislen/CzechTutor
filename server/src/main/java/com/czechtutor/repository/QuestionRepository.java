package com.czechtutor.repository;

import org.springframework.data.repository.CrudRepository;

import com.czechtutor.model.QuestionModel;

public interface QuestionRepository extends CrudRepository<QuestionModel, Integer> {
}
