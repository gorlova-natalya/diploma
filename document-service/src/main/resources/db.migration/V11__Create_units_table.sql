CREATE TABLE asset_units
(
    id        BIGSERIAL NOT NULL UNIQUE PRIMARY KEY,
    unit_name VARCHAR   NOT NULL

);

INSERT INTO asset_units (unit_name)
VALUES ('Штука');
INSERT INTO asset_units (unit_name)
VALUES ('Рулон');
INSERT INTO asset_units (unit_name)
VALUES ('Упаковка');
INSERT INTO asset_units (unit_name)
VALUES ('Комплект');