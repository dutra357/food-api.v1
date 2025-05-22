package com.dutra.food_api.domain.services.interfaces;

import com.dutra.food_api.api.model.input.PedidoInput;
import com.dutra.food_api.api.model.output.PedidoOutput;
import com.dutra.food_api.api.model.output.PedidoOutputShort;
import com.dutra.food_api.domain.models.Pedido;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;


import java.util.List;

public interface CadastroPedidosInterface {

    PedidoOutput buscarPorId(Long pedidoId);

    List<PedidoOutputShort> buscarTodos();

    Page<PedidoOutputShort> buscarTodosComSpec(Pageable pageable, Specification<Pedido> specification);

    PedidoOutput salvar(PedidoInput pedidoInput);

    PedidoOutput buscarPedidoPorCodigo(String codigo);
}
