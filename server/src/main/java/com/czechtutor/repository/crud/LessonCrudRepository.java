package com.czechtutor.repository.crud;

import org.springframework.data.repository.CrudRepository;

import com.czechtutor.model.LessonModel;

public interface LessonCrudRepository extends CrudRepository<LessonModel, Integer> {
}
