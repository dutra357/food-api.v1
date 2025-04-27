package com.dutra.food_api.domain.services;

import com.dutra.food_api.domain.models.Restaurante;
import com.dutra.food_api.domain.repositories.repo.RestauranteRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CadastroRestauranteService {

    private final RestauranteRepository restauranteRepository;
    public CadastroRestauranteService(RestauranteRepository restauranteRepository) {
        this.restauranteRepository = restauranteRepository;
    }

    @Transactional
    public Restaurante salvar(Restaurante restaurante) {
        return restauranteRepository.save(restaurante);
    }

    @Transactional(readOnly = true)
    public Restaurante buscar(Long id) {
        return restauranteRepository.findById(id).get();
    }

    @Transactional(readOnly = true)
    public List<Restaurante> buscarTodos() {
        return restauranteRepository.findAll();
    }

    @Transactional
    public Restaurante atualizar(Restaurante restaurante) {
        return restauranteRepository.save(restaurante);
    }


}
