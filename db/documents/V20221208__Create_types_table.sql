CREATE TABLE types
(
    id        BIGSERIAL NOT NULL UNIQUE PRIMARY KEY,
    type_name VARCHAR   NOT NULL UNIQUE
);

INSERT INTO types (type_name)
VALUES ('Приходный кассовый ордер');
INSERT INTO types (type_name)
VALUES ('Расходный кассовый ордер');
INSERT INTO types (type_name)
VALUES ('Накладная на выдачу в производство');
INSERT INTO types (type_name)
VALUES ('Накладная на внутреннее перемещение');