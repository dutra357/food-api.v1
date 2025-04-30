package com.dutra.food_api.domain.repositories.specifications;

import com.dutra.food_api.domain.models.Restaurante;
import org.springframework.data.jpa.domain.Specification;

public class SpecFactory {

    public static Specification<Restaurante> comFreteGratis() {
        return (root, query, builder) ->
                builder.equal(root.get("taxaFrete"), 0.0);
    }

    public static Specification<Restaurante> comNomeSemelhante(String nome) {
        return (root, query, builder) ->
                builder.like(root.get("nome"), "%" + nome + "%");
    }
}
