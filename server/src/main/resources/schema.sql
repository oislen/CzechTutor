create table if not exists lesson (
    lesson_id bigint auto_increment primary key,
    from_Language varchar(32) not null,
    to_Language varchar(32) not null,
    n_questions tinyint not null,
    n_options tinyint not null
);

create table if not exists question (
    question_id bigint auto_increment primary key,
    lesson_id bigint not null,
    question_sub_id tinyint not null,
    phrase varchar(255) not null,
    option1 varchar(255) not null,
    option2 varchar(255) not null,
    option3 varchar(255) not null,
    option4 varchar(255) not null,
    solution varchar(255) not null
);

create table if not exists answer (
    answer_id bigint auto_increment primary key,
    lesson_id bigint not null,
    answer_sub_id tinyint not null,
    answer varchar(255) not null,
    correct boolean not null
);

create table if not exists ces(
  id bigint primary key,
  en varchar(255),
  cz varchar(255),
  ref varchar(255)
) as select * from CSVREAD('E:\GitHub\CzechTutor\server\src\main\resources\data\ces.csv');