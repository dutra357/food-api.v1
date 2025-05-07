package com.dutra.food_api.domain.services.interfaces;

import com.dutra.food_api.api.model.RestauranteInput;
import com.dutra.food_api.domain.models.Restaurante;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

public interface CadastroRestauranteInterface {

    public Restaurante buscarPrimeiro();

    @Transactional
    Restaurante salvar(RestauranteInput restaurante);

    @Transactional(readOnly = true)
    Restaurante buscar(Long id);

    @Transactional(readOnly = true)
    List<Restaurante> buscarTodos();

    @Transactional
    Restaurante atualizarTudo(Long id, Restaurante restaurante);

    @Transactional
    Restaurante atualizarParcial(Long id, Map<String, Object> camposInformados);
}
