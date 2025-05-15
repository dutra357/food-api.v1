package com.dutra.food_api.api.controller;

import com.dutra.food_api.api.model.output.UsuarioOutput;
import com.dutra.food_api.domain.services.interfaces.CadastroRestauranteInterface;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/restaurantes/{restauranteId}/responsaveis")
public class RestauranteResponsaveisController {

    private final CadastroRestauranteInterface restauranteService;

    public RestauranteResponsaveisController(CadastroRestauranteInterface restauranteService) {
        this.restauranteService = restauranteService;
    }

    @GetMapping
    public ResponseEntity<List<UsuarioOutput>> buscarResponsaveis(@PathVariable Long restauranteId) {
        return ResponseEntity.ok(restauranteService.buscarResponsaveis(restauranteId));
    }

    @PutMapping("/{usuarioId}")
    public ResponseEntity<Void> associarUsuario(@PathVariable Long restauranteId, @PathVariable Long usuarioId) {
        restauranteService.associarUsuario(restauranteId, usuarioId);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{usuarioId}")
    public ResponseEntity<Void> desassociarUsuario(@PathVariable Long restauranteId, @PathVariable Long usuarioId) {
        restauranteService.desassociarUsuario(restauranteId, usuarioId);
        return ResponseEntity.noContent().build();
    }
}
