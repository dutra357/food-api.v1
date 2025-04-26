package com.dutra.food_api.domain.services;

import com.dutra.food_api.domain.models.Estado;
import com.dutra.food_api.domain.repositories.repo.EstadoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CadastroEstadoService {

    private final EstadoRepository estadoRepository;
    public CadastroEstadoService(EstadoRepository estadoRepository) {
        this.estadoRepository = estadoRepository;
    }

    @Transactional(readOnly = false)
    public List<Estado> buscarTodos() {
        return estadoRepository.buscarTodos();
    }

}
