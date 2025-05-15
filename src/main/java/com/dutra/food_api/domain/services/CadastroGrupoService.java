package com.dutra.food_api.domain.services;

import com.dutra.food_api.api.model.input.GrupoInput;
import com.dutra.food_api.api.model.output.GrupoOutput;
import com.dutra.food_api.domain.models.Grupo;
import com.dutra.food_api.domain.repositories.GrupoRepository;
import com.dutra.food_api.domain.services.exceptions.EntidadeEmUsoException;
import com.dutra.food_api.domain.services.exceptions.EntidadeNaoEncontradaException;
import com.dutra.food_api.domain.services.interfaces.CadastroGrupoInterface;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CadastroGrupoService implements CadastroGrupoInterface {

    private static final String CIDADE_NOT_FOUND = "Grupo não encontrado";

    private final GrupoRepository grupoRepository;
    public CadastroGrupoService(GrupoRepository grupoRepository) {
        this.grupoRepository = grupoRepository;
    }

    @Transactional
    @Override
    public GrupoOutput salvar(GrupoInput grupoInput) {
        Grupo grupo = grupoInput.toEntity();
        return GrupoOutput.toGrupoOutput(grupoRepository.save(grupo));
    }

    @Transactional
    @Override
    public GrupoOutput atualizar(Long grupoId, GrupoInput grupoInput) {
        Grupo grupoAtual = grupoRepository.findById(grupoId)
                .orElseThrow( () -> new EntidadeNaoEncontradaException(CIDADE_NOT_FOUND));

        grupoAtual.setNome(grupoInput.getNome());
        return GrupoOutput.toGrupoOutput(grupoRepository.save(grupoAtual));
    }

    @Transactional
    @Override
    public void excluir(Long grupoId) {
        Grupo grupo = buscaInternaGrupo(grupoId);

        try {
            grupoRepository.delete(grupo);
            grupoRepository.flush();

        } catch (DataIntegrityViolationException _) {
            throw new EntidadeEmUsoException(
                    String.format("Grupo de código %d não pode ser removido, pois está em uso", grupoId));
        }
    }

    @Transactional(readOnly = true)
    @Override
    public GrupoOutput buscarPorId(Long grupoId) {
        return GrupoOutput.toGrupoOutput(buscaInternaGrupo(grupoId));
    }

    @Transactional(readOnly = true)
    @Override
    public List<GrupoOutput> buscarTodos() {
        return grupoRepository.findAll()
                .stream().map(GrupoOutput::toGrupoOutput).toList();
    }

    protected Grupo buscaInternaGrupo(Long grupoId) {
        return grupoRepository.findById(grupoId)
                .orElseThrow(() -> new EntidadeNaoEncontradaException(CIDADE_NOT_FOUND));
    }
}
