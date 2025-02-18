package com.dutra.food_api.repositories.repo;

import com.dutra.food_api.entities.Cozinha;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CozinhaRepository extends JpaRepository<Cozinha, Long> {

}
