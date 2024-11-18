package com.czechtutor.service;

import com.czechtutor.model.Lesson;
import com.czechtutor.repository.LessonRepository;
import org.springframework.stereotype.Service;

@Service
public class LessonService {

    private final LessonRepository lessonRepository;

    public LessonService(LessonRepository lessonRepository) {
        this.lessonRepository = lessonRepository;
    }   

    public Lesson get(Integer id) {
        return lessonRepository.findById(id).orElse(null);
    }

    public void save(Lesson lesson) {
        lessonRepository.save(lesson);
    }
}