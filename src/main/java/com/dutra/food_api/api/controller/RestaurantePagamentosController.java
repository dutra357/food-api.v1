package com.dutra.food_api.api.controller;

import com.dutra.food_api.api.model.output.FormaPagamentoOutput;
import com.dutra.food_api.domain.services.interfaces.CadastroRestauranteInterface;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/restaurantes/{restauranteId}/formas-pagamento")
public class RestaurantePagamentosController {


    public final CadastroRestauranteInterface cadastroRestaurante;
    public RestaurantePagamentosController(CadastroRestauranteInterface cadastroRestaurante) {
        this.cadastroRestaurante = cadastroRestaurante;
    }

    @GetMapping
    public ResponseEntity<List<FormaPagamentoOutput>> buscarFormasRestaurante(@PathVariable Long restauranteId) {
        return ResponseEntity.ok(cadastroRestaurante.buscarFormasPagamento(restauranteId));
    }

    @PostMapping("/{formaPagamentoId}")
    public ResponseEntity<Void> associarFormaPagamento(@PathVariable Long restauranteId,
                                                      @PathVariable Long formaPagamentoId) {
        cadastroRestaurante.associarFormaPagamento(restauranteId, formaPagamentoId);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{formaPagamentoId}")
    public ResponseEntity<Void> removerFormaPagamento(@PathVariable Long restauranteId,
                                                      @PathVariable Long formaPagamentoId) {
        cadastroRestaurante.removerFormaPagamento(restauranteId, formaPagamentoId);
        return ResponseEntity.noContent().build();
    }


}
