CREATE TABLE student
(
    id       BIGINT PRIMARY KEY,
    name     VARCHAR(255),
    surname  VARCHAR(255),
    age      INTEGER,
    city     VARCHAR(255),
    group_id BIGINT,
    FOREIGN KEY (group_id) REFERENCES university_group (id)
);

