ALTER TABLE tb_pedido ADD COLUMN codigo VARCHAR(36) NOT NULL;
ALTER TABLE tb_pedido ALTER COLUMN codigo SET DATA TYPE UUID USING codigo::UUID;
UPDATE tb_pedido SET codigo = gen_random_uuid();
ALTER TABLE tb_pedido ADD CONSTRAINT uk_pedido_codigo UNIQUE (codigo);
