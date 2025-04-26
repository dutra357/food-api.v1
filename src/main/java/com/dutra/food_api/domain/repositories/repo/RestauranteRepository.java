package com.dutra.food_api.domain.repositories.repo;

import com.dutra.food_api.domain.models.Restaurante;

import java.util.List;

public interface RestauranteRepository {

    List<Restaurante> buscarTodos();
    Restaurante buscar(Long id);

    Restaurante salvar(Restaurante cozinha);
    void remover(Long id);

}
