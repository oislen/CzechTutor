-- select all lessons, questions and answers
select 
    l.lesson_id, l.from_Language, l.to_Language, l.n_questions, l.n_options, l.level,
    q.question_id, q.phrase, q.option1, q.option2, q.option3, q.option4, q.solution,
    a.answer_id, a.answer, a.correct
from lessons as l
inner join questions as q on l.lesson_id = q.lesson_id
inner join answers as a on q.question_id = a.question_id
;

-- select all lessons and results
select 
    l.lesson_id, l.from_Language, l.to_Language, l.n_questions, l.n_options, l.level,
    r.result_id, r.n_correct, r.score
from lessons as l
inner join results as r on l.lesson_id = r.lesson_id
;

-- select all czech / english phrases
select id, english, czech, level, reference from ces;