package com.dutra.food_api.domain.services.interfaces;

import com.dutra.food_api.api.model.input.CozinhaInput;
import com.dutra.food_api.api.model.output.CozinhaOutput;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CadastroCozinhaInterface {

    CozinhaOutput salvarCozinha(CozinhaInput cozinhaInput);

    CozinhaOutput atualizarCozinha(Long cozinhaId, CozinhaInput cozinhaInput);

    void remover(Long cozinhaId);

    CozinhaOutput buscar(Long id);

    Page<CozinhaOutput> buscarTodas(Pageable pageable);
}
