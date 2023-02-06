CREATE TABLE employees
(
    id          BIGSERIAL NOT NULL UNIQUE PRIMARY KEY,
    full_name    VARCHAR   NOT NULL UNIQUE,
    position_id BIGINT REFERENCES positions (id)
);

INSERT INTO employees (full_name, position_id)
VALUES ('Шевченко Александр Владимирович', (SELECT id
                                            FROM positions
                                            WHERE position_name = 'Директор'));
INSERT INTO employees (full_name, position_id)
VALUES ('Акудович Елена Георгиевна', (SELECT id
                                            FROM positions
                                            WHERE position_name = 'Главный бухгалтер'));
INSERT INTO employees (full_name, position_id)
VALUES ('Цыпурко Виктория Васильевна', (SELECT id
                                            FROM positions
                                            WHERE position_name = 'Кассир'));
INSERT INTO employees (full_name, position_id)
VALUES ('Иванов Николай Николаевич', (SELECT id
                                            FROM positions
                                            WHERE position_name = 'Начальник отдела'));
INSERT INTO employees (full_name, position_id)
VALUES ('Чайковский Степан Васильевич', (SELECT id
                                            FROM positions
                                            WHERE position_name = 'Начальник отдела'));
INSERT INTO employees (full_name, position_id)
VALUES ('Чернявская Ирина Анатольевна', (SELECT id
                                            FROM positions
                                            WHERE position_name = 'Инспектор'));
INSERT INTO employees (full_name, position_id)
VALUES ('Олейник Виталий Александрович', (SELECT id
                                         FROM positions
                                         WHERE position_name = 'Сотрудник'));