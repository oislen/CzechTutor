# Czech Tutor

A simple single page website for practising Czech phrases and vocabulary.

# Data Source

Czech to English phrases and vocabulary sourced from:

- https://www.manythings.org/anki/
- https://apps.ankiweb.net/
- https://tatoeba.org/en

# Execute Web App

```
exeSpring.cmd
```

# Data Model

```
select * from lessons as l inner join results as r on l.lesson_id = r.lesson_id;
select * from questions as q inner join answers as a on q.question_id = a.question_id;
```