package com.dutra.food_api.domain.services.interfaces;

import com.dutra.food_api.api.model.input.EstadoInput;
import com.dutra.food_api.api.model.output.EstadoOutput;


import java.util.List;

public interface CadastroEstadoInterface {

    List<EstadoOutput> buscarTodos();

    EstadoOutput buscarPorId(Long id);

    EstadoOutput salvar(EstadoInput estado);

    void delete(Long estadoId);

    EstadoOutput atualizar(Long id, EstadoInput estado);
}
