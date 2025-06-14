package com.czechtutor.repository.crud;

import java.util.ArrayList;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.czechtutor.model.LessonModel;
import com.czechtutor.model.ResultModel;
import com.czechtutor.model.custom.LessonQuestionsAnswers;

/**
 * <p>
 * The result crud repository class defines the available processes to interact
 * with the result table with</p>
 *
 * @author oislen
 */
public interface ResultCrudRepository extends CrudRepository<ResultModel, Integer> {

    /**
     * <p>
     * Finds a result model with a specified lesson id</p>
     *
     * @param LessonId the lesson id to find by
     * @return the result model
     */
    ResultModel findByLessonId(String LessonId);

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
    //public List<UserProjection> getAllRequestResponseRecords();
    ArrayList<LessonQuestionsAnswers> findAllQuestionsAnswersByLessonId(@Param("lessonModel") LessonModel lessonModel);

}
