package com.dutra.food_api.domain.services.interfaces;

import com.dutra.food_api.api.model.CozinhaOutput;
import com.dutra.food_api.domain.models.Cozinha;

import java.util.List;

public interface CadastroCozinhaInterface {

    CozinhaOutput salvarCozinha(Cozinha cozinha);

    CozinhaOutput atualizarCozinha(Long cozinhaId, Cozinha cozinha);

    void remover(Long cozinhaId);

    CozinhaOutput buscar(Long id);

    List<CozinhaOutput> buscarTodas();
}
