package com.dutra.food_api.domain.services.interfaces;

import com.dutra.food_api.domain.models.Estado;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface CadastroEstadoInterface {
    @Transactional(readOnly = true)
    List<Estado> buscarTodos();

    @Transactional(readOnly = true)
    Estado buscarPorId(Long id);

    @Transactional(readOnly = false)
    Estado salvar(Estado estado);

    @Transactional(readOnly = false)
    void delete(Long estadoId);

    @Transactional(readOnly = false)
    Estado atualizar(Estado estado);
}
