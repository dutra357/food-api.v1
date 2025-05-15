package com.dutra.food_api.api.controller;

import com.dutra.food_api.api.model.output.PermissaoOutput;
import com.dutra.food_api.domain.services.interfaces.GruposPermissaoInterface;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/grupos/{grupoId}/permissoes")
public class GrupoPermissoesController {

    private final GruposPermissaoInterface gruposPermissaoService;

    public GrupoPermissoesController(GruposPermissaoInterface gruposPermissaoService) {
        this.gruposPermissaoService = gruposPermissaoService;
    }

    @GetMapping
    public ResponseEntity<List<PermissaoOutput>> buscarTodasPermissoesGrupo(@PathVariable Long grupoId){
        return ResponseEntity.ok(gruposPermissaoService.buscarTodasPermissoesGrupo(grupoId));
    }

    @PutMapping("/associar/{permissaoId}")
    public ResponseEntity<Void> associar(@PathVariable Long grupoId, @PathVariable Long permissaoId) {
        gruposPermissaoService.associar(grupoId, permissaoId);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/desassociar/{permissaoId}")
    public ResponseEntity<Void> desassociar(@PathVariable Long grupoId, @PathVariable Long permissaoId) {
        gruposPermissaoService.desassociar(grupoId, permissaoId);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/desassociar-todas")
    public ResponseEntity<Void> desassociarTodasPermissoes(@PathVariable Long grupoId){
        gruposPermissaoService.desassociarTodasPermissoes(grupoId);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/associar-todas")
    public ResponseEntity<Void> associarTodasPermissoes(@PathVariable Long grupoId){
        gruposPermissaoService.associarTodasPermissoes(grupoId);
        return ResponseEntity.ok().build();
    }

}
