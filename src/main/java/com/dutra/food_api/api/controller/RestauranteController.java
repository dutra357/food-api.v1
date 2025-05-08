package com.dutra.food_api.api.controller;

import com.dutra.food_api.api.model.RestauranteInput;
import com.dutra.food_api.api.model.RestauranteOutput;
import com.dutra.food_api.domain.models.Restaurante;
import com.dutra.food_api.domain.repositories.impl.RestauranteImpl;
import com.dutra.food_api.domain.services.interfaces.CadastroRestauranteInterface;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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



    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<RestauranteOutput> salvar(@Valid @RequestBody RestauranteInput restaurante) {
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
                                                 @Valid @RequestBody Restaurante restaurante) {
        return ResponseEntity.ok(cadastroRestauranteService.atualizarTudo(id, restaurante));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<RestauranteOutput> atualizarParcial(@PathVariable Long id,
                                               @RequestBody Map<String, Object> campos) {
        return  ResponseEntity.ok(cadastroRestauranteService.atualizarParcial(id, campos));
    }
}
