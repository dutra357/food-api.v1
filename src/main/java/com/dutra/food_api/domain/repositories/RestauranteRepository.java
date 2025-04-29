package com.dutra.food_api.domain.repositories;

import com.dutra.food_api.domain.models.Restaurante;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface RestauranteRepository extends JpaRepository<Restaurante, Long> {

    @Query("from Restaurante where nome like %:parametro% and cozinha.id = :id")
    List<Restaurante> findPersonalizado(String parametro, @Param("id") Long cozinhaId);

    @Query("from Restaurante where nome like %:nome% and taxaFrete between :taxaInicial and :taxaFinal")
    List<Restaurante> findPersonalizadoBetween(String nome, BigDecimal taxaInicial, BigDecimal taxaFinal);

    List<Restaurante> consultaFromXml(String nome, @Param("id") Long cozinhaId);

    List<Restaurante> findByTaxaFreteBetween(BigDecimal taxaInicial, BigDecimal taxaFinal);

    List<Restaurante> findByNomeContainingAndTaxaFrete(String nome, BigDecimal taxaFrete);

    List<Restaurante> findByNomeContainingAndCozinhaId(String nome, Long cozinhaId);

}
