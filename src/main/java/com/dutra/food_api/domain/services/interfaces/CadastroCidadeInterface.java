package com.dutra.food_api.domain.services.interfaces;

import com.dutra.food_api.api.model.output.CidadeOutput;
import com.dutra.food_api.domain.models.Cidade;

import java.util.List;

public interface CadastroCidadeInterface {

    CidadeOutput salvar(Cidade cidade);

    CidadeOutput atualizar(Long cidadeId, Cidade cidade);

    void excluir(Long cidadeId);

    CidadeOutput buscarPorId(Long cidadeId);

    List<CidadeOutput> buscarTodas();
}
