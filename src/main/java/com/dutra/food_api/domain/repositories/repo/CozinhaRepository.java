package com.dutra.food_api.domain.repositories.repo;

import com.dutra.food_api.domain.models.Cozinha;

import java.util.List;

public interface CozinhaRepository {

    Cozinha buscar(Long id);
    List<Cozinha> buscarTodas();

    Cozinha salvar(Cozinha cozinha);
    void remover(Cozinha cozinha);

}