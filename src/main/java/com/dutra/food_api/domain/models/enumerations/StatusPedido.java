package com.dutra.food_api.domain.models.enumerations;

public enum StatusPedido {

    CRIADO("Criado"),
    CONFIRMADO("Confirmado"),
    CANCELADO("Cancelado"),
    ENTREGUE("Entregue");

    private String descricao;

    private StatusPedido(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }
}
