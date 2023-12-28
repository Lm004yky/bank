CREATE TABLE loan
(
    id                 SERIAL PRIMARY KEY,
    loan_status        VARCHAR(50) NOT NULL,
    loan_amount        INT         NOT NULL,
    loan_open_date     VARCHAR(50) NOT NULL,
    loan_close_date    VARCHAR(50) NOT NULL,
    account_id_account BIGINT      NOT NULL,
    CONSTRAINT fk_loan_account FOREIGN KEY (account_id_account) REFERENCES account (id)
);
