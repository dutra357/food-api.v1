package com.dutra.food_api.domain.repositories;


import com.dutra.food_api.domain.models.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long> {

    @Query("from Produto where restaurante.id = :restaurante and id = :produto")
    Optional<Produto> findById(@Param("restaurante") Long restauranteId,
                               @Param("produto") Long produtoId);

    @Query("from Produto p where p.ativo = true and p.restaurante.id = :restauranteId")
    List<Produto> findAtivoByRestaurante(Long restauranteId);

    @Query("from Produto p where p.restaurante.id = :restauranteId")
    List<Produto> findAllByRestaurante(Long restauranteId);

    @Query("SELECT p FROM Produto p WHERE p.id IN :ids AND p.restaurante.id = :restauranteId")
    List<Produto> buscarPorIdsEPorRestaurante(List<Long> ids, Long restauranteId);
}
