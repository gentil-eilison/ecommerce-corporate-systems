CREATE TABLE IF NOT EXISTS produto(
    id SERIAL PRIMARY KEY,
    nome VARCHAR(255) NOT NULL,
    descricao VARCHAR(255),
    preco NUMERIC NOT NULL CHECK (preco >= 0),
    estoque INT NOT NULL DEFAULT 0 CHECK (estoque >= 0)
);

CREATE TABLE IF NOT EXISTS categoria(
    id SERIAL PRIMARY KEY,
    nome VARCHAR(255) NOT NULL UNIQUE,
    descricao VARCHAR(255) NOT NULL
);

CREATE TABLE IF NOT EXISTS produto_categoria(
    produto_id INT NOT NULL REFERENCES produto ON DELETE CASCADE,
    categoria_id INT NOT NULL REFERENCES categoria ON DELETE CASCADE,
    CONSTRAINT produto_categoria_pk PRIMARY KEY (produto_id, categoria_id)
);