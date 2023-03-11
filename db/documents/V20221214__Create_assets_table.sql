CREATE TABLE assets
(
    id            BIGSERIAL        NOT NULL UNIQUE PRIMARY KEY,
    asset_name    VARCHAR          NOT NULL,
    asset_number  BIGINT           NOT NULL,
    asset_count   BIGINT           NOT NULL,
    cost          DOUBLE PRECISION NOT NULL,
    department_id BIGINT REFERENCES departments (id),
    employee_id   BIGINT REFERENCES employees (id),
    asset_unit_id BIGINT REFERENCES asset_units (id)

);

INSERT INTO assets (asset_name, asset_number, asset_count, cost, department_id, employee_id, asset_unit_id)
VALUES ('Картридж для Pantum M6800', 6300001, 15, 60.00, (SELECT id
                                                          FROM departments
                                                          WHERE department_name = 'Склад'), (SELECT id
                                                                                             FROM employees
                                                                                             WHERE full_name = 'Иванов Николай Николаевич'),
        (SELECT id
         FROM asset_units
         WHERE unit_name = 'Штука'));
INSERT INTO assets (asset_name, asset_number, asset_count, cost, department_id, employee_id, asset_unit_id)
VALUES ('Бумага для печати', 6300002, 70, 9.80, (SELECT id
                                                 FROM departments
                                                 WHERE department_name = 'Склад'), (SELECT id
                                                                                    FROM employees
                                                                                    WHERE full_name = 'Иванов Николай Николаевич'),
        (SELECT id
         FROM asset_units
         WHERE unit_name = 'Упаковка'));
INSERT INTO assets (asset_name, asset_number, asset_count, cost, department_id, employee_id, asset_unit_id)
VALUES ('Скрепки', 6300003, 14, 1.00, (SELECT id
                                       FROM departments
                                       WHERE department_name = 'Склад'), (SELECT id
                                                                          FROM employees
                                                                          WHERE full_name = 'Иванов Николай Николаевич'),
        (SELECT id
         FROM asset_units
         WHERE unit_name = 'Упаковка'));
INSERT INTO assets (asset_name, asset_number, asset_count, cost, department_id, employee_id, asset_unit_id)
VALUES ('Удлинитель', 6300004, 10, 18.00, (SELECT id
                                           FROM departments
                                           WHERE department_name = 'Склад'), (SELECT id
                                                                              FROM employees
                                                                              WHERE full_name = 'Иванов Николай Николаевич'),
        (SELECT id
         FROM asset_units
         WHERE unit_name = 'Штука'));
