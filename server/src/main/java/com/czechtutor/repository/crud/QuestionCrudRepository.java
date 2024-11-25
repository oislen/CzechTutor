package com.czechtutor.repository.crud;

import java.util.ArrayList;

import org.springframework.data.repository.CrudRepository;

import com.czechtutor.model.QuestionModel;

public interface QuestionCrudRepository extends CrudRepository<QuestionModel, Integer> {
    
    ArrayList<QuestionModel> findByLessonId(String LessonId);

}
