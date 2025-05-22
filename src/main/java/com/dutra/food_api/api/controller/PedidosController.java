package com.dutra.food_api.api.controller;

import com.dutra.food_api.api.model.input.PedidoInput;
import com.dutra.food_api.api.model.output.PedidoOutput;
import com.dutra.food_api.api.model.output.PedidoOutputShort;
import com.dutra.food_api.core.PropertiesTranslateFromDto;
import com.dutra.food_api.domain.repositories.filters.PedidoFilter;
import com.dutra.food_api.domain.repositories.specifications.PedidosFilterSpec;
import com.dutra.food_api.domain.services.interfaces.CadastroPedidosInterface;
import com.google.common.collect.ImmutableMap;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pedidos")
public class PedidosController {

    private final CadastroPedidosInterface cadastropedidosService;

    public PedidosController(CadastroPedidosInterface cadastropedidosService) {
        this.cadastropedidosService = cadastropedidosService;
    }

    @GetMapping("/{pedidoId}")
    public ResponseEntity<PedidoOutput> buscarPorId(@PathVariable Long pedidoId){
        return ResponseEntity.ok(cadastropedidosService.buscarPorId(pedidoId));
    }

    @GetMapping("/codigo/{codigo}")
    public ResponseEntity<PedidoOutput> buscarPorCodigo(@PathVariable String codigo){
        return ResponseEntity.ok(cadastropedidosService.buscarPedidoPorCodigo(codigo));
    }

    @GetMapping
    public ResponseEntity<List<PedidoOutputShort>> pesquisar(){
        return ResponseEntity.ok(cadastropedidosService.buscarTodos());
    }

    //Consulta customizada com Specification, JpaSpecificationExecutor
    @GetMapping("/com-spec")
    public ResponseEntity<Page<PedidoOutputShort>> pesquisar(@PageableDefault(size = 10, page = 0) Pageable pageable,
                                                             PedidoFilter pedidoFilter){
        return ResponseEntity.ok(cadastropedidosService.buscarTodosComSpec(translateFields(pageable), PedidosFilterSpec.usingFilter(pedidoFilter)));
    }

    @PostMapping
    public ResponseEntity<PedidoOutput> salvar(@RequestBody @Valid PedidoInput pedidoInput){
        return ResponseEntity.ok(cadastropedidosService.salvar(pedidoInput));
    }

    //Exclusivamente para ajuste dos parâmetros de paginação
    private Pageable translateFields(Pageable pageableInput) {
        var mapeamento = ImmutableMap.of(
                "nomeCliente",  "cliente.nome",
                "restauranteId", "restaurante.id",
                "restauranteNome", "restaurante.nome"
        );

        return PropertiesTranslateFromDto.translateFields(pageableInput, mapeamento);
    }
}
