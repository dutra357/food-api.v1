package com.dutra.food_api.domain.repositories.specifications;

import com.dutra.food_api.domain.models.Restaurante;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.data.jpa.domain.Specification;

import java.math.BigDecimal;

public class RestauranteComFreteGratisSpec implements Specification<Restaurante>{

    @Override
    public Predicate toPredicate(Root<Restaurante> root,
                                 CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        
        return criteriaBuilder.equal(root.get("taxaFrete"), BigDecimal.ZERO);
    }
}
