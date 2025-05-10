package com.dutra.food_api.api.controller;

import com.dutra.food_api.api.model.input.CidadeInput;
import com.dutra.food_api.api.model.output.CidadeOutput;
import com.dutra.food_api.domain.services.CadastroCidadeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cidades")
public class CidadeController {

    private final CadastroCidadeService cadastroCidadeService;

    public CidadeController(CadastroCidadeService cadastroCidadeService) {
        this.cadastroCidadeService = cadastroCidadeService;
    }


    @GetMapping("/{cidadeId}")
    public ResponseEntity<CidadeOutput> buscar(@PathVariable Long cidadeId) {
        return ResponseEntity.ok(cadastroCidadeService.buscarPorId(cidadeId));
    }

    @GetMapping
    public ResponseEntity<List<CidadeOutput>> buscarTodas() {
        return ResponseEntity.ok(cadastroCidadeService.buscarTodas());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<CidadeOutput> salvar(CidadeInput cidadeInput) {
        return ResponseEntity.ok(cadastroCidadeService.salvar(cidadeInput));
    }

    @PutMapping("/{cidadeId}")
    public ResponseEntity<CidadeOutput> atualizar(@PathVariable Long cidadeId, CidadeInput cidadeInput) {
        return ResponseEntity.ok(cadastroCidadeService.atualizar(cidadeId, cidadeInput));
    }

    @DeleteMapping("/{cidadeId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void excluir(@PathVariable Long cidadeId) {
        cadastroCidadeService.excluir(cidadeId);
    }
}
