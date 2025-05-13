package com.dutra.food_api.api.controller;

import com.dutra.food_api.api.model.input.GrupoInput;
import com.dutra.food_api.api.model.output.GrupoOutput;
import com.dutra.food_api.domain.services.interfaces.CadastroGrupoInterface;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/grupos")
public class GrupoController {

    private final CadastroGrupoInterface grupoService;
    public GrupoController(CadastroGrupoInterface grupoService) {
        this.grupoService = grupoService;
    }


    @GetMapping("/{grupoId}")
    public ResponseEntity<GrupoOutput> buscar(@PathVariable Long grupoId) {
        return ResponseEntity.ok(grupoService.buscarPorId(grupoId));
    }

    @GetMapping
    public ResponseEntity<List<GrupoOutput>> buscarTodos() {
        return ResponseEntity.ok(grupoService.buscarTodos());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<GrupoOutput> salvar(@RequestBody @Valid GrupoInput grupoInput) {
        return ResponseEntity.ok(grupoService.salvar(grupoInput));
    }

    @PutMapping("/{grupoId}")
    public ResponseEntity<GrupoOutput> atualizar(@PathVariable Long grupoId,@RequestBody @Valid GrupoInput grupoInput) {
        return ResponseEntity.ok(grupoService.atualizar(grupoId, grupoInput));
    }

    @DeleteMapping("/{grupoId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void excluir(@PathVariable Long grupoId) {
        grupoService.excluir(grupoId);
    }
}
