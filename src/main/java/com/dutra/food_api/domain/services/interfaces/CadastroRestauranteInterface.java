package com.dutra.food_api.domain.services.interfaces;

import com.dutra.food_api.domain.models.Restaurante;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

public interface CadastroRestauranteInterface {
    @Transactional
    Restaurante salvar(Restaurante restaurante);

    @Transactional(readOnly = true)
    Restaurante buscar(Long id);

    @Transactional(readOnly = true)
    List<Restaurante> buscarTodos();

    @Transactional
    Restaurante atualizarTudo(Restaurante restaurante);

    @Transactional
    Restaurante atualizarParcial(Long id, Map<String, Object> camposInformados);
}
