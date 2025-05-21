package com.dutra.food_api.domain.repositories.specifications;

import com.dutra.food_api.domain.models.Pedido;
import com.dutra.food_api.domain.repositories.filters.PedidoFilter;
import org.springframework.data.jpa.domain.Specification;

import jakarta.persistence.criteria.Predicate;
import java.util.ArrayList;

public class PedidosFilterSpec {

    public static Specification<Pedido> usingFilter(PedidoFilter filter) {
        return (root, query, builder) -> {
            var predicates = new ArrayList<Predicate>();

            if (filter.getClienteId() != null) {
                predicates.add(builder.equal(builder.toLong(root.get("cliente").get("id")),filter.getClienteId()));
            }

            return builder.and(predicates.toArray(new Predicate[0]));
        };
    }


}
