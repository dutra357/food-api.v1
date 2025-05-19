package com.dutra.food_api.domain.models.enumerations;

import java.util.Arrays;
import java.util.List;

public enum StatusPedido {

    CRIADO("Criado"),
    CONFIRMADO("Confirmado", CRIADO),
    ENTREGUE("Entregue", CONFIRMADO),
    CANCELADO("Cancelado", CRIADO, CONFIRMADO);

    private final String descricao;
    private final List<StatusPedido> historicoStatus;

    private StatusPedido(String descricao, StatusPedido... historicoStatus) {
        this.descricao = descricao;
        this.historicoStatus = Arrays.asList(historicoStatus);
    }

    public boolean naoPodeAlterarPara(StatusPedido novoStatus) {
        return !novoStatus.historicoStatus.contains(this);
    }

    public String getDescricao() {
        return descricao;
    }

    public List<StatusPedido> getHistoricoStatus() {
        return historicoStatus;
    }
}
