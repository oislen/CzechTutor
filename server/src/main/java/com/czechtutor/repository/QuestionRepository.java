package com.czechtutor.repository;

import org.springframework.data.repository.CrudRepository;

import com.czechtutor.model.Question;

public interface QuestionRepository extends CrudRepository<Question, Integer> {
}
