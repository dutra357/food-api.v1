package com.dutra.food_api.api.controller;

import com.dutra.food_api.domain.models.Restaurante;
import com.dutra.food_api.domain.services.CadastroRestauranteService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/restaurantes")
public class RestauranteController {

    private final CadastroRestauranteService cadastroRestauranteService;
    public RestauranteController(CadastroRestauranteService cadastroRestauranteService) {
        this.cadastroRestauranteService = cadastroRestauranteService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Restaurante> buscar(@PathVariable Long id) {
        return ResponseEntity.ok(cadastroRestauranteService.buscar(id));
    }

    @GetMapping()
    public ResponseEntity<List<Restaurante>> buscarTodos() {
        return ResponseEntity.ok(cadastroRestauranteService.buscarTodos());
    }
}
