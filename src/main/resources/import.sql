insert into tb_cozinha (nome) values ('Tailandesa');
insert into tb_cozinha (nome) values ('Indiana');

insert into tb_estado (nome) values ('Minas Gerais');
insert into tb_estado (nome) values ('São Paulo');
insert into tb_estado (nome) values ('Ceará');

insert into tb_cidade (nome, estado_id) values ('Uberlândia', 1);
insert into tb_cidade (nome, estado_id) values ('Belo Horizonte', 1);
insert into tb_cidade (nome, estado_id) values ('São Paulo', 2);
insert into tb_cidade (nome, estado_id) values ('Campinas', 2);
insert into tb_cidade (nome, estado_id) values ('Fortaleza', 3);

insert into tb_restaurante (nome, taxa_frete, cozinha_id) values ('Thai Gourmet', 10, 1);
insert into tb_restaurante (nome, taxa_frete, cozinha_id) values ('Thai Delivery', 9.50, 1);
insert into tb_restaurante (nome, taxa_frete, cozinha_id) values ('Tuk Tuk Comida Indiana', 15, 2);
insert into tb_restaurante (nome, taxa_frete, cozinha_id, cidade_id, cep, logradouro, numero, bairro, estado_id) values ('Thai Gourmet', 10, 1, 1, '38400-999', 'Rua João Pinheiro', '1000', 'Centro', 1);

insert into tb_forma_pagamento (descricao) values ('Cartão de crédito');
insert into tb_forma_pagamento (descricao) values ('Cartão de débito');
insert into tb_forma_pagamento (descricao) values ('Dinheiro');
insert into tb_forma_pagamento (descricao) values ('PIX');

insert into tb_permissao (nome, descricao) values ('CONSULTAR_COZINHAS', 'Permite consultar cozinhas');
insert into tb_permissao (nome, descricao) values ('EDITAR_COZINHAS', 'Permite editar cozinhas');

insert into tb_restaurante_forma_pagamento (restaurante_id, forma_pagamento_id) values (1, 1), (1, 2), (1, 3), (2, 3), (3, 2), (3, 3);

