CREATE TABLE IF NOT EXISTS cliente(
    id SERIAL PRIMARY KEY,
    nome VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL UNIQUE,
    cpf VARCHAR(14) NOT NULL UNIQUE,
    telefone VARCHAR(15) NOT NULL
);