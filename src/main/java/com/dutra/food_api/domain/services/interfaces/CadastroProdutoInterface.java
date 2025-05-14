package com.dutra.food_api.domain.services.interfaces;

import com.dutra.food_api.api.model.input.ProdutoInput;
import com.dutra.food_api.api.model.output.ProdutoOutput;

import java.util.List;

public interface CadastroProdutoInterface {

    ProdutoOutput salvar(ProdutoInput produtoInput);

    ProdutoOutput atualizar(Long produtoId, ProdutoInput produtoInput);

    void excluir(Long produtoId);

    ProdutoOutput buscarPorId(Long produtoId);

    List<ProdutoOutput> buscarTodas();
}
