package com.dutra.food_api.api.controller;

import com.dutra.food_api.domain.models.Cozinha;
import com.dutra.food_api.domain.services.CadastroCozinhaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/cozinhas")
public class CozinhaController {

    private final CadastroCozinhaService cadastroCozinhaService;
    public CozinhaController(CadastroCozinhaService cadastroCozinhaService) {
        this.cadastroCozinhaService = cadastroCozinhaService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Cozinha adicionar(@RequestBody Cozinha cozinha) {
        return cadastroCozinhaService.salvarCozinha(cozinha);
    }

    @DeleteMapping("/{cozinhaId}")
    public ResponseEntity<Void> remover(@PathVariable Long cozinhaId) {
        cadastroCozinhaService.remover(cozinhaId);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{cozinhaId}")
    public ResponseEntity<Cozinha> atualizar(@PathVariable Long cozinhaId,
                                             @RequestBody Cozinha cozinha) {
        return ResponseEntity.ok(cadastroCozinhaService.atualizarCozinha(cozinhaId, cozinha));
    }

    @GetMapping
    public ResponseEntity<List<Cozinha>> buscarTodas() {
        return ResponseEntity.ok(cadastroCozinhaService.buscarTodas());
    }

    @GetMapping("/{cozinhaId}")
    public ResponseEntity<Cozinha> buscar(@PathVariable Long cozinhaId) {
        return ResponseEntity.ok(cadastroCozinhaService.buscar(cozinhaId));
    }
}