package com.dutra.food_api.api.model.input;

import com.dutra.food_api.domain.models.FormaPagamento;
import jakarta.validation.constraints.NotNull;

public class FormaPagamentoInput {

    @NotNull(message = "Campo requerido")
    private String descricao;

    public FormaPagamentoInput() {

    }

    public FormaPagamentoInput(String descricao) {
        this.descricao = descricao;
    }

    public FormaPagamento toEntity() {
        FormaPagamento formaPagamento = new FormaPagamento();
        formaPagamento.setDescricao(descricao);
        return formaPagamento;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}
