package com.dutra.food_api.domain.repositories.repo;

import com.dutra.food_api.domain.models.Restaurante;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface RestauranteRepository extends JpaRepository<Restaurante, Long> {
}
