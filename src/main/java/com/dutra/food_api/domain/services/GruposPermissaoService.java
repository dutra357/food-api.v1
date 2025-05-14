package com.dutra.food_api.domain.services;

import com.dutra.food_api.api.model.output.GrupoOutput;
import com.dutra.food_api.api.model.output.PermissaoOutput;
import com.dutra.food_api.domain.models.Grupo;
import com.dutra.food_api.domain.models.Permissao;
import com.dutra.food_api.domain.models.Produto;
import com.dutra.food_api.domain.repositories.GrupoRepository;
import com.dutra.food_api.domain.repositories.PermissaoRepository;
import com.dutra.food_api.domain.services.exceptions.EntidadeNaoEncontradaException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class GruposPermissaoService {

    private final GrupoRepository grupoRepository;
    private final PermissaoRepository permissaoRepository;

    public GruposPermissaoService(GrupoRepository grupoRepository, PermissaoRepository permissaoRepository) {
        this.grupoRepository = grupoRepository;
        this.permissaoRepository = permissaoRepository;
    }

    @Transactional
    public void associar(Long grupoId, Long permissaoId){
        Grupo grupo = buscarGrupo(grupoId);
        Permissao permissao = buscarPermissao(permissaoId);
        grupo.getPermissoes().add(permissao);
    }

    @Transactional
    public void desassociar(Long grupoId, Long permissaoId){
        Grupo grupo = buscarGrupo(grupoId);
        Permissao permissao = buscarPermissao(permissaoId);
        grupo.getPermissoes().remove(permissao);
    }

    @Transactional
    public List<PermissaoOutput> buscarPermissoes(Long grupoId){
        Grupo grupo = buscarGrupo(grupoId);
        return grupo.getPermissoes().stream().map(PermissaoOutput::toPermissaoOutput).toList();
    }

    @Transactional
    public GrupoOutput  buscarGrupoPorId(Long grupoId){
        return GrupoOutput.toGrupoOutput(buscarGrupo(grupoId));
    }

    @Transactional
    public List<GrupoOutput> buscarTodosGrupos(){
        return grupoRepository.findAll().stream().map(GrupoOutput::toGrupoOutput).toList();
    }




    protected Grupo buscarGrupo(Long grupoId){
        return grupoRepository.findById(grupoId).orElseThrow(
                () -> new EntidadeNaoEncontradaException("Grupo de permissões não encontrado.")
        );
    }

    protected Permissao buscarPermissao(Long permissaoId){
        return permissaoRepository.findById(permissaoId).orElseThrow(
                () -> new EntidadeNaoEncontradaException("Permissão não encontrada")
        );
    }
}
