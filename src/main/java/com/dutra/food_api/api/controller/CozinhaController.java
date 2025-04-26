package com.dutra.food_api.api.controller;

import com.dutra.food_api.api.model.CozinhasXmlWrapper;
import com.dutra.food_api.domain.models.Cozinha;
import com.dutra.food_api.domain.services.CadastroCozinhaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
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
        cadastroCozinhaService.removerCozinha(cozinhaId);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{cozinhaId}")
    public ResponseEntity<Cozinha> atualizar(@PathVariable Long cozinhaId,
                                             @RequestBody Cozinha cozinha) {
        return ResponseEntity.ok(cadastroCozinhaService.buscarCozinha(cozinhaId));
    }

    @GetMapping
    public ResponseEntity<List<Cozinha>> buscarTodas() {
        return ResponseEntity.ok(cadastroCozinhaService.buscarTodasCozinhas());
    }

    @GetMapping("/{cozinhaId}")
    public ResponseEntity<Cozinha> buscar(@PathVariable Long cozinhaId) {
        return ResponseEntity.ok(cadastroCozinhaService.buscarCozinha(cozinhaId));
    }


    //Endpoint legado para XMLWrapper
    @GetMapping(produces = MediaType.APPLICATION_XML_VALUE)
    public CozinhasXmlWrapper listarXml() {
        return new CozinhasXmlWrapper(cadastroCozinhaService.buscarTodasCozinhas());
    }
}