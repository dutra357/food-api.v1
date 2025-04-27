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
        return cozinhaRepository.save(cozinha);
    }

    @Transactional
    public Cozinha atualizarCozinha(Long cozinhaId, Cozinha cozinha) {
        Cozinha cozinhaAtual = cozinhaRepository.findById(cozinhaId)
                .orElseThrow( () -> new EmptyResultDataAccessException(1));

        BeanUtils.copyProperties(cozinha, cozinhaAtual, "id");

        return cozinhaRepository.save(cozinha);
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    public void remover(Long id) {
        Cozinha cozinha = cozinhaRepository.findById(id)
                .orElseThrow( () -> new EmptyResultDataAccessException(1));

        if (cozinha != null) {
            throw new EmptyResultDataAccessException(1); //Número esperado de elementos
        }

        try {
            cozinhaRepository.delete(cozinha);

        } catch (DataIntegrityViolationException e) {
            throw new EntidadeEmUsoException("Entidade em uso, impossível excluir.");
        }
    }

    @Transactional(readOnly = false)
    public Cozinha buscar(Long id) {
        return cozinhaRepository.findById(id)
                .orElseThrow( () -> new EmptyResultDataAccessException(1));
    }

    @Transactional(readOnly = false)
    public List<Cozinha> buscarTodas() {
        return cozinhaRepository.findAll();
    }

}
