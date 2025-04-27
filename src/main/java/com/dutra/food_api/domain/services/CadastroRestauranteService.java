package com.dutra.food_api.domain.services;

import com.dutra.food_api.domain.models.Restaurante;
import com.dutra.food_api.domain.repositories.RestauranteRepository;
import com.dutra.food_api.domain.services.exceptions.PatchMergeFieldsException;
import com.dutra.food_api.domain.services.interfaces.CadastroRestauranteInterface;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;

@Service
public class CadastroRestauranteService implements CadastroRestauranteInterface {

    private final RestauranteRepository restauranteRepository;
    public CadastroRestauranteService(RestauranteRepository restauranteRepository) {
        this.restauranteRepository = restauranteRepository;
    }

    @Transactional
    @Override
    public Restaurante salvar(Restaurante restaurante) {
        return restauranteRepository.save(restaurante);
    }

    @Transactional(readOnly = true)
    @Override
    public Restaurante buscar(Long id) {
        return restauranteRepository.findById(id)
                .orElseThrow(() -> new EmptyResultDataAccessException(1));
    }

    @Transactional(readOnly = true)
    @Override
    public List<Restaurante> buscarTodos() {
        return restauranteRepository.findAll();
    }

    @Transactional
    @Override
    public Restaurante atualizarTudo(Restaurante restaurante) {
        return restauranteRepository.save(restaurante);
    }

    @Transactional
    @Override
    public Restaurante atualizarParcial(Long id, Map<String, Object> camposInformados) {

        Restaurante  restaurante = restauranteRepository.findById(id)
                .orElseThrow(() -> new EmptyResultDataAccessException(1));

        ObjectMapper objectMapper = new ObjectMapper();
        Restaurante restauranteOrigem = objectMapper.convertValue(camposInformados, Restaurante.class);

        camposInformados.forEach((campoInformado, valor) -> {
            try {
                Field campo = Restaurante.class.getDeclaredField(campoInformado);
                ReflectionUtils.makeAccessible(campo);

                Object novoValor = ReflectionUtils.getField(campo, restauranteOrigem);
                ReflectionUtils.setField(campo, restaurante, novoValor);

            } catch (NoSuchFieldException | IllegalArgumentException e) {
                throw new PatchMergeFieldsException("Erro ao atualizar o campo: " + campoInformado);
            }
        });

        return restauranteRepository.save(restaurante);
    }
}
