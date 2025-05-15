package com.dutra.food_api.api.controller;

import com.dutra.food_api.api.model.output.GrupoOutput;
import com.dutra.food_api.domain.services.interfaces.CadastroUsuarioInterface;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuarios/{usuarioId}/grupos")
public class UsuarioGruposController {

    private final CadastroUsuarioInterface usuarioService;

    public UsuarioGruposController(CadastroUsuarioInterface usuarioService) {
        this.usuarioService = usuarioService;
    }

    @GetMapping
    public ResponseEntity<List<GrupoOutput>> buscarGruposUsuario(@PathVariable Long usuarioId) {
        return ResponseEntity.ok(usuarioService.buscarGruposUsuario(usuarioId));
    }

    @PutMapping("/{grupoId}")
    public ResponseEntity<Void> associarGrupo(@PathVariable Long usuarioId, @PathVariable Long grupoId) {
        usuarioService.associarGrupo(usuarioId, grupoId);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{grupoId}")
    public ResponseEntity<Void> desassociarGrupo(@PathVariable Long usuarioId, @PathVariable Long grupoId) {
        usuarioService.desassociarGrupo(usuarioId, grupoId);
        return ResponseEntity.noContent().build();
    }
}
