package com.dutra.food_api.api.model.input;

import com.dutra.food_api.domain.models.Produto;

import java.math.BigDecimal;

public class ProdutoInput {

    private String nome;
    private String descricao;
    private BigDecimal preco;
    private Boolean ativo;

    public ProdutoInput() {}

    public ProdutoInput(String nome, String descricao,
                        BigDecimal preco, Boolean ativo) {
        this.nome = nome;
        this.descricao = descricao;
        this.preco = preco;
        this.ativo = ativo;
    }

    public Produto toEntity() {
        Produto  produto = new Produto();
        produto.setNome(nome);
        produto.setDescricao(descricao);
        produto.setPreco(preco);
        produto.setAtivo(ativo);
        return produto;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public BigDecimal getPreco() {
        return preco;
    }

    public void setPreco(BigDecimal preco) {
        this.preco = preco;
    }

    public Boolean getAtivo() {
        return ativo;
    }

    public void setAtivo(Boolean ativo) {
        this.ativo = ativo;
    }
}
