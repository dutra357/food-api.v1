package com.dutra.food_api.domain.services.interfaces;

import com.dutra.food_api.domain.models.Cidade;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface CadastroCidadeInterface {
    
    @Transactional
    Cidade salvar(Cidade cidade);

    @Transactional
    Cidade atualizar(Long cidadeId, Cidade cidade);

    @Transactional(propagation = Propagation.SUPPORTS)
    void excluir(Long cidadeId);

    @Transactional(readOnly = true)
    Cidade buscarPorId(Long cidadeId);

    @Transactional(readOnly = true)
    List<Cidade> buscarTodas();
}
