package com.dutra.food_api.api.controller;

import com.dutra.food_api.api.model.output.PedidoOutput;
import com.dutra.food_api.domain.services.interfaces.CadastroPedidosInterface;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/pedidos")
public class PedidosController {

    private final CadastroPedidosInterface cadastropedidosService;

    public PedidosController(CadastroPedidosInterface cadastropedidosService) {
        this.cadastropedidosService = cadastropedidosService;
    }

    @GetMapping("/{pedidoId}")
    public ResponseEntity<PedidoOutput> buscarPorId(@PathVariable Long pedidoId){
        return ResponseEntity.ok(cadastropedidosService.buscarPorId(pedidoId));
    }

    @GetMapping
    public ResponseEntity<List<PedidoOutput>> buscarTodos(){
        return ResponseEntity.ok(cadastropedidosService.buscarTodos());
    }
}
