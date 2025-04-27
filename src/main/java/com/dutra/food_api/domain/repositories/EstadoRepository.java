package com.dutra.food_api.domain.repositories;

import com.dutra.food_api.domain.models.Estado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EstadoRepository extends JpaRepository<Estado, Long>{

}
