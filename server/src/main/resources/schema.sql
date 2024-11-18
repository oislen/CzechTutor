create table if not exists lesson (
    lesson_id bigint auto_increment primary key,
    from_Language varchar(32) not null,
    to_Language varchar(32) not null,
    n_questions tinyint not null,
    n_options tinyint not null
);