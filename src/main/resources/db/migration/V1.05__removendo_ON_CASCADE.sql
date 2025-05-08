ALTER TABLE tb_grupo_permissao DROP CONSTRAINT fk_grupo;
ALTER TABLE tb_grupo_permissao DROP CONSTRAINT fk_permissao;
ALTER TABLE tb_restaurante DROP CONSTRAINT fk_cozinha;
ALTER TABLE tb_produto DROP CONSTRAINT fk_restaurante;
ALTER TABLE tb_restaurante_forma_pagamento DROP CONSTRAINT fk_restaurante;
ALTER TABLE tb_restaurante_forma_pagamento DROP CONSTRAINT fk_forma_pagamento;
ALTER TABLE tb_usuario_grupo DROP CONSTRAINT fk_usuario;
ALTER TABLE tb_usuario_grupo DROP CONSTRAINT fk_grupo;

ALTER TABLE tb_grupo_permissao
ADD CONSTRAINT fk_grupo FOREIGN KEY (grupo_id) REFERENCES tb_grupo ON DELETE RESTRICT;

ALTER TABLE tb_grupo_permissao
ADD CONSTRAINT fk_permissao FOREIGN KEY (permissao_id) REFERENCES tb_permissao ON DELETE RESTRICT;

ALTER TABLE tb_restaurante
ADD CONSTRAINT fk_cozinha FOREIGN KEY (cozinha_id) REFERENCES tb_cozinha ON DELETE RESTRICT;

ALTER TABLE tb_produto
ADD CONSTRAINT fk_restaurante FOREIGN KEY (restaurante_id) REFERENCES tb_restaurante ON DELETE SET NULL;

ALTER TABLE tb_restaurante_forma_pagamento
ADD CONSTRAINT fk_restaurante FOREIGN KEY (restaurante_id) REFERENCES tb_restaurante ON DELETE SET NULL;

ALTER TABLE tb_restaurante_forma_pagamento
ADD CONSTRAINT fk_forma_pagamento FOREIGN KEY (forma_pagamento_id) REFERENCES tb_forma_pagamento ON DELETE SET NULL;

ALTER TABLE tb_usuario_grupo
ADD CONSTRAINT fk_usuario FOREIGN KEY (usuario_id) REFERENCES tb_usuario ON DELETE RESTRICT;

ALTER TABLE tb_usuario_grupo
ADD CONSTRAINT fk_grupo FOREIGN KEY (grupo_id) REFERENCES tb_grupo ON DELETE RESTRICT;
