package com.dutra.food_api.api.controller;

import com.dutra.food_api.api.model.input.ProdutoInput;
import com.dutra.food_api.api.model.output.ProdutoOutput;
import com.dutra.food_api.domain.services.interfaces.CadastroProdutoInterface;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {

    private final CadastroProdutoInterface cadastroProdutoService;
    public ProdutoController(CadastroProdutoInterface cadastroProdutoService) {
        this.cadastroProdutoService = cadastroProdutoService;
    }

    @GetMapping
    public ResponseEntity<List<ProdutoOutput>> listarTodos() {
        return ResponseEntity.ok(cadastroProdutoService.buscarTodas());
    }

    @GetMapping("/{produtoId}")
    public ResponseEntity<ProdutoOutput> buscarPorId(@PathVariable Long produtoId) {
        return ResponseEntity.ok(cadastroProdutoService.buscarPorId(produtoId));
    }

    @PutMapping("/{produtoId}")
    public ResponseEntity<ProdutoOutput> atualizar(@PathVariable Long produtoId, @RequestBody @Valid ProdutoInput produtoInput) {
        return ResponseEntity.ok(cadastroProdutoService.atualizar(produtoId, produtoInput));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<ProdutoOutput> salvar(@RequestBody @Valid ProdutoInput produtoInput) {
        return ResponseEntity.ok(cadastroProdutoService.salvar(produtoInput));
    }

    @DeleteMapping("/{produtoId}")
    public ResponseEntity<Void> deletar(@PathVariable Long produtoId) {
        cadastroProdutoService.excluir(produtoId);
        return ResponseEntity.noContent().build();
    }

}
