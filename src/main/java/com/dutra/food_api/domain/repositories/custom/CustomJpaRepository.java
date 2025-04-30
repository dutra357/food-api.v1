package com.dutra.food_api.domain.repositories.custom;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.Optional;

@NoRepositoryBean
public interface CustomJpaRepository<T, I> extends JpaRepository<T, I> {

    Optional<T> buscarPrimeiro();
}
