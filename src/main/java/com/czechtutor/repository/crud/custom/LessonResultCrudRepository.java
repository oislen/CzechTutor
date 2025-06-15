package com.czechtutor.repository.crud.custom;

import java.util.ArrayList;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;

import com.czechtutor.model.custom.LessonResult;

/**
 * <p>
 * The lesson result crud repository class defines the available processes to interact
 * with the lesson result model</p>
 *
 * @author oislen
 */
public interface LessonResultCrudRepository extends CrudRepository<LessonResult, Integer> {

    public static final String LESSONS_RESULTS_QUERY = """
    select 
        l.lesson_id, l.from_Language, l.to_Language, l.n_questions, l.n_options, l.level,
        r.result_id, r.n_correct, r.score
    from lessons as l
    left join results as r on l.lesson_id = r.lesson_id
    order by lesson_id
    ;
    """;

    @Query(LESSONS_RESULTS_QUERY)
    ArrayList<LessonResult> findAllLessonsResults();

}
