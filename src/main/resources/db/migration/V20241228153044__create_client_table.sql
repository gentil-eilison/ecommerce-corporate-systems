CREATE TABLE IF NOT EXISTS cliente(
    id SERIAL PRIMARY KEY,
    nome VARCHAR(256) NOT NULL,
    email VARCHAR(256) NOT NULL UNIQUE,
    cpf VARCHAR(14) NOT NULL UNIQUE,
    telefone VARCHAR(15) NOT NULL
);