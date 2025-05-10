package com.dutra.food_api.api.controller;

import com.dutra.food_api.api.model.input.CozinhaInput;
import com.dutra.food_api.api.model.output.CozinhaOutput;
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
    public CozinhaOutput adicionar(@RequestBody CozinhaInput cozinhaInput) {
        return cadastroCozinhaService.salvarCozinha(cozinhaInput);
    }

    @PutMapping("/{cozinhaId}")
    public ResponseEntity<CozinhaOutput> atualizar(@PathVariable Long cozinhaId,
                                             @RequestBody CozinhaInput cozinhaInput) {
        return ResponseEntity.ok(cadastroCozinhaService.atualizarCozinha(cozinhaId, cozinhaInput));
    }

    @GetMapping
    public ResponseEntity<List<CozinhaOutput>> buscarTodas() {
        return ResponseEntity.ok(cadastroCozinhaService.buscarTodas());
    }

    @GetMapping("/{cozinhaId}")
    public ResponseEntity<CozinhaOutput> buscar(@PathVariable Long cozinhaId) {
        return ResponseEntity.ok(cadastroCozinhaService.buscar(cozinhaId));
    }


    @DeleteMapping("/{cozinhaId}")
    public ResponseEntity<Void> remover(@PathVariable Long cozinhaId) {
        cadastroCozinhaService.remover(cozinhaId);
        return ResponseEntity.noContent().build();
    }
}