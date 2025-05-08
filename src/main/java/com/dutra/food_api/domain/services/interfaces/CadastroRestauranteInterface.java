package com.dutra.food_api.domain.services.interfaces;

import com.dutra.food_api.api.model.RestauranteInput;
import com.dutra.food_api.domain.models.Restaurante;

import java.util.List;
import java.util.Map;

public interface CadastroRestauranteInterface {

    public Restaurante buscarPrimeiro();

    Restaurante salvar(RestauranteInput restaurante);

    Restaurante buscar(Long id);

    List<Restaurante> buscarTodos();

    Restaurante atualizarTudo(Long id, Restaurante restaurante);

    Restaurante atualizarParcial(Long id, Map<String, Object> camposInformados);
}
