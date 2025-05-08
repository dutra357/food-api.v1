package com.dutra.food_api.domain.services.interfaces;

import com.dutra.food_api.domain.models.Cidade;

import java.util.List;

public interface CadastroCidadeInterface {
    
    Cidade salvar(Cidade cidade);

    Cidade atualizar(Long cidadeId, Cidade cidade);

    void excluir(Long cidadeId);

    Cidade buscarPorId(Long cidadeId);

    List<Cidade> buscarTodas();
}
