package com.dutra.food_api.api.controller;

import com.dutra.food_api.api.model.output.CozinhaOutput;
import com.dutra.food_api.domain.services.AcompanhamentoPedidoService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/pedidos/{codigoPedido}")
public class FluxoPedidoController {

    private final AcompanhamentoPedidoService acompanhamentoPedidoService;

    public FluxoPedidoController(AcompanhamentoPedidoService acompanhamentoPedidoService) {
        this.acompanhamentoPedidoService = acompanhamentoPedidoService;
    }


    @PutMapping("/confirmacao")
    public ResponseEntity<CozinhaOutput> atualizar(@PathVariable Long codigoPedido) {
        acompanhamentoPedidoService.confirmar(codigoPedido);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/cancelamento")
    public ResponseEntity<CozinhaOutput> cancelar(@PathVariable Long codigoPedido) {
        acompanhamentoPedidoService.cancelar(codigoPedido);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/entrega")
    public ResponseEntity<CozinhaOutput> entregue(@PathVariable Long codigoPedido) {
        acompanhamentoPedidoService.entregue(codigoPedido);
        return ResponseEntity.ok().build();
    }
}
