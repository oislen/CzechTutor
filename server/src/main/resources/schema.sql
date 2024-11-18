create table if not exists lesson (
    lesson_id bigint auto_increment primary key,
    from_Language varchar(32) not null,
    to_Language varchar(32) not null,
    n_questions tinyint not null,
    n_options tinyint not null
);

create table if not exists answer (
    answer_id bigint primary key,
    lesson_id bigint not null,
    answer_sub_id tinyint not null,
    answer varchar(255) not null,
    correct boolean not null
);