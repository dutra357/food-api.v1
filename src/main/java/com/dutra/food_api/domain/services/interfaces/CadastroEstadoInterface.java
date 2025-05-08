package com.dutra.food_api.domain.services.interfaces;

import com.dutra.food_api.domain.models.Estado;

import java.util.List;

public interface CadastroEstadoInterface {

    List<Estado> buscarTodos();

    Estado buscarPorId(Long id);

    Estado salvar(Estado estado);

    void delete(Long estadoId);

    Estado atualizar(Estado estado);
}
