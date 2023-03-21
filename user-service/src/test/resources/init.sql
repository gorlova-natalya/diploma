create database users_test;

CREATE TABLE roles
(
    id   BIGSERIAL NOT NULL UNIQUE PRIMARY KEY,
    role VARCHAR   NOT NULL UNIQUE
);

INSERT INTO roles (role)
VALUES ('admin');
INSERT INTO roles (role)
VALUES ('user');


CREATE TABLE users
(
    id       BIGSERIAL NOT NULL UNIQUE PRIMARY KEY,
    login    VARCHAR   NOT NULL UNIQUE,
    password VARCHAR   NOT NULL,
    role_id  BIGINT    REFERENCES roles (id)
);

INSERT INTO users (login, password, role_id)
VALUES ('Natasha', '$2a$12$MLzVq4kZXBnvXJE2xZCdIO/rb/gselnIxdTp6awqyuzwc9CmOE6O6', (SELECT id
                                                                                    FROM roles
                                                                                    WHERE role = 'admin'));
INSERT INTO users (login, password, role_id)
VALUES ('Natasha1', '$2a$12$MLzVq4kZXBnvXJE2xZCdIO/rb/gselnIxdTp6awqyuzwc9CmOE6O6', (SELECT id
                                                                                     FROM roles
                                                                                     WHERE role = 'user'));
