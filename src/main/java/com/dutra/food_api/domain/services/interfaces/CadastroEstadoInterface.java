package com.dutra.food_api.domain.services.interfaces;

import com.dutra.food_api.api.model.output.EstadoOutput;
import com.dutra.food_api.domain.models.Estado;

import java.util.List;

public interface CadastroEstadoInterface {

    List<EstadoOutput> buscarTodos();

    EstadoOutput buscarPorId(Long id);

    EstadoOutput salvar(Estado estado);

    void delete(Long estadoId);

    EstadoOutput atualizar(Estado estado);
}
