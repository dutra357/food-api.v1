package com.dutra.food_api.api.controller;

import com.dutra.food_api.api.model.input.FormaPagamentoInput;
import com.dutra.food_api.api.model.output.FormaPagamentoOutput;
import com.dutra.food_api.domain.services.FormaPagamentoService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/formas-pagamento")
public class FormaPagamentoController {

    private final FormaPagamentoService service;

    public FormaPagamentoController(FormaPagamentoService service) {
        this.service = service;
    }

    @GetMapping("/{formaPagamentoId}")
    public ResponseEntity<FormaPagamentoOutput> buscar(@PathVariable Long formaPagamentoId) {
        return ResponseEntity.ok(service.buscarPorId(formaPagamentoId));
    }

    @GetMapping
    public ResponseEntity<List<FormaPagamentoOutput>> buscarTodas() {
        return ResponseEntity.ok(service.buscarTodas());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<FormaPagamentoOutput> salvar(@RequestBody @Valid FormaPagamentoInput formaPagamentoInput) {
        return ResponseEntity.ok(service.salvar(formaPagamentoInput));
    }

    @PutMapping("/{formaPagamentoId}")
    public ResponseEntity<FormaPagamentoOutput> atualizar(@PathVariable Long formaPagamentoId,
                                                          @RequestBody @Valid FormaPagamentoInput formaPagamentoInput) {
        return ResponseEntity.ok(service.atualizar(formaPagamentoId, formaPagamentoInput));
    }

    @DeleteMapping("/{formaPagamentoId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void excluir(@PathVariable Long formaPagamentoId) {
        service.excluir(formaPagamentoId);
    }

}
