package com.dutra.food_api.domain.repositories;

import com.dutra.food_api.api.model.output.PedidoOutputShort;
import com.dutra.food_api.domain.models.Pedido;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface PedidosRepository extends JpaRepository<Pedido, Long>, JpaSpecificationExecutor<Pedido> {

    Page<Pedido> findAll(Specification<Pedido> specification, Pageable pageable);

    @Query("from Pedido WHERE codigo = :codigo")
    Optional<Pedido> buscarPedidoPorCodigo(UUID codigo);

    @EntityGraph(attributePaths = {
            "cliente",
            "restaurante",
            "restaurante.cozinha",
            "formaPagamento",
            "enderecoEntrega.cidade",
            "enderecoEntrega.cidade.estado",
            "itemPedido",
            "itemPedido.produto"
    })
    @Query("SELECT p FROM Pedido p")
    List<Pedido> buscarPedidosComDetalhes();

    @EntityGraph(attributePaths = {
            "cliente",
            "restaurante",
            "restaurante.cozinha",
            "formaPagamento",
            "enderecoEntrega.cidade",
            "enderecoEntrega.cidade.estado",
            "itemPedido",
            "itemPedido.produto"
    })
    @Query("SELECT p FROM Pedido p WHERE p.id = :id")
    List<Pedido> buscarPedidoId(Long id);

}
