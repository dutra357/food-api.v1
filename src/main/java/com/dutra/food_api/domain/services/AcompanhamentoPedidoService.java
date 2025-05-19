package com.dutra.food_api.domain.services;

import com.dutra.food_api.domain.models.Pedido;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AcompanhamentoPedidoService {

    private final CadastroPedidosService pedidoService;

    public AcompanhamentoPedidoService(CadastroPedidosService pedidoService) {
        this.pedidoService = pedidoService;
    }

    @Transactional
    public void confirmar(Long codigoPedido) {
        Pedido pedido = pedidoService.buscaPorPedido(codigoPedido);
        pedido.setStatus(pedido.getStatus().());
    }
}
