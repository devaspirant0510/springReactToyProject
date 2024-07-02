create table user_t
(
    id          int primary key,
    user_name   varchar(255),
    password    varchar(255),
    profile_url varchar(255),
    user_desc   varchar(255),
    created_at  TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);