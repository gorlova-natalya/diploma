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
