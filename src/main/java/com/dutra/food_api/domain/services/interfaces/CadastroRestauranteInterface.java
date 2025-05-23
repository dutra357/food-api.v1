package com.dutra.food_api.domain.services.interfaces;

import com.dutra.food_api.api.model.input.RestauranteInput;
import com.dutra.food_api.api.model.output.FormaPagamentoOutput;
import com.dutra.food_api.api.model.output.ProdutoOutput;
import com.dutra.food_api.api.model.output.RestauranteOutput;
import com.dutra.food_api.api.model.output.UsuarioOutput;
import com.dutra.food_api.domain.models.Restaurante;

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

    void inativarTodos(List<Long> ids);

    void ativarTodos(List<Long> ids);

    List<FormaPagamentoOutput> buscarFormasPagamento(Long restauranteId);

    void removerFormaPagamento(Long restauranteId, Long formaPagamentoId);

    void associarFormaPagamento(Long restauranteId, Long formaPagamentoId);

    List<ProdutoOutput> buscarProdutos(Long restauranteId);

    void associarProduto(Long restauranteId, Long produtoId);

    void desassociarProduto(Long restauranteId, Long produtoId);

    void abrirRestaurante(Long id);

    void fecharRestaurante(Long id);

    void desassociarUsuario(Long restauranteId, Long usuarioId);

    List<UsuarioOutput> buscarResponsaveis(Long restauranteId);

    void associarUsuario(Long restauranteId, Long usuarioId);
}
