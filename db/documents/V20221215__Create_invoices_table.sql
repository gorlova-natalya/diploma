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
