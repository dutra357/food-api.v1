package com.dutra.food_api.domain.repositories;

import com.dutra.food_api.domain.models.Cozinha;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CozinhaRepository extends JpaRepository<Cozinha, Long>{

    List<Cozinha> findFirstByNomeContaining(String nome);

    boolean existsByNome(String nome);

    int countByNome(String nome);
}