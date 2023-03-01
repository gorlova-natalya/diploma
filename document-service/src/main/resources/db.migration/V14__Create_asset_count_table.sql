CREATE TABLE asset_counts
(
    id                    BIGSERIAL NOT NULL UNIQUE PRIMARY KEY,
    invoice_id            BIGINT REFERENCES invoices (id),
    asset_id              BIGINT    NOT NULL REFERENCES assets (id),
    asset_for_issue_count BIGINT    NOT NULL,
    asset_for_issue_sum   DOUBLE PRECISION    NOT NULL

);

