package com.dutra.food_api.api.controller;

import com.dutra.food_api.api.model.input.PedidoInput;
import com.dutra.food_api.api.model.output.PedidoOutput;
import com.dutra.food_api.api.model.output.PedidoOutputShort;
import com.dutra.food_api.domain.services.interfaces.CadastroPedidosInterface;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/codigo/{codigo}")
    public ResponseEntity<PedidoOutput> buscarPorCodigo(@PathVariable String codigo){
        return ResponseEntity.ok(cadastropedidosService.buscarPedidoPorCodigo(codigo));
    }

    @GetMapping
    public ResponseEntity<List<PedidoOutputShort>> buscarTodos(){
        return ResponseEntity.ok(cadastropedidosService.buscarTodos());
    }

    @PostMapping
    public ResponseEntity<PedidoOutput> salvar(@RequestBody @Valid PedidoInput pedidoInput){
        return ResponseEntity.ok(cadastropedidosService.salvar(pedidoInput));
    }
}
