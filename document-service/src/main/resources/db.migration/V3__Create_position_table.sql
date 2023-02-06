CREATE TABLE positions
(
    id   BIGSERIAL NOT NULL UNIQUE PRIMARY KEY,
    position_name VARCHAR   NOT NULL UNIQUE,
    signature_authority BOOLEAN
);

INSERT INTO positions (position_name, signature_authority)
VALUES ('Директор', true);
INSERT INTO positions (position_name, signature_authority)
VALUES ('Главный бухгалтер', true);
INSERT INTO positions (position_name, signature_authority)
VALUES ('Начальник отдела', false);
INSERT INTO positions (position_name, signature_authority)
VALUES ('Кассир', false);
INSERT INTO positions (position_name, signature_authority)
VALUES ('Сотрудник', false);
INSERT INTO positions (position_name, signature_authority)
VALUES ('Инспектор', false);