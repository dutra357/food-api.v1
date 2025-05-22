package com.dutra.food_api.domain.services.interfaces;

import com.dutra.food_api.api.model.metrics.VendaDiaria;
import com.dutra.food_api.domain.repositories.filters.VendaDiariaFilter;

import java.util.List;

public interface VendasMetricasInterface {

    List<VendaDiaria> consultarVendasDiarias(VendaDiariaFilter filtro);
}
