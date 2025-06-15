package com.czechtutor.repository.crud;

import java.util.ArrayList;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.czechtutor.model.QuestionModel;

/**
 * <p>
 * The question crud repository class defines the available processes to
 * interact with the question table with</p>
 *
 * @author oislen
 */
public interface QuestionCrudRepository extends CrudRepository<QuestionModel, Integer> {

    /**
     * <p>
     * Finds all question models with a specified lesson id as an array list</p>
     *
     * @param LessonId the lesson id to find by
     * @return the question models as an array list
     */
    ArrayList<QuestionModel> findByLessonId(String LessonId);

    public static final String GET_QUESTION_BY_LESSON_QUESTION_IDS_QUERY = """
    select * from questions where lesson_id = :#{#lessonId.intValue()} and lesson_question_id = :#{#LessonQuestionId.intValue()}
    """;

    @Query(GET_QUESTION_BY_LESSON_QUESTION_IDS_QUERY)
    QuestionModel getQuestionByLessonQuestionIds(@Param("lessonId") Integer lessonId, @Param("LessonQuestionId") Integer LessonQuestionId);

}
