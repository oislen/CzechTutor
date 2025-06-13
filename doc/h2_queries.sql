-- select all lessons, questions and answers
select 
    * 
from lessons as l
inner join questions as q on l.lesson_id = q.lesson_id
inner join answers as a on q.question_id = a.question_id
;

-- select all lessons and results
select 
    * 
from lessons as l
inner join results as r on l.lesson_id = r.lesson_id
;

-- select all czech / english phrases
select * from ces;