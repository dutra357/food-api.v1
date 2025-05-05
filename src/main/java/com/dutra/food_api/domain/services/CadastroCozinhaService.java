package com.dutra.food_api.domain.services;

import com.dutra.food_api.domain.services.exceptions.EntidadeEmUsoException;
import com.dutra.food_api.domain.models.Cozinha;
import com.dutra.food_api.domain.repositories.CozinhaRepository;
import com.dutra.food_api.domain.services.exceptions.EntidadeNaoEncontradaException;
import com.dutra.food_api.domain.services.interfaces.CadastroCozinhaInterface;
import org.springframework.beans.BeanUtils;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
public class CadastroCozinhaService implements CadastroCozinhaInterface {

    private static final String COZINHA_NOT_FOUND = "Cozinha não encontrada.";

    private final CozinhaRepository cozinhaRepository;
    public CadastroCozinhaService(CozinhaRepository cozinhaRepository) {
        this.cozinhaRepository = cozinhaRepository;
    }

    @Transactional
    @Override
    public Cozinha salvarCozinha(Cozinha cozinha) {
        return cozinhaRepository.save(cozinha);
    }

    @Transactional
    @Override
    public Cozinha atualizarCozinha(Long cozinhaId, Cozinha cozinha) {
        Cozinha cozinhaAtual = cozinhaRepository.findById(cozinhaId)
                .orElseThrow( () -> new EntidadeNaoEncontradaException(COZINHA_NOT_FOUND));

        BeanUtils.copyProperties(cozinha, cozinhaAtual, "id");
        return cozinhaRepository.save(cozinhaAtual);
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public void remover(Long cozinhaId) {

        try {
            if (!cozinhaRepository.existsById(cozinhaId)) {
                throw new EntidadeNaoEncontradaException(COZINHA_NOT_FOUND);
            }
            cozinhaRepository.deleteById(cozinhaId);

        } catch (DataIntegrityViolationException e) {
            throw new EntidadeEmUsoException(
                    String.format("Cozinha de código %d não pode ser removida, pois está em uso", cozinhaId));
        }
    }

    @Transactional(readOnly = false)
    @Override
    public Cozinha buscar(Long id) {
        return cozinhaRepository.findById(id)
                .orElseThrow( () -> new EntidadeNaoEncontradaException(COZINHA_NOT_FOUND));
    }

    @Transactional(readOnly = false)
    @Override
    public List<Cozinha> buscarTodas() {
        return cozinhaRepository.findAll();
    }

}
