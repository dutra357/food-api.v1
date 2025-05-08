package com.dutra.food_api.domain.services.interfaces;

import com.dutra.food_api.domain.models.Cozinha;

import java.util.List;

public interface CadastroCozinhaInterface {

    Cozinha salvarCozinha(Cozinha cozinha);

    Cozinha atualizarCozinha(Long cozinhaId, Cozinha cozinha);

    void remover(Long cozinhaId);

    Cozinha buscar(Long id);

    List<Cozinha> buscarTodas();
}
