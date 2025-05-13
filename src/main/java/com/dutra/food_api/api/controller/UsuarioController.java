package com.dutra.food_api.api.controller;

import com.dutra.food_api.api.model.input.SenhaInput;
import com.dutra.food_api.api.model.input.UsuarioInput;
import com.dutra.food_api.api.model.input.UsuarioUpdateInput;
import com.dutra.food_api.api.model.output.UsuarioOutput;
import com.dutra.food_api.domain.services.interfaces.CadastroUsuarioInterface;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    private final CadastroUsuarioInterface usuarioService;

    public UsuarioController(CadastroUsuarioInterface usuarioService) {
        this.usuarioService = usuarioService;
    }


    @GetMapping("/{usuarioId}")
    public ResponseEntity<UsuarioOutput> buscar(@PathVariable Long usuarioId) {
        return ResponseEntity.ok(usuarioService.buscarPorId(usuarioId));
    }

    @GetMapping
    public ResponseEntity<List<UsuarioOutput>> buscarTodos() {
        return ResponseEntity.ok(usuarioService.buscarTodos());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<UsuarioOutput> salvar(@RequestBody @Valid UsuarioInput usuarioInput) {
        return ResponseEntity.ok(usuarioService.salvar(usuarioInput));
    }

    @PutMapping("/{usuarioId}")
    public ResponseEntity<UsuarioOutput> atualizar(@PathVariable Long usuarioId,
                                                   @RequestBody @Valid UsuarioUpdateInput usuarioUpdateInput) {
        return ResponseEntity.ok(usuarioService.atualizar(usuarioId, usuarioUpdateInput));
    }

    @DeleteMapping("/{usuarioId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void excluir(@PathVariable Long usuarioId) {
        usuarioService.remover(usuarioId);
    }

    @PutMapping("/{usuarioId}/senha")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void alterarSenha(@PathVariable Long usuarioId, @RequestBody @Valid SenhaInput senhaInput) {
        usuarioService.alterarSenha(usuarioId, senhaInput);
    }
}
