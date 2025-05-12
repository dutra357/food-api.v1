package com.dutra.food_api.domain.services;

import com.dutra.food_api.api.model.input.EstadoInput;
import com.dutra.food_api.api.model.output.EstadoOutput;
import com.dutra.food_api.domain.models.Cozinha;
import com.dutra.food_api.domain.services.exceptions.EntidadeEmUsoException;
import com.dutra.food_api.domain.services.exceptions.EntidadeNaoEncontradaException;
import com.dutra.food_api.domain.models.Estado;
import com.dutra.food_api.domain.repositories.EstadoRepository;
import com.dutra.food_api.domain.services.interfaces.CadastroEstadoInterface;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CadastroEstadoService implements CadastroEstadoInterface {

    private static final String ESTADO_NOT_FOUND = "Estado não encontrado";

    private final EstadoRepository estadoRepository;
    public CadastroEstadoService(EstadoRepository estadoRepository) {
        this.estadoRepository = estadoRepository;
    }

    @Transactional(readOnly = true)
    @Override
    public List<EstadoOutput> buscarTodos() {
        return estadoRepository.findAll().stream().map(EstadoOutput::toEstadoOutput).toList();
    }

    @Transactional(readOnly = true)
    @Override
    public EstadoOutput buscarPorId(Long id) {
        return EstadoOutput.toEstadoOutput(estadoRepository.findById(id)
                .orElseThrow(() -> new EntidadeNaoEncontradaException(ESTADO_NOT_FOUND)));
    }

    @Transactional(readOnly = false)
    @Override
    public EstadoOutput salvar(EstadoInput estadoInput) {
        Estado estado = estadoInput.toEntity();
        return EstadoOutput.toEstadoOutput(estadoRepository.save(estado));
    }

    @Transactional(readOnly = false)
    @Override
    public void delete(Long estadoId) {
        Estado estado = buscaInternaEstado(estadoId);

        try {
            estadoRepository.delete(estado);
            estadoRepository.flush();

        } catch (DataIntegrityViolationException _) {
            throw new EntidadeEmUsoException(
                    String.format("Estado de código %d não pode ser removida, pois está em uso", estadoId));
        }
    }

    @Transactional(readOnly = false)
    @Override
    public EstadoOutput atualizar(Long id, EstadoInput estadoInput) {
        Estado estado = buscaInternaEstado(id);
        estado.setNome(estadoInput.getNome());

        return EstadoOutput.toEstadoOutput(estadoRepository.save(estado));
    }

    protected Estado buscaInternaEstado(Long id) {
        return estadoRepository.findById(id)
                .orElseThrow(() -> new EntidadeNaoEncontradaException(ESTADO_NOT_FOUND));
    }
}
