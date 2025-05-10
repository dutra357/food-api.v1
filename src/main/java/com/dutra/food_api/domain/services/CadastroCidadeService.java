package com.dutra.food_api.domain.services;

import com.dutra.food_api.api.model.input.CidadeInput;
import com.dutra.food_api.api.model.output.CidadeOutput;
import com.dutra.food_api.domain.services.exceptions.EntidadeEmUsoException;
import com.dutra.food_api.domain.services.exceptions.EntidadeNaoEncontradaException;
import com.dutra.food_api.domain.models.Cidade;
import com.dutra.food_api.domain.models.Estado;
import com.dutra.food_api.domain.repositories.CidadeRepository;
import com.dutra.food_api.domain.services.exceptions.EstadoNaoEncontradoException;
import com.dutra.food_api.domain.services.interfaces.CadastroCidadeInterface;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CadastroCidadeService implements CadastroCidadeInterface {

    private static final String CIDADE_NOT_FOUND = "Cidade não encontrada";

    private final CidadeRepository cidadeRepository;
    private final CadastroEstadoService cadastroEstadoService;

    public CadastroCidadeService(CidadeRepository cidadeRepository, CadastroEstadoService cadastroEstadoService) {
        this.cidadeRepository = cidadeRepository;
        this.cadastroEstadoService = cadastroEstadoService;
    }


    @Transactional
    @Override
    public CidadeOutput salvar(CidadeInput cidadeInput) {

        Cidade cidade = cidadeInput.toEntity();
        Estado estado = cadastroEstadoService.buscaInternaEstado(cidadeInput.getEstadoId());
        try {
            cidade.setEstado(estado);

        } catch (EntidadeNaoEncontradaException _) {
            throw new EstadoNaoEncontradoException("Estado não encontrado");
        }

        return CidadeOutput.toCidadeOutput(cidadeRepository.save(cidade));
    }

    @Transactional
    @Override
    public CidadeOutput atualizar(Long cidadeId, CidadeInput cidadeInput) {

        Cidade cidadeAtual = cidadeRepository.findById(cidadeId)
                .orElseThrow( () -> new EntidadeNaoEncontradaException(CIDADE_NOT_FOUND));

        cidadeAtual.setNome(cidadeInput.getNome());
        cidadeAtual.setEstado(cadastroEstadoService.buscaInternaEstado(cidadeInput.getEstadoId()));

        return CidadeOutput.toCidadeOutput(cidadeRepository.save(cidadeAtual));
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public void excluir(Long cidadeId) {
        Cidade cidade = buscaInternaCidade(cidadeId);

        try {
            cidadeRepository.delete(cidade);
            cidadeRepository.flush();

        } catch (DataIntegrityViolationException _) {
            throw new EntidadeEmUsoException(
                    String.format("Cidade de código %d não pode ser removida, pois está em uso", cidadeId));
        }
    }

    @Transactional(readOnly = true)
    @Override
    public CidadeOutput buscarPorId(Long cidadeId) {
        return CidadeOutput.toCidadeOutput(cidadeRepository.findById(cidadeId)
                .orElseThrow(() -> new EntidadeNaoEncontradaException(CIDADE_NOT_FOUND)));
    }

    @Transactional(readOnly = true)
    @Override
    public List<CidadeOutput> buscarTodas() {
        return cidadeRepository.findAll()
                .stream().map(CidadeOutput::toCidadeOutput).toList();
    }

    protected Cidade buscaInternaCidade(Long id) {
        return cidadeRepository.findById(id)
                .orElseThrow(() -> new EntidadeNaoEncontradaException(CIDADE_NOT_FOUND));
    }

}
