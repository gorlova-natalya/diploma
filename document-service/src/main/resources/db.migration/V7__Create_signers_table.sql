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
         WHERE position_name = 'Директор'
            OR position_name = 'Главный бухгалтер'
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
