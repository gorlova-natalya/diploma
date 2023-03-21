create database documents_test;

CREATE TABLE positions
(
    id   BIGSERIAL NOT NULL UNIQUE PRIMARY KEY,
    position_name VARCHAR   NOT NULL,
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

CREATE TABLE employees
(
    id          BIGSERIAL NOT NULL UNIQUE PRIMARY KEY,
    full_name    VARCHAR NOT NULL,
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


CREATE TABLE organizations
(
    id                BIGSERIAL NOT NULL UNIQUE PRIMARY KEY,
    organization_name VARCHAR   NOT NULL,
    payer_ID_number   BIGINT    NOT NULL UNIQUE,
    supervisor_id     BIGINT REFERENCES employees (id)
);

INSERT INTO organizations (organization_name, payer_ID_number, supervisor_id)
VALUES ('Минская центральная таможня', '100420574', (SELECT id
                                                     FROM employees
                                                     WHERE full_name = 'Шевченко Александр Владимирович'));

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

CREATE TABLE signers
(
    document_type_id BIGINT REFERENCES types (id),
    employees_id     BIGINT NOT NULL REFERENCES employees (id)
);

INSERT INTO signers (document_type_id, employees_id)
SELECT typ.id, emp.id
FROM employees AS emp
         INNER JOIN positions AS pos ON pos.id = emp.position_id
         FULL JOIN types AS typ ON typ.type_name = 'Приходный кассовый ордер'
WHERE position_name = 'Главный бухгалтер'
   OR position_name = 'Кассир';

INSERT INTO signers (document_type_id, employees_id)
SELECT typ.id, emp.id
FROM employees AS emp
         INNER JOIN positions AS pos ON pos.id = emp.position_id
         FULL JOIN types AS typ ON typ.type_name = 'Расходный кассовый ордер'
WHERE position_name = 'Директор'
   OR position_name = 'Главный бухгалтер'
   OR position_name = 'Кассир';

INSERT INTO signers (document_type_id, employees_id)
SELECT typ.id, emp.id
FROM employees AS emp
         INNER JOIN positions AS pos ON pos.id = emp.position_id
         FULL JOIN types AS typ ON typ.type_name = 'Накладная на выдачу в производство'
WHERE position_name = 'Директор'
   OR position_name = 'Главный бухгалтер';

INSERT INTO signers (document_type_id, employees_id)
SELECT typ.id, emp.id
FROM employees AS emp
         INNER JOIN positions AS pos ON pos.id = emp.position_id
         FULL JOIN types AS typ ON typ.type_name = 'Накладная на внутреннее перемещение'
WHERE position_name = 'Директор'
   OR position_name = 'Главный бухгалтер';

CREATE TABLE orders
(
    id               BIGSERIAL NOT NULL UNIQUE PRIMARY KEY,
    purpose          VARCHAR   NOT NULL,
    document_type_id BIGINT REFERENCES types (id),
    employee_id      BIGINT REFERENCES employees (id),
    organization_id  BIGINT REFERENCES organizations (id),
    order_sum        BIGINT    NOT NULL,
    order_number     BIGINT    NOT NULL UNIQUE,
    order_date       TIMESTAMP,
    annex            VARCHAR
);

CREATE TABLE vouchers
(
    id               BIGSERIAL NOT NULL UNIQUE PRIMARY KEY,
    purpose          VARCHAR   NOT NULL,
    document_type_id BIGINT REFERENCES types (id),
    employee_id      BIGINT REFERENCES employees (id),
    organization_id  BIGINT REFERENCES organizations (id),
    order_sum        BIGINT    NOT NULL,
    order_number     BIGINT    NOT NULL UNIQUE,
    order_date       TIMESTAMP,
    annex            VARCHAR,
    passport         VARCHAR

);


CREATE TABLE departments
(
    id              BIGSERIAL NOT NULL UNIQUE PRIMARY KEY,
    department_name VARCHAR   NOT NULL,
    organization_id BIGINT REFERENCES organizations (id)
);

INSERT INTO departments (department_name, organization_id)
VALUES ('ОТР', (SELECT id
                FROM organizations
                WHERE organization_name = 'Минская центральная таможня'));
INSERT INTO departments (department_name, organization_id)
VALUES ('ОБК', (SELECT id
                FROM organizations
                WHERE organization_name = 'Минская центральная таможня'));
INSERT INTO departments (department_name, organization_id)
VALUES ('ОБуИК', (SELECT id
                  FROM organizations
                  WHERE organization_name = 'Минская центральная таможня'));
INSERT INTO departments (department_name, organization_id)
VALUES ('АХО', (SELECT id
                FROM organizations
                WHERE organization_name = 'Минская центральная таможня'));
INSERT INTO departments (department_name, organization_id)
VALUES ('ОПО', (SELECT id
                FROM organizations
                WHERE organization_name = 'Минская центральная таможня'));
INSERT INTO departments (department_name, organization_id)
VALUES ('Склад', (SELECT id
                  FROM organizations
                  WHERE organization_name = 'Минская центральная таможня'));

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


CREATE TABLE invoices
(
    id               BIGSERIAL NOT NULL UNIQUE PRIMARY KEY,
    invoice_number     BIGINT    NOT NULL UNIQUE,
    invoice_date       TIMESTAMP,
    organization_id  BIGINT REFERENCES organizations (id),
    from_department_id BIGINT REFERENCES departments (id),
    to_department_id BIGINT REFERENCES departments (id),
    from_employee_id BIGINT REFERENCES employees (id),
    to_employee_id BIGINT REFERENCES employees (id),
    document_type_id BIGINT REFERENCES types (id)
);


CREATE TABLE asset_counts
(
    id                    BIGSERIAL NOT NULL UNIQUE PRIMARY KEY,
    invoice_id            BIGINT REFERENCES invoices (id),
    asset_id              BIGINT    NOT NULL REFERENCES assets (id),
    asset_for_issue_count BIGINT    NOT NULL,
    asset_for_issue_sum   DOUBLE PRECISION    NOT NULL

);

