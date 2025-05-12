package com.dutra.food_api.api.model.output;

import com.dutra.food_api.domain.models.FormaPagamento;

import java.util.Objects;

public class FormaPagamentoOutput {

    private Long id;
    private String descricao;

    public FormaPagamentoOutput() {

    }

    public FormaPagamentoOutput(Long id, String descricao) {
        this.id = id;
        this.descricao = descricao;
    }

    public static FormaPagamentoOutput toFormaPagamentoOutput(FormaPagamento formaPagamento) {
        return new FormaPagamentoOutput(formaPagamento.getId(), formaPagamento.getDescricao());
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        FormaPagamentoOutput that = (FormaPagamentoOutput) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
