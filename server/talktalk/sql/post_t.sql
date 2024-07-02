CREATE TABLE post
(
    id         int primary key,
    title      varchar(255),
    content    varchar(255),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    user_id    int,
    CONSTRAINT fk_user
        FOREIGN KEY (user_id) REFERENCES user_t (id)
);