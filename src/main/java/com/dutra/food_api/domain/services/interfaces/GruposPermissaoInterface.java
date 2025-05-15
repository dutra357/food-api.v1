package com.dutra.food_api.domain.services.interfaces;

import com.dutra.food_api.api.model.output.GrupoOutput;
import com.dutra.food_api.api.model.output.PermissaoOutput;

import java.util.List;

public interface GruposPermissaoInterface {

    void associar(Long grupoId, Long permissaoId);

    void desassociar(Long grupoId, Long permissaoId);

    void desassociarTodasPermissoes(Long grupoId);

    void associarTodasPermissoes(Long grupoId);

    GrupoOutput buscarGrupoPorId(Long grupoId);

    List<GrupoOutput> buscarTodosGrupos();

    List<PermissaoOutput> buscarTodasPermissoesGrupo(Long grupoId);


}
