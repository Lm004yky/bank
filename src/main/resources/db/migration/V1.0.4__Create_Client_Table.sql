CREATE TABLE client
(
    id               SERIAL PRIMARY KEY,
    client_name      VARCHAR(50)  NOT NULL,
    client_surname   VARCHAR(50)  NOT NULL,
    address          VARCHAR(255),
    gender           VARCHAR(50)  NOT NULL,
    birth_date       VARCHAR(50),
    branch_id_branch BIGINT       NOT NULL,
    CONSTRAINT fk_client_branches FOREIGN KEY (branch_id_branch) REFERENCES branches (id)
);
