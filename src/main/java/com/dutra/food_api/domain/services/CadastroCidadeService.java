package com.dutra.food_api.domain.services;

import com.dutra.food_api.domain.services.exceptions.EntidadeNaoEncontradaException;
import com.dutra.food_api.domain.models.Cidade;
import com.dutra.food_api.domain.models.Estado;
import com.dutra.food_api.domain.repositories.CidadeRepository;
import com.dutra.food_api.domain.services.exceptions.EstadoNaoEncontradoException;
import com.dutra.food_api.domain.services.interfaces.CadastroCidadeInterface;
import org.springframework.beans.BeanUtils;
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
    public Cidade salvar(Cidade cidade) {

        try {
            Long estadoId = cidade.getEstado().getId();
            Estado estado = cadastroEstadoService.buscarPorId(estadoId);
            cidade.setEstado(estado);

        } catch (EntidadeNaoEncontradaException err) {
            throw new EstadoNaoEncontradoException("Estado não encontrado");
        }

        return cidadeRepository.save(cidade);
    }

    @Transactional
    @Override
    public Cidade atualizar(Long cidadeId, Cidade cidade) {

        Cidade cidadeAtual = cidadeRepository.findById(cidadeId)
                .orElseThrow( () -> new EntidadeNaoEncontradaException(CIDADE_NOT_FOUND));

        BeanUtils.copyProperties(cidade, cidadeAtual, "id");

        return cidadeRepository.save(cidade);
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public void excluir(Long cidadeId) {
        cidadeRepository.deleteById(cidadeId);
    }

    @Transactional(readOnly = true)
    @Override
    public Cidade buscarPorId(Long cidadeId) {
        return cidadeRepository.findById(cidadeId)
                .orElseThrow(() -> new EntidadeNaoEncontradaException(CIDADE_NOT_FOUND));
    }

    @Transactional(readOnly = true)
    @Override
    public List<Cidade> buscarTodas() {
        return cidadeRepository.findAll();
    }

}
