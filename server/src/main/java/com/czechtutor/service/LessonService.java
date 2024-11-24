package com.czechtutor.service;

import org.springframework.stereotype.Service;

import com.czechtutor.model.LessonModel;
import com.czechtutor.repository.LessonRepository;

@Service
public class LessonService {

    private final LessonRepository lessonRepository;

    public LessonService(LessonRepository lessonRepository) {
        this.lessonRepository = lessonRepository;
    }   

    public LessonModel get(Integer id) {
        return lessonRepository.findById(id).orElse(null);
    }

    public void save(LessonModel lessonModel) {
        lessonRepository.save(lessonModel);
    }
}