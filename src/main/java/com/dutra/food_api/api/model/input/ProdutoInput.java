package com.dutra.food_api.api.model.input;

import com.dutra.food_api.domain.models.Produto;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public class ProdutoInput {

    @NotNull(message = "Campo requerido")
    private String nome;

    @NotNull(message = "Campo requerido")
    private String descricao;

    @NotNull(message = "Campo requerido")
    @DecimalMin(value = "0", message = "Deve ser informado um valor positivo ou zero para Preço")
    private BigDecimal preco;

    @NotNull(message = "Campo requerido")
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
