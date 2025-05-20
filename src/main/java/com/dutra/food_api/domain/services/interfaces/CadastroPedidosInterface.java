package com.dutra.food_api.domain.services.interfaces;

import com.dutra.food_api.api.model.input.PedidoInput;
import com.dutra.food_api.api.model.output.PedidoOutput;
import com.dutra.food_api.api.model.output.PedidoOutputShort;

import java.util.List;

public interface CadastroPedidosInterface {

    PedidoOutput buscarPorId(Long pedidoId);

    List<PedidoOutputShort> buscarTodos();

    PedidoOutput salvar(PedidoInput pedidoInput);

    PedidoOutput buscarPedidoPorCodigo(String codigo);
}
