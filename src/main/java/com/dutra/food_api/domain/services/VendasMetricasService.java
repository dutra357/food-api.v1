package com.dutra.food_api.domain.services;

import com.dutra.food_api.api.model.metrics.VendaDiaria;
import com.dutra.food_api.domain.models.Pedido;
import com.dutra.food_api.domain.repositories.filters.VendaDiariaFilter;
import com.dutra.food_api.domain.services.interfaces.VendasMetricasInterface;
import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class VendasMetricasService implements VendasMetricasInterface {

    private final EntityManager entityManager;

    public VendasMetricasService(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public List<VendaDiaria> consultarVendasDiarias(VendaDiariaFilter filtro) {

        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();

        var criteriaQuery = criteriaBuilder.createQuery(VendaDiaria.class);
        var root = criteriaQuery.from(Pedido.class);

        var funcionDate = criteriaBuilder.function(
                "date", LocalDate.class, root.get("dataCriacao"));

        var selection = criteriaBuilder.construct(VendaDiaria.class,
                funcionDate,
                criteriaBuilder.count(root.get("id")),
                criteriaBuilder.sum(root.get("valorTotal")));

        criteriaQuery.select(selection);
        criteriaQuery.groupBy(funcionDate);

        return entityManager.createQuery(criteriaQuery).getResultList();
    }
}
