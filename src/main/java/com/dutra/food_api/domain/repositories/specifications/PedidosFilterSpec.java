package com.dutra.food_api.domain.repositories.specifications;

import com.dutra.food_api.domain.models.Pedido;
import com.dutra.food_api.domain.repositories.filters.PedidoFilter;
import org.springframework.data.jpa.domain.Specification;

import jakarta.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;

public class PedidosFilterSpec {

    public static Specification<Pedido> usingFilter(PedidoFilter filter) {
        return (root, query, builder) -> {

            if (Pedido.class.equals(query.getResultType())) {
                var restauranteFetch = root.fetch("restaurante");
                restauranteFetch.fetch("cozinha");
                root.fetch("cliente");
                root.fetch("formaPagamento");
            }

            // Evita duplicatas no resultado
            if (query.getResultType() != Long.class) {
                query.distinct(true);
            }

            List<Predicate> predicates = new ArrayList<>();

            if (filter.getClienteId() != null) {
                predicates.add(builder.equal(builder.toLong(root.get("cliente").get("id")),filter.getClienteId()));
            }

            if (filter.getRestauranteId() != null) {
                predicates.add(builder.equal(builder.toLong(root.get("restaurante").get("id")),filter.getRestauranteId()));
            }

            if (filter.getDataCriacaoInicio() != null) {
                predicates.add(builder.greaterThanOrEqualTo(root.get("dataCriacao"),filter.getDataCriacaoInicio()));
            }

            if (filter.getDataCriacaoFim() != null) {
                predicates.add(builder.lessThan(root.get("dataCriacao"),filter.getDataCriacaoFim()));
            }

            return builder.and(predicates.toArray(new Predicate[0]));
        };
    }


}
