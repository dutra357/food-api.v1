insert into tb_cozinha (nome) values ('Tailandesa');
insert into tb_cozinha (nome) values ('Indiana');
insert into tb_cozinha (nome) values ('Argentina');
insert into tb_cozinha (nome) values ('Brasileira');

insert into tb_estado (nome) values ('Minas Gerais');
insert into tb_estado (nome) values ('São Paulo');
insert into tb_estado (nome) values ('Ceará');

insert into tb_cidade (nome, estado_id) values ('Uberlândia', 1);
insert into tb_cidade (nome, estado_id) values ('Belo Horizonte', 1);
insert into tb_cidade (nome, estado_id) values ('São Paulo', 2);
insert into tb_cidade (nome, estado_id) values ('Campinas', 2);
insert into tb_cidade (nome, estado_id) values ('Fortaleza', 3);

insert into tb_restaurante (nome, taxa_frete, cozinha_id, cidade_id, cep, logradouro, numero, bairro, estado_id) values ('Thai Gourmet', 10, 1, 1, '38400-000', 'Rua João Pinheiro', '1000', 'Centro', 1);
insert into tb_restaurante (nome, taxa_frete, cozinha_id, cidade_id, cep, logradouro, numero, bairro, estado_id) values ('Java Steakhouse', 12, 3, 2, '12345-245', 'Rua João Pinheiro', '445', 'Cosme', 2);
insert into tb_restaurante (nome, taxa_frete, cozinha_id, cidade_id, cep, logradouro, numero, bairro, estado_id) values ('Lanchonete do Tio Sam', 11, 4, 3, '54367-564', 'Rua Rosa Velha', '23', 'Praia Grande', 3);
insert into tb_restaurante (nome, taxa_frete, cozinha_id, cidade_id, cep, logradouro, numero, bairro, estado_id) values ('Bar da Maria', 6, 4, 1, '38400-465', 'Rua Paulo Alves', '12', 'Pantanal', 2);

insert into tb_forma_pagamento (descricao) values ('Cartão de crédito');
insert into tb_forma_pagamento (descricao) values ('Cartão de débito');
insert into tb_forma_pagamento (descricao) values ('Dinheiro');
insert into tb_forma_pagamento (descricao) values ('PIX');

insert into tb_permissao (nome, descricao) values ('CONSULTAR_COZINHAS', 'Permite consultar cozinhas');
insert into tb_permissao (nome, descricao) values ('EDITAR_COZINHAS', 'Permite editar cozinhas');

insert into tb_produto (nome, descricao, preco, ativo, restaurante_id) values ('Porco com molho agridoce', 'Deliciosa carne suína ao molho especial', 78.90, true, 1);
insert into tb_produto (nome, descricao, preco, ativo, restaurante_id) values ('Camarão tailandês', '16 camarões grandes ao molho picante', 110, true, 1);
insert into tb_produto (nome, descricao, preco, ativo, restaurante_id) values ('Salada picante com carne grelhada', 'Salada de folhas com cortes finos de carne bovina grelhada e nosso molho especial de pimenta vermelha', 87.20, true, 2);
insert into tb_produto (nome, descricao, preco, ativo, restaurante_id) values ('Garlic Naan', 'Pão tradicional indiano com cobertura de alho', 21, true, 3);
insert into tb_produto (nome, descricao, preco, ativo, restaurante_id) values ('Murg Curry', 'Cubos de frango preparados com molho curry e especiarias', 43, true, 3);
insert into tb_produto (nome, descricao, preco, ativo, restaurante_id) values ('Bife Ancho', 'Corte macio e suculento, com dois dedos de espessura, retirado da parte dianteira do contrafilé', 79, true, 4);
insert into tb_produto (nome, descricao, preco, ativo, restaurante_id) values ('T-Bone', 'Corte muito saboroso, com um osso em formato de T, sendo de um lado o contrafilé e do outro o filé mignon', 89, true, 4);
insert into tb_produto (nome, descricao, preco, ativo, restaurante_id) values ('Sanduíche X-Tudo', 'Sandubão com muito queijo, hamburger bovino, bacon, ovo, salada e maionese', 19, true, 2);
insert into tb_produto (nome, descricao, preco, ativo, restaurante_id) values ('Espetinho de Cupim', 'Acompanha farinha, mandioca e vinagrete', 8, true, 2);

insert into tb_grupo (nome) values ('Gerente'), ('Vendedor'), ('Secretária'), ('Cadastrador');

INSERT INTO tb_usuario (nome, email, senha, data_cadastro) VALUES
('João da Silva', 'joao.ger@algafood.com', '123', CURRENT_TIMESTAMP AT TIME ZONE 'UTC'),
('Maria Joaquina', 'maria.vnd@algafood.com', '123', CURRENT_TIMESTAMP AT TIME ZONE 'UTC'),
('José Souza', 'jose.aux@algafood.com', '123', CURRENT_TIMESTAMP AT TIME ZONE 'UTC'),
('Sebastião Martins', 'sebastiao.cad@algafood.com', '123', CURRENT_TIMESTAMP AT TIME ZONE 'UTC');