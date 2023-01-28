CREATE TABLE users
(
    id       BIGSERIAL NOT NULL UNIQUE PRIMARY KEY,
    login    VARCHAR   NOT NULL UNIQUE,
    password VARCHAR   NOT NULL,
    role_id  BIGINT    NOT NULL REFERENCES roles (id)
);

INSERT INTO users (login, password, role_id)
VALUES ('Natasha', '123', 'admin');
INSERT INTO users (login, password)
VALUES ('Natasha1', '123', 'user');