package com.czechtutor.service;

import org.springframework.stereotype.Service;

import com.czechtutor.model.LessonModel;
import com.czechtutor.repository.crud.LessonCrudRepository;

@Service
public class LessonService {

    private final LessonCrudRepository lessonCrudRepository;

    public LessonService(LessonCrudRepository lessonCrudRepository) {
        this.lessonCrudRepository = lessonCrudRepository;
    }   

    public LessonModel get(Integer id) {
        return lessonCrudRepository.findById(id).orElse(null);
    }

    public void save(LessonModel lessonModel) {
        lessonCrudRepository.save(lessonModel);
    }
}