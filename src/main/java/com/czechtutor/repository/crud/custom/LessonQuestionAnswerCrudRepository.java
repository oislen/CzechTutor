package com.czechtutor.repository.crud.custom;

import java.util.ArrayList;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.czechtutor.model.LessonModel;
import com.czechtutor.model.custom.LessonQuestionAnswer;

/**
 * <p>
 * The lesson question and answer crud repository class defines the available processes to interact
 * with the lesson question and answer model</p>
 *
 * @author oislen
 */
public interface LessonQuestionAnswerCrudRepository extends CrudRepository<LessonQuestionAnswer, Integer> {

    public static final String LESSON_QUESTIONS_ANSWERS_QUERY = """
    select
        l.lesson_id, l.from_Language, l.to_Language, l.n_questions, l.n_options, l.level,
        q.question_id, q.phrase, q.option1, q.option2, q.option3, q.option4, q.solution,
        a.answer_id, a.answer, a.correct
    from lessons as l
    inner join questions as q on l.lesson_id = q.lesson_id
    inner join answers as a on q.question_id = a.question_id
    where l.lesson_id = :#{#lessonModel.LessonId}
    """;

    @Query(LESSON_QUESTIONS_ANSWERS_QUERY)
    ArrayList<LessonQuestionAnswer> findAllQuestionsAnswersByLesson(@Param("lessonModel") LessonModel lessonModel);

}
