package com.dutra.food_api.api.controller;

import com.dutra.food_api.api.model.input.RestauranteInput;
import com.dutra.food_api.api.model.output.RestauranteOutput;
import com.dutra.food_api.domain.repositories.impl.RestauranteImpl;
import com.dutra.food_api.domain.services.interfaces.CadastroRestauranteInterface;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import jakarta.validation.Valid;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJacksonValue;

import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/restaurantes")
public class RestauranteController {

    private final CadastroRestauranteInterface cadastroRestauranteService;
    private final RestauranteImpl restauranteImpl;

    public RestauranteController(CadastroRestauranteInterface cadastroRestauranteService,
                                 RestauranteImpl restauranteImpl) {
        this.cadastroRestauranteService = cadastroRestauranteService;
        this.restauranteImpl = restauranteImpl;
    }

    @GetMapping("/com-frete-gratis")
    public ResponseEntity<List<RestauranteOutput>> buscar(@RequestParam String comNome) {
        return ResponseEntity.ok(restauranteImpl.findComFreteGratis(comNome));
    }

    @GetMapping("/buscar-primeiro")
    public ResponseEntity<RestauranteOutput> buscarPrimeiro() {
        return ResponseEntity.ok(cadastroRestauranteService.buscarPrimeiro());
    }

    //TESTE - solução não ideal para retorno
    //Uso de JAVA.LANG3 - adicionada ao POM
    @GetMapping("/buscar-resumido")
    public ResponseEntity<MappingJacksonValue> buscarResumido(@RequestParam(required = false) String campos) {

        List<RestauranteOutput> restaurantes = cadastroRestauranteService.buscarTodos();

        MappingJacksonValue  mappingJacksonValue = new MappingJacksonValue(restaurantes);
        SimpleFilterProvider filterProvider = new SimpleFilterProvider();

        filterProvider.addFilter("RestauranteOutputFilter", SimpleBeanPropertyFilter.serializeAll());
        if (StringUtils.isNotBlank(campos)) {
            filterProvider.addFilter("RestauranteOutputFilter",
                    SimpleBeanPropertyFilter.filterOutAllExcept(campos.split(",")));
        }

        mappingJacksonValue.setFilters(filterProvider);

        return ResponseEntity.ok(mappingJacksonValue);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<RestauranteOutput> salvar(@RequestBody @Valid RestauranteInput restaurante) {
        return ResponseEntity.ok(cadastroRestauranteService.salvar(restaurante));
    }

    @GetMapping("/{id}")
    public ResponseEntity<RestauranteOutput> buscar(@PathVariable Long id) {
        return ResponseEntity.ok(cadastroRestauranteService.buscar(id));
    }

    @GetMapping()
    public ResponseEntity<List<RestauranteOutput>> buscarTodos() {
        return ResponseEntity.ok(cadastroRestauranteService.buscarTodos());
    }

    @PutMapping("/{id}")
    public ResponseEntity<RestauranteOutput> atualizar(@PathVariable Long id,
                                                 @RequestBody @Valid RestauranteInput restauranteInput) {
        return ResponseEntity.ok(cadastroRestauranteService.atualizarTudo(id, restauranteInput));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<RestauranteOutput> atualizarParcial(@PathVariable Long id,
                                               @RequestBody Map<String, Object> campos) {
        return  ResponseEntity.ok(cadastroRestauranteService.atualizarParcial(id, campos));
    }

    @PutMapping("/{id}/ativar")
    public ResponseEntity<Void> ativar(@PathVariable Long id) {
        cadastroRestauranteService.ativar(id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}/inativar")
    public ResponseEntity<Void> inativar(@PathVariable Long id) {
        cadastroRestauranteService.inativar(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/ativar-todos")
    public ResponseEntity<Void> ativarTodos(@RequestBody List<Long> ids) {
        cadastroRestauranteService.ativarTodos(ids);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/inativar-todos")
    public ResponseEntity<Void> inativarTodos(@RequestBody List<Long> ids) {
        cadastroRestauranteService.inativarTodos(ids);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}/fechamento")
    public ResponseEntity<Void> fecharRestaurante(@PathVariable Long id) {
        cadastroRestauranteService.fecharRestaurante(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}/abertura")
    public ResponseEntity<Void> abrirRestaurante(@PathVariable Long id) {
        cadastroRestauranteService.abrirRestaurante(id);
        return ResponseEntity.noContent().build();
    }
}
