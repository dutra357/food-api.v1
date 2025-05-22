package com.dutra.food_api.api.model.metrics;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

public class VendaDiariaOutput {

    private OffsetDateTime dataVendas;
    private Long totalVendas;
    private BigDecimal totalFaturado;

    public VendaDiariaOutput() {

    }

    public VendaDiariaOutput(OffsetDateTime dataVendas,
                             Long totalVendas, BigDecimal totalFaturado) {
        this.dataVendas = dataVendas;
        this.totalVendas = totalVendas;
        this.totalFaturado = totalFaturado;
    }

    public OffsetDateTime getDataVendas() {
        return dataVendas;
    }

    public void setDataVendas(OffsetDateTime dataVendas) {
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
