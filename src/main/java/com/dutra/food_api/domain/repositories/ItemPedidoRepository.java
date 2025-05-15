package com.dutra.food_api.domain.repositories;

import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemPedidoRepository extends JpaEntityInformation<ItemPedidoRepository, Long> {
}
