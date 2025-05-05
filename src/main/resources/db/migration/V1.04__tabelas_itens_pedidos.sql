CREATE TABLE tb_item_pedido (
    preco_total NUMERIC(38,2) NOT NULL,
    preco_unitario NUMERIC(38,2) NOT NULL,
    quantidade INTEGER NOT NULL,
    id BIGSERIAL PRIMARY KEY,
    pedido_id BIGINT NOT NULL,
    produto_id BIGINT NOT NULL,
    observacao VARCHAR(255) NOT NULL
);

CREATE TABLE tb_pedido (
    data_cancelamento TIMESTAMP,
    data_confirmacao TIMESTAMP,
    data_criacao TIMESTAMP NOT NULL,
    data_entrega TIMESTAMP,
    status SMALLINT NOT NULL CHECK (status BETWEEN 0 AND 3),
    sub_total NUMERIC(38,2) NOT NULL,
    taxa_frete NUMERIC(38,2) NOT NULL,
    valor_total NUMERIC(38,2) NOT NULL,
    cidade_id BIGINT,
    estado_id BIGINT,
    forma_pagamento_id BIGINT NOT NULL,
    id BIGSERIAL PRIMARY KEY,
    restaurante_id BIGINT NOT NULL,
    usuario_id BIGINT NOT NULL,
    bairro VARCHAR(255),
    cep VARCHAR(255),
    complemento VARCHAR(255),
    logradouro VARCHAR(255),
    numero VARCHAR(255)
);