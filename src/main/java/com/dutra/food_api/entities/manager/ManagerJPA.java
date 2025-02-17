package com.dutra.food_api.entities.manager;

import com.dutra.food_api.entities.Cozinha;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ManagerJPA {

    @PersistenceContext
    private EntityManager manager;

    public List<Cozinha> listarCozinhas() {

        TypedQuery<Cozinha> query = manager.createQuery("from Cozinha", Cozinha.class);
        return query.getResultList();
    }
}
