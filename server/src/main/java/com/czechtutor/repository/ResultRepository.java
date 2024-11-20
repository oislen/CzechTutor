package com.czechtutor.repository;

import org.springframework.data.repository.CrudRepository;

import com.czechtutor.model.Result;

public interface ResultRepository extends CrudRepository<Result, Integer> {
}
