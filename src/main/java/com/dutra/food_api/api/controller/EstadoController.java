package com.dutra.food_api.api.controller;

import com.dutra.food_api.api.model.input.EstadoInput;
import com.dutra.food_api.api.model.output.EstadoOutput;
import com.dutra.food_api.domain.models.Estado;
import com.dutra.food_api.domain.services.CadastroEstadoService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/estados")
public class EstadoController {

    private final CadastroEstadoService cadastroEstadoService;
    public EstadoController(CadastroEstadoService cadastroEstadoService) {
        this.cadastroEstadoService = cadastroEstadoService;
    }

    @GetMapping
    public ResponseEntity<List<EstadoOutput>> listarTodos() {
        return ResponseEntity.ok(cadastroEstadoService.buscarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<EstadoOutput> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(cadastroEstadoService.buscarPorId(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<EstadoOutput> atualizar(@PathVariable Long id, @RequestBody @Valid EstadoInput  estadoInput) {
        return ResponseEntity.ok(cadastroEstadoService.atualizar(id, estadoInput));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<EstadoOutput> salvar(@RequestBody @Valid EstadoInput  estadoInput) {
        return ResponseEntity.ok(cadastroEstadoService.salvar(estadoInput));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        cadastroEstadoService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
