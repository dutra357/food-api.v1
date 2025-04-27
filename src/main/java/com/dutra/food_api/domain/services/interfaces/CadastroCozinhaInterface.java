package com.dutra.food_api.domain.services.interfaces;

import com.dutra.food_api.domain.models.Cozinha;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface CadastroCozinhaInterface {
    @Transactional
    Cozinha salvarCozinha(Cozinha cozinha);

    @Transactional
    Cozinha atualizarCozinha(Long cozinhaId, Cozinha cozinha);

    @Transactional(propagation = Propagation.SUPPORTS)
    void remover(Long cozinhaId);

    @Transactional(readOnly = false)
    Cozinha buscar(Long id);

    @Transactional(readOnly = false)
    List<Cozinha> buscarTodas();
}
