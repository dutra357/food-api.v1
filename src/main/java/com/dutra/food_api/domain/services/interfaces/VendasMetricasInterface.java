package com.dutra.food_api.domain.services.interfaces;

import com.dutra.food_api.api.model.metrics.VendaDiariaOutput;
import com.dutra.food_api.domain.repositories.filters.VendaDiariaFilter;

import java.util.List;

public interface VendasMetricasInterface {

    List<VendaDiariaOutput> consultarVendasDiarias(VendaDiariaFilter filtro);
}
