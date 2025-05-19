package com.dutra.food_api.domain.services;

import com.dutra.food_api.domain.models.Pedido;
import com.dutra.food_api.domain.models.enumerations.StatusPedido;
import com.dutra.food_api.domain.services.exceptions.NegotioException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.OffsetDateTime;

@Service
public class AcompanhamentoPedidoService {

    private final CadastroPedidosService pedidoService;

    public AcompanhamentoPedidoService(CadastroPedidosService pedidoService) {
        this.pedidoService = pedidoService;
    }

    @Transactional
    public void confirmar(Long codigoPedido) {
        Pedido pedido = pedidoService.buscaPorPedido(codigoPedido);

        if (!pedido.getStatus().equals(StatusPedido.CANCELADO)) {
            throw new NegotioException(
                    String.format("Pedido cancelado. Status do pedido %d não pode ser alterado.", codigoPedido)
            );
        }

        if  (pedido.getStatus().equals(StatusPedido.CONFIRMADO)) {
            throw new NegotioException(
                    String.format("Pedido já confirmado. ID: %d.", codigoPedido)
            );
        }

        if (pedido.getStatus().equals(StatusPedido.ENTREGUE)) {
            throw new NegotioException(
                    String.format("Pedido já entregue. ID: %d.", codigoPedido)
            );
        }

        pedido.confirmar();
    }

    @Transactional
    public void cancelar(Long codigoPedido) {
        Pedido pedido = pedidoService.buscaPorPedido(codigoPedido);

        if (!pedido.getStatus().equals(StatusPedido.CANCELADO)) {
            throw new NegotioException(
                    String.format("Pedido já cancelado. ID: %d.", codigoPedido)
            );
        }

        pedido.cancelar();
    }

    @Transactional
    public void entregue(Long codigoPedido) {
        Pedido pedido = pedidoService.buscaPorPedido(codigoPedido);

        if (!pedido.getStatus().equals(StatusPedido.CONFIRMADO)) {
            throw new NegotioException(
                    String.format("Para atualizar o pedido ID %d como 'ENTREGUE', deve antes ser 'CONFIRMADO'.", codigoPedido)
            );
        }

        if (pedido.getStatus().equals(StatusPedido.ENTREGUE)) {
            throw new NegotioException(
                    String.format("Pedido já entregue. ID: %d.", codigoPedido)
            );
        }

        if (pedido.getStatus().equals(StatusPedido.CANCELADO)) {
            throw new NegotioException(
                    String.format("Pedido já cancelado. ID: %d.", codigoPedido)
            );
        }

        pedido.entregue();
    }
}
