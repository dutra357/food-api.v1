package com.dutra.food_api.api.controller;

import com.dutra.food_api.api.model.output.ProdutoOutput;
import com.dutra.food_api.domain.services.interfaces.CadastroRestauranteInterface;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/restaurantes/{restauranteId}/produtos")
public class RestauranteProdutosController {

    public final CadastroRestauranteInterface cadastroRestaurante;
    public RestauranteProdutosController(CadastroRestauranteInterface cadastroRestaurante) {
        this.cadastroRestaurante = cadastroRestaurante;
    }

    @GetMapping
    public ResponseEntity<List<ProdutoOutput>> buscarProdutos(@PathVariable Long restauranteId) {
        return ResponseEntity.ok(cadastroRestaurante.buscarProdutos(restauranteId));
    }

    @PostMapping("/{produtoId}")
    public ResponseEntity<Void> associarProduto(@PathVariable Long restauranteId,
                                                       @PathVariable Long produtoId) {
        cadastroRestaurante.associarProduto(restauranteId, produtoId);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{produtoId}")
    public ResponseEntity<Void> desassociarProduto(@PathVariable Long restauranteId,
                                                      @PathVariable Long produtoId) {
        cadastroRestaurante.desassociarProduto(restauranteId, produtoId);
        return ResponseEntity.noContent().build();
    }
}
