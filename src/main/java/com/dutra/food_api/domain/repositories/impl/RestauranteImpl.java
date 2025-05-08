package com.dutra.food_api.domain.repositories.impl;

import com.dutra.food_api.api.model.RestauranteOutput;
import com.dutra.food_api.domain.models.Restaurante;
import com.dutra.food_api.domain.repositories.RestauranteRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static com.dutra.food_api.domain.repositories.specifications.SpecFactory.comFreteGratis;
import static com.dutra.food_api.domain.repositories.specifications.SpecFactory.comNomeSemelhante;

@Repository
public class RestauranteImpl implements RestauranteRepositoryQueries {

    @PersistenceContext
    private final EntityManager manager;
    private final RestauranteRepository restauranteRepository;

    public RestauranteImpl(EntityManager manager, RestauranteRepository restauranteRepository) {
        this.manager = manager;
        this.restauranteRepository = restauranteRepository;
    }


    // * Método para Criteria API
    @Override
    public List<RestauranteOutput> find(String nome,
                                        BigDecimal taxaFreteInicial, BigDecimal taxaFreteFinal) {

        CriteriaBuilder builder = manager.getCriteriaBuilder();
        CriteriaQuery<Restaurante> criteria = builder.createQuery(Restaurante.class);
        Root<Restaurante> root = criteria.from(Restaurante.class);

        List<Predicate> predicates = new ArrayList<>();

        if (StringUtils.hasText(nome)) {
            predicates.add(builder.like(root.get("nome"), "%" + nome + "%"));
        }

        if (taxaFreteInicial != null) {
            predicates.add(builder.greaterThanOrEqualTo(root.get("taxaFrete"), taxaFreteInicial));
        }

        if (taxaFreteFinal != null) {
            predicates.add(builder.lessThanOrEqualTo(root.get("taxaFrete"), taxaFreteFinal));
        }

        criteria.where(predicates.toArray(new Predicate[0])); //Converte ArrayList em array

        TypedQuery<Restaurante> query = manager.createQuery(criteria);
        return query.getResultList().stream().map(RestauranteOutput::toRestauranteOutput).toList();
    }

    //Mètodo para Specializations
    @Override
    public List<RestauranteOutput> findComFreteGratis(String nome) {
        //Import estático
        return restauranteRepository.findAll(comFreteGratis()
                .and(comNomeSemelhante(nome)))
                .stream().map(RestauranteOutput::toRestauranteOutput).toList();
    }
}
