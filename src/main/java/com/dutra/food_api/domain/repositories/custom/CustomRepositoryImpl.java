package com.dutra.food_api.domain.repositories.custom;

import jakarta.persistence.EntityManager;
import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;

import java.util.Optional;

public class CustomRepositoryImpl<T, I> extends SimpleJpaRepository<T, I> implements CustomJpaRepository<T, I>{

    private final EntityManager entityManager;

    public CustomRepositoryImpl(JpaEntityInformation<T, ?> entityInformation,
                                EntityManager entityManager) {
        super(entityInformation, entityManager);
        this.entityManager = entityManager;
    }

    public CustomRepositoryImpl(Class<T> domainClass,
                                EntityManager entityManager) {
        super(domainClass, entityManager);
        this.entityManager = entityManager;
    }


    @Override
    public Optional<T> buscarPrimeiro() {

        var jpql = "from " + getDomainClass().getName();

        T entity = entityManager.createQuery(jpql, getDomainClass())
                .setMaxResults(1) //limita o resultado a 1
                .getSingleResult();

        if (entity != null) {
            return Optional.of(entity);
        }

        return Optional.ofNullable(entity);
    }
}
