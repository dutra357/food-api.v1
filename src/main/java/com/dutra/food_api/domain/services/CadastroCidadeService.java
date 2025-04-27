package com.dutra.food_api.domain.services;

import com.dutra.food_api.domain.services.exceptions.EntidadeNaoEncontradaException;
import com.dutra.food_api.domain.models.Cidade;
import com.dutra.food_api.domain.models.Estado;
import com.dutra.food_api.domain.repositories.CidadeRepository;
import com.dutra.food_api.domain.services.interfaces.CadastroCidadeInterface;
import org.springframework.beans.BeanUtils;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CadastroCidadeService implements CadastroCidadeInterface {

    private final CidadeRepository cidadeRepository;
    private final CadastroEstadoService cadastroEstadoService;

    public CadastroCidadeService(CidadeRepository cidadeRepository, CadastroEstadoService cadastroEstadoService) {
        this.cidadeRepository = cidadeRepository;
        this.cadastroEstadoService = cadastroEstadoService;
    }


    @Transactional
    @Override
    public Cidade salvar(Cidade cidade) {

        Long estadoId = cidade.getEstado().getId();
        Estado estado = cadastroEstadoService.buscarPorId(estadoId);

        if (estado == null) {
            throw new EntidadeNaoEncontradaException(
                    String.format("Não existe cadastro de estado com código %d", estadoId));
        }

        cidade.setEstado(estado);

        return cidadeRepository.save(cidade);
    }

    @Transactional
    @Override
    public Cidade atualizar(Long cidadeId, Cidade cidade) {

        Cidade cidadeAtual = cidadeRepository.findById(cidadeId)
                .orElseThrow( () -> new EmptyResultDataAccessException(1));

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
        return cidadeRepository.findById(cidadeId).orElseThrow(() -> new EmptyResultDataAccessException(1));
    }

    @Transactional(readOnly = true)
    @Override
    public List<Cidade> buscarTodas() {
        return cidadeRepository.findAll();
    }

}
