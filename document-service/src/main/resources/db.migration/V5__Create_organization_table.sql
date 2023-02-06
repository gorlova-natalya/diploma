CREATE TABLE organizations
(
    id                BIGSERIAL NOT NULL UNIQUE PRIMARY KEY,
    organization_name VARCHAR   NOT NULL UNIQUE,
    payer_ID_number   BIGINT    NOT NULL UNIQUE,
    supervisor_id     BIGINT REFERENCES employees (id)
);

INSERT INTO organizations (organization_name, payer_ID_number, supervisor_id)
VALUES ('Минская центральная таможня', '100420574', (SELECT id
                                            FROM employees
                                            WHERE full_name = 'Шевченко Александр Владимирович'));
