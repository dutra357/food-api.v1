package com.dutra.food_api.domain.repositories.filters;

import java.time.OffsetDateTime;

public class PedidoFilter {

    private Long clienteId;
    private Long restauranteId;
    private OffsetDateTime dataCriacaoInicio;
    private OffsetDateTime dataCriacaoFim;


    public PedidoFilter() {
        //construtor vazio para Spring
    }

    public Long getClienteId() {
        return clienteId;
    }

    public void setClienteId(Long clienteId) {
        this.clienteId = clienteId;
    }

    public Long getRestauranteId() {
        return restauranteId;
    }

    public void setRestauranteId(Long restauranteId) {
        this.restauranteId = restauranteId;
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
