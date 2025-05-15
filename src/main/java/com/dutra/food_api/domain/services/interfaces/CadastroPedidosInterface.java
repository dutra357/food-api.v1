package com.dutra.food_api.domain.services.interfaces;

import com.dutra.food_api.api.model.output.PedidoOutput;

import java.util.List;

public interface CadastroPedidosInterface {

    PedidoOutput buscarPorId(Long pedidoId);

    List<PedidoOutput> buscarTodos();
}
