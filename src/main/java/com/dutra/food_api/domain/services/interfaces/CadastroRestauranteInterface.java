package com.dutra.food_api.domain.services.interfaces;

import com.dutra.food_api.api.model.input.RestauranteInput;
import com.dutra.food_api.api.model.output.FormaPagamentoOutput;
import com.dutra.food_api.api.model.output.RestauranteOutput;

import java.util.List;
import java.util.Map;

public interface CadastroRestauranteInterface {

    RestauranteOutput buscarPrimeiro();

    RestauranteOutput salvar(RestauranteInput restaurante);

    RestauranteOutput buscar(Long id);

    List<RestauranteOutput> buscarTodos();

    RestauranteOutput atualizarTudo(Long id, RestauranteInput restaurante);

    RestauranteOutput atualizarParcial(Long id, Map<String, Object> camposInformados);

    void remover(Long id);

    void ativar(Long id);

    void inativar(Long id);

    List<FormaPagamentoOutput> buscarFormasPagamento(Long restauranteId);

    void removerFormaPagamento(Long restauranteId, Long formaPagamentoId);

    void associarFormaPagamento(Long restauranteId, Long formaPagamentoId);
}
