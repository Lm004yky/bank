CREATE TABLE worker
(
    id               SERIAL PRIMARY KEY,
    worker_name      VARCHAR(50)  NOT NULL,
    worker_surname   VARCHAR(50)  NOT NULL,
    address          VARCHAR(255) NOT NULL,
    position         VARCHAR(50)  NOT NULL,
    hire_date        VARCHAR(50)  NOT NULL,
    salary           INT          NOT NULL,
    branch_id_branch BIGINT          NOT NULL,
    CONSTRAINT fk_worker_branches FOREIGN KEY (branch_id_branch) REFERENCES branches (id)
);
