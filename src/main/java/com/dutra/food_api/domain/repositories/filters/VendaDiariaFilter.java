package com.dutra.food_api.domain.repositories.filters;

import java.time.LocalDate;

public class VendaDiariaFilter {

    private LocalDate dataCriacaoInicio;
    private LocalDate dataCriacaoFim;


    public VendaDiariaFilter() {
        //construtor vazio para Spring
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
