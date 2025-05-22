package com.dutra.food_api.domain.services;

import com.dutra.food_api.api.model.input.CozinhaInput;
import com.dutra.food_api.api.model.output.CozinhaOutput;
import com.dutra.food_api.domain.services.exceptions.EntidadeEmUsoException;
import com.dutra.food_api.domain.models.Cozinha;
import com.dutra.food_api.domain.repositories.CozinhaRepository;
import com.dutra.food_api.domain.services.exceptions.EntidadeNaoEncontradaException;
import com.dutra.food_api.domain.services.interfaces.CadastroCozinhaInterface;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
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
    public CozinhaOutput salvarCozinha(CozinhaInput cozinhaInput) {
        Cozinha cozinha = cozinhaInput.toEntity();
        return CozinhaOutput.toCozinhaOutput(cozinhaRepository.save(cozinha));
    }

    @Transactional
    @Override
    public CozinhaOutput atualizarCozinha(Long cozinhaId, CozinhaInput cozinhaInput) {
        Cozinha cozinhaAtual = buscarCozinha(cozinhaId);

        cozinhaAtual.setNome(cozinhaInput.getNome());
        return CozinhaOutput.toCozinhaOutput(cozinhaRepository.save(cozinhaAtual));
    }

    @Transactional
    @Override
    public void remover(Long cozinhaId) {
        Cozinha cozinha = buscarCozinha(cozinhaId);

        try {
            cozinhaRepository.delete(cozinha);
            cozinhaRepository.flush();

        } catch (DataIntegrityViolationException _) {
            throw new EntidadeEmUsoException(
                    String.format("Cozinha de código %d não pode ser removida, pois está em uso", cozinhaId));
        }
    }

    @Transactional(readOnly = false)
    @Override
    public CozinhaOutput buscar(Long id) {
        return CozinhaOutput.toCozinhaOutput(cozinhaRepository.findById(id)
                .orElseThrow( () -> new EntidadeNaoEncontradaException(COZINHA_NOT_FOUND)));
    }

    @Transactional(readOnly = false)
    @Override
    public Page<CozinhaOutput> buscarTodas(Pageable pageable) {
        return cozinhaRepository.findAll(pageable).map(CozinhaOutput::toCozinhaOutput);
    }

    protected Cozinha buscarCozinha(Long id) {
        return cozinhaRepository.findById(id)
                .orElseThrow( () -> new EntidadeNaoEncontradaException(COZINHA_NOT_FOUND));
    }

}
