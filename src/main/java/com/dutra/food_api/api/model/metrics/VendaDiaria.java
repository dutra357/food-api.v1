package com.dutra.food_api.api.model.metrics;

import java.math.BigDecimal;
import java.time.LocalDate;

public class VendaDiaria {

    private LocalDate dataVendas;
    private Long totalVendas;
    private BigDecimal totalFaturado;

    public VendaDiaria() {

    }

    public VendaDiaria(LocalDate dataVendas,
                       Long totalVendas, BigDecimal totalFaturado) {
        this.dataVendas = dataVendas;
        this.totalVendas = totalVendas;
        this.totalFaturado = totalFaturado;
    }

    public LocalDate getDataVendas() {
        return dataVendas;
    }

    public void setDataVendas(LocalDate dataVendas) {
        this.dataVendas = dataVendas;
    }

    public Long getTotalVendas() {
        return totalVendas;
    }

    public void setTotalVendas(Long totalVendas) {
        this.totalVendas = totalVendas;
    }

    public BigDecimal getTotalFaturado() {
        return totalFaturado;
    }

    public void setTotalFaturado(BigDecimal totalFaturado) {
        this.totalFaturado = totalFaturado;
    }
}
