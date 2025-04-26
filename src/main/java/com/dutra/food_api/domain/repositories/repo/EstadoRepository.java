package com.dutra.food_api.domain.repositories.repo;

import com.dutra.food_api.domain.models.Estado;

import java.util.List;

public interface EstadoRepository {

    List<Estado> buscarTodos();
    Estado buscarEstado(Long id);

    Estado salvar(Estado estado);
    void remover(Estado estado);

}
