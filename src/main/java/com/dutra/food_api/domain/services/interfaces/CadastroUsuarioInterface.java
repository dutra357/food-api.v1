package com.dutra.food_api.domain.services.interfaces;

import com.dutra.food_api.api.model.input.SenhaInput;
import com.dutra.food_api.api.model.input.UsuarioInput;
import com.dutra.food_api.api.model.input.UsuarioUpdateInput;
import com.dutra.food_api.api.model.output.UsuarioOutput;

import java.util.List;

public interface CadastroUsuarioInterface {

    UsuarioOutput salvar(UsuarioInput usuarioInput);

    UsuarioOutput atualizar(Long id, UsuarioUpdateInput usuarioUpdateInput);

    UsuarioOutput buscarPorId(Long usuarioId);

    List<UsuarioOutput> buscarTodos();

    void remover(Long usuarioId);

    void alterarSenha(Long usuarioId, SenhaInput senhaInput);
}
