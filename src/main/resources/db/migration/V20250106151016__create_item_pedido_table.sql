CREATE TABLE IF NOT EXISTS item_pedido(
    id SERIAL PRIMARY KEY,
    quantidade INT NOT NULL,
    pedido_id INT NOT NULL REFERENCES pedido,
    produto_id INT NOT NULL REFERENCES produto
);