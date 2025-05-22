package com.dutra.food_api.domain.repositories.filters;

import com.dutra.food_api.domain.models.enumerations.StatusPedido;

import java.time.OffsetDateTime;

public class VendaDiariaFilter {

    private OffsetDateTime dataCriacaoInicio;
    private OffsetDateTime dataCriacaoFim;
    private Long restauranteId;
    private StatusPedido statusPedido;

    public VendaDiariaFilter() {
        //construtor vazio para Spring
    }

    public Long getRestauranteId() {
        return restauranteId;
    }

    public void setRestauranteId(Long restauranteId) {
        this.restauranteId = restauranteId;
    }

    public StatusPedido getStatusPedido() {
        return statusPedido;
    }

    public void setStatusPedido(StatusPedido statusPedido) {
        this.statusPedido = statusPedido;
    }

    public OffsetDateTime getDataCriacaoInicio() {
        return dataCriacaoInicio;
    }

    public void setDataCriacaoInicio(OffsetDateTime dataCriacaoInicio) {
        this.dataCriacaoInicio = dataCriacaoInicio;
    }

    public OffsetDateTime getDataCriacaoFim() {
        return dataCriacaoFim;
    }

    public void setDataCriacaoFim(OffsetDateTime dataCriacaoFim) {
        this.dataCriacaoFim = dataCriacaoFim;
    }
}
