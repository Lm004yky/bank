CREATE TABLE branches
(
    id           SERIAL PRIMARY KEY,
    branch_name  VARCHAR(50)  NOT NULL,
    city         VARCHAR(50)  NOT NULL,
    address      VARCHAR(255),
    postal_code  VARCHAR(50),
    bank_id_bank BIGINT       NOT NULL,
    CONSTRAINT fk_branches_bank FOREIGN KEY (bank_id_bank) REFERENCES bank (id)
);
