package com.dutra.food_api.domain.services.interfaces;

import com.dutra.food_api.api.model.input.GrupoInput;
import com.dutra.food_api.api.model.output.GrupoOutput;

import java.util.List;

public interface CadastroGrupoInterface {

    GrupoOutput salvar(GrupoInput grupoInput);

    GrupoOutput atualizar(Long cidadeId, GrupoInput grupoInput);

    void excluir(Long grupoId);

    GrupoOutput buscarPorId(Long grupoId);

    List<GrupoOutput> buscarTodos();
}
