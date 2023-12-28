CREATE TABLE account
(
    id               SERIAL PRIMARY KEY,
    status           VARCHAR(50) NOT NULL,
    balance          INT         NOT NULL,
    open_date        VARCHAR(50),
    close_date       VARCHAR(50),
    client_id_client BIGINT      NOT NULL,
    CONSTRAINT fk_account_client FOREIGN KEY (client_id_client) REFERENCES client (id)
);
