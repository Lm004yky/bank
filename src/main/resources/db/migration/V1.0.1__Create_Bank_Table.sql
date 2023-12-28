CREATE TABLE IF NOT EXISTS bank
(
    id             SERIAL PRIMARY KEY,
    name           VARCHAR(50) NOT NULL,
    capitalization VARCHAR(50)
);
