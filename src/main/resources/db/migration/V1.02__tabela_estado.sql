CREATE TABLE tb_estado (
    id BIGSERIAL PRIMARY KEY,
    nome VARCHAR(80) NOT null
);

insert into tb_estado (nome) select distinct nome from tb_cidade tc;

alter table tb_cidade add column estado_id bigint not null;

UPDATE tb_cidade SET estado_id = (SELECT e.id FROM tb_estado e WHERE e.nome = tb_cidade.nome_estado);

alter table tb_cidade add constraint fk_cidade_estado
	foreign key (estado_id) references tb_estado (id);

alter table tb_cidade drop column nome_estado;