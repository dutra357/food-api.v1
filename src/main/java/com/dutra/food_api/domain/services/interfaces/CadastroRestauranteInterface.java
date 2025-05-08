package com.dutra.food_api.domain.services.interfaces;

import com.dutra.food_api.api.model.RestauranteInput;
import com.dutra.food_api.api.model.RestauranteOutput;
import com.dutra.food_api.domain.models.Restaurante;

import java.util.List;
import java.util.Map;

public interface CadastroRestauranteInterface {

    RestauranteOutput buscarPrimeiro();

    RestauranteOutput salvar(RestauranteInput restaurante);

    RestauranteOutput buscar(Long id);

    List<RestauranteOutput> buscarTodos();

    RestauranteOutput atualizarTudo(Long id, Restaurante restaurante);

    RestauranteOutput atualizarParcial(Long id, Map<String, Object> camposInformados);
}
