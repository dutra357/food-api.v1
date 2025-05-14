ALTER TABLE tb_restaurante ADD COLUMN aberto BOOLEAN NOT NULL DEFAULT TRUE;

UPDATE tb_restaurante SET ativo = TRUE;
