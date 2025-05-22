package com.dutra.food_api.domain.services;

import com.dutra.food_api.api.model.metrics.VendaDiariaOutput;
import com.dutra.food_api.domain.models.Pedido;
import com.dutra.food_api.domain.models.enumerations.StatusPedido;
import com.dutra.food_api.domain.repositories.filters.VendaDiariaFilter;
import com.dutra.food_api.domain.services.interfaces.VendasMetricasInterface;
import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class VendasMetricasService implements VendasMetricasInterface {

    private final EntityManager entityManager;
    public VendasMetricasService(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public List<VendaDiariaOutput> consultarVendasDiarias(VendaDiariaFilter filtro) {

        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<VendaDiariaOutput> cq = cb.createQuery(VendaDiariaOutput.class);
        Root<Pedido> root = cq.from(Pedido.class);

        var dataCriacao = cb.function("date", OffsetDateTime.class, root.get("dataCriacao"));

        // Seleciona os campos agregados
        cq.select(cb.construct(VendaDiariaOutput.class,
                dataCriacao,
                cb.count(root.get("id")),
                cb.sum(root.get("valorTotal"))
        ));

        // Aqui montamos a lista de filtros dinamicamente
        List<Predicate> predicates = new ArrayList<>();

        if (filtro.getDataCriacaoInicio() != null) {
            predicates.add(cb.greaterThanOrEqualTo(root.get("dataCriacao"), filtro.getDataCriacaoInicio()));
        }

        if (filtro.getDataCriacaoFim() != null) {
            predicates.add(cb.lessThanOrEqualTo(root.get("dataCriacao"), filtro.getDataCriacaoFim()));
        }

        if (filtro.getRestauranteId() != null) {
            predicates.add(cb.equal(root.get("restaurante").get("id"), filtro.getRestauranteId()));
        }

        //Predicado fixo. Apenas pedidos entregues ou confirmados (faturados).
        predicates.add(root.get("status").in(StatusPedido.CONFIRMADO, StatusPedido.ENTREGUE));

        cq.where(predicates.toArray(new Predicate[0]));
        cq.groupBy(dataCriacao);

        return entityManager.createQuery(cq).getResultList();
    }
}
