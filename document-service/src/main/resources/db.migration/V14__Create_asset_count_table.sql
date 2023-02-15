CREATE TABLE asset_counts
(
    invoice_id            BIGINT REFERENCES invoices (id),
    asset_id              BIGINT NOT NULL REFERENCES assets (id),
    asset_for_issue_count BIGINT NOT NULL
);

