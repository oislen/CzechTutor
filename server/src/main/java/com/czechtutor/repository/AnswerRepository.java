package com.czechtutor.repository;

import org.springframework.data.repository.CrudRepository;

import com.czechtutor.model.Answer;

public interface AnswerRepository extends CrudRepository<Answer, Integer> {
}
