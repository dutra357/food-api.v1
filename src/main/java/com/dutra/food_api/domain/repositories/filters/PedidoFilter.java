package com.dutra.food_api.domain.repositories.filters;

import java.time.LocalDate;

public class PedidoFilter {

    private Long clienteId;
    private Long restauranteId;
    private LocalDate dataCriacaoInicio;
    private LocalDate dataCriacaoFim;


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

    public LocalDate getDataCriacaoInicio() {
        return dataCriacaoInicio;
    }

    public void setDataCriacaoInicio(LocalDate dataCriacaoInicio) {
        this.dataCriacaoInicio = dataCriacaoInicio;
    }

    public LocalDate getDataCriacaoFim() {
        return dataCriacaoFim;
    }

    public void setDataCriacaoFim(LocalDate dataCriacaoFim) {
        this.dataCriacaoFim = dataCriacaoFim;
    }
}
