package com.dutra.food_api.domain.services;

import com.dutra.food_api.domain.exceptions.EntidadeEmUsoException;
import com.dutra.food_api.domain.models.Cozinha;
import com.dutra.food_api.domain.repositories.repo.CozinhaRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
public class CadastroCozinhaService {

    private final CozinhaRepository cozinhaRepository;
    public CadastroCozinhaService(CozinhaRepository cozinhaRepository) {
        this.cozinhaRepository = cozinhaRepository;
    }

    @Transactional
    public Cozinha salvarCozinha(Cozinha cozinha) {
        return cozinhaRepository.salvar(cozinha);
    }

    @Transactional
    public Cozinha atualizarCozinha(Cozinha cozinha) {
        Cozinha cozinhaAtual = cozinhaRepository.buscar(cozinha.getId());
        BeanUtils.copyProperties(cozinha, cozinhaAtual, "id");

        return cozinhaRepository.salvar(cozinha);
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    public void removerCozinha(Long id) {
        Cozinha cozinha = cozinhaRepository.buscar(id);

        if (cozinha != null) {
            throw new EmptyResultDataAccessException(1); //Número esperado de elementos
        }

        try {
            cozinhaRepository.remover(cozinha);
        } catch (DataIntegrityViolationException e) {
            throw new EntidadeEmUsoException("Entidade em uso, impossível excluir.");
        }
    }

    @Transactional(readOnly = false)
    public Cozinha buscarCozinha(Long id) {
        return cozinhaRepository.buscar(id);
    }

    @Transactional(readOnly = false)
    public List<Cozinha> buscarTodasCozinhas() {
        return cozinhaRepository.buscarTodas();
    }

}
