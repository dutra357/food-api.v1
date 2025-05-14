package com.dutra.food_api.domain.services.interfaces;

import com.dutra.food_api.api.model.input.FormaPagamentoInput;
import com.dutra.food_api.api.model.output.FormaPagamentoOutput;
import com.dutra.food_api.domain.models.FormaPagamento;

import java.util.List;

public interface CadastroFormaPagamentoInterface {

    FormaPagamentoOutput salvar(FormaPagamentoInput formaPagamentoInput);

    FormaPagamentoOutput atualizar(Long formaPagamentoId, FormaPagamentoInput formaPagamentoInput);

    void excluir(Long cidadeId);

    FormaPagamentoOutput buscarPorId(Long cidadeId);

    List<FormaPagamentoOutput> buscarTodas();
}
