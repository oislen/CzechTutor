package com.czechtutor.repository;

import org.springframework.data.repository.CrudRepository;

import com.czechtutor.model.LessonModel;

public interface LessonRepository extends CrudRepository<LessonModel, Integer> {
}
