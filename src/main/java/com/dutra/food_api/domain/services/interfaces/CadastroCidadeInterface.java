package com.dutra.food_api.domain.services.interfaces;

import com.dutra.food_api.api.model.input.CidadeInput;
import com.dutra.food_api.api.model.output.CidadeOutput;

import java.util.List;

public interface CadastroCidadeInterface {

    CidadeOutput salvar(CidadeInput cidadeInput);

    CidadeOutput atualizar(Long cidadeId, CidadeInput cidadeInput);

    void excluir(Long cidadeId);

    CidadeOutput buscarPorId(Long cidadeId);

    List<CidadeOutput> buscarTodas();
}
