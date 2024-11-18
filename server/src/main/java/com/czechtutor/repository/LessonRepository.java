package com.czechtutor.repository;

import org.springframework.data.repository.CrudRepository;

import com.czechtutor.model.Lesson;

public interface LessonRepository extends CrudRepository<Lesson, Integer> {
}
