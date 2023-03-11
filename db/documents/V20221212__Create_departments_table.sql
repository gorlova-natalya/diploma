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