CREATE TABLE tb_forma_pagamento (
    id SERIAL PRIMARY KEY,
    descricao VARCHAR(255) NOT NULL
);

CREATE TABLE tb_grupo (
    id SERIAL PRIMARY KEY,
    nome VARCHAR(255)
);

CREATE TABLE tb_permissao (
    id SERIAL PRIMARY KEY,
    nome VARCHAR(255) NOT NULL,
    descricao VARCHAR(255) NOT NULL
);

CREATE TABLE tb_grupo_permissao (
    grupo_id BIGINT NOT NULL,
    permissao_id BIGINT NOT NULL,
    PRIMARY KEY (grupo_id, permissao_id),
    CONSTRAINT fk_grupo FOREIGN KEY (grupo_id) REFERENCES tb_grupo ON DELETE CASCADE,
    CONSTRAINT fk_permissao FOREIGN KEY (permissao_id) REFERENCES tb_permissao ON DELETE CASCADE
);

CREATE TABLE tb_restaurante (
    id SERIAL PRIMARY KEY,
    nome VARCHAR(255) NOT NULL,
    taxa_frete NUMERIC(38,2) NOT NULL,
    bairro VARCHAR(255),
    cep VARCHAR(255),
    complemento VARCHAR(255),
    logradouro VARCHAR(255),
    numero VARCHAR(255),
    cidade_id BIGINT,
    cozinha_id BIGINT NOT NULL,
    estado_id BIGINT,
    data_cadastro TIMESTAMP,
    data_atualizacao TIMESTAMP,
    CONSTRAINT fk_cozinha FOREIGN KEY (cozinha_id) REFERENCES tb_cozinha ON DELETE CASCADE,
    CONSTRAINT fk_cidade FOREIGN KEY (cidade_id) REFERENCES tb_cidade ON DELETE SET NULL,
    CONSTRAINT fk_estado FOREIGN KEY (estado_id) REFERENCES tb_estado ON DELETE SET NULL
);

CREATE TABLE tb_produto (
    id SERIAL PRIMARY KEY,
    nome VARCHAR(255),
    descricao VARCHAR(255),
    preco NUMERIC(38,2),
    ativo BOOLEAN,
    restaurante_id BIGINT,
    CONSTRAINT fk_restaurante FOREIGN KEY (restaurante_id) REFERENCES tb_restaurante ON DELETE CASCADE
);

CREATE TABLE tb_restaurante_forma_pagamento (
    restaurante_id BIGINT NOT NULL,
    forma_pagamento_id BIGINT NOT NULL,
    PRIMARY KEY (restaurante_id, forma_pagamento_id),
    CONSTRAINT fk_restaurante FOREIGN KEY (restaurante_id) REFERENCES tb_restaurante ON DELETE CASCADE,
    CONSTRAINT fk_forma_pagamento FOREIGN KEY (forma_pagamento_id) REFERENCES tb_forma_pagamento ON DELETE CASCADE
);

CREATE TABLE tb_usuario (
    id SERIAL PRIMARY KEY,
    nome VARCHAR(255),
    email VARCHAR(255) UNIQUE,
    senha VARCHAR(255),
    data_cadastro TIMESTAMP
);

CREATE TABLE tb_usuario_grupo (
    usuario_id BIGINT NOT NULL,
    grupo_id BIGINT NOT NULL,
    PRIMARY KEY (usuario_id, grupo_id),
    CONSTRAINT fk_usuario FOREIGN KEY (usuario_id) REFERENCES tb_usuario ON DELETE CASCADE,
    CONSTRAINT fk_grupo FOREIGN KEY (grupo_id) REFERENCES tb_grupo ON DELETE CASCADE
);
