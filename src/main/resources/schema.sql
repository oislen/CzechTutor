create table if not exists lessons (
    lesson_id bigint auto_increment primary key,
    from_Language varchar(32) not null,
    to_Language varchar(32) not null,
    n_questions tinyint not null,
    n_options tinyint not null,
    level varchar(32) not null
);

create table if not exists questions (
    question_id bigint auto_increment primary key,
    lesson_id bigint not null,
    phrase varchar(255) not null,
    option1 varchar(255) not null,
    option2 varchar(255) not null,
    option3 varchar(255) not null,
    option4 varchar(255) not null,
    solution varchar(255) not null
);

create table if not exists answers (
    answer_id bigint auto_increment primary key,
    lesson_id bigint not null,
    question_id bigint not null,
    answer varchar(255) not null,
    correct boolean not null
);

create table if not exists results (
    result_id bigint auto_increment primary key,
    lesson_id bigint not null,
    n_correct tinyint not null,
    score real not null
);

create table if not exists ces(
  id bigint primary key,
  english varchar(255),
  czech varchar(255),
  level varchar(255),
  reference varchar(255)
) as select * from CSVREAD('/home/ubuntu/CzechTutor/src/main/resources/data/ces.csv');