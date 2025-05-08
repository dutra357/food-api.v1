package com.dutra.food_api.domain.repositories.impl;

import com.dutra.food_api.api.model.RestauranteOutput;

import java.math.BigDecimal;
import java.util.List;

public interface RestauranteRepositoryQueries {

    List<RestauranteOutput> find(String nome,
                           BigDecimal taxaFreteInicial, BigDecimal taxaFreteFinal);

    List<RestauranteOutput> findComFreteGratis(String nome);
}
