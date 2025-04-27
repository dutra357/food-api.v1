package com.dutra.food_api.api.controller;

import com.dutra.food_api.domain.models.Restaurante;
import com.dutra.food_api.domain.services.CadastroRestauranteService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/restaurantes")
public class RestauranteController {

    private final CadastroRestauranteService cadastroRestauranteService;
    public RestauranteController(CadastroRestauranteService cadastroRestauranteService) {
        this.cadastroRestauranteService = cadastroRestauranteService;
    }


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Restaurante> salvar(@RequestBody Restaurante restaurante) {
        return ResponseEntity.ok(cadastroRestauranteService.salvar(restaurante));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Restaurante> buscar(@PathVariable Long id) {
        return ResponseEntity.ok(cadastroRestauranteService.buscar(id));
    }

    @GetMapping()
    public ResponseEntity<List<Restaurante>> buscarTodos() {
        return ResponseEntity.ok(cadastroRestauranteService.buscarTodos());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Restaurante> atualizar(@PathVariable Long id,
                                                 @RequestBody Restaurante restaurante) {
        return ResponseEntity.ok(cadastroRestauranteService.salvar(restaurante));
    }
}
