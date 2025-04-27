package com.dutra.food_api.domain.repositories;

import com.dutra.food_api.domain.models.Cozinha;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CozinhaRepository extends JpaRepository<Cozinha, Long>{

}