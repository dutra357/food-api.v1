package com.dutra.food_api.domain.services;

import com.dutra.food_api.api.model.RestauranteInput;
import com.dutra.food_api.domain.models.Restaurante;
import com.dutra.food_api.domain.repositories.RestauranteRepository;
import com.dutra.food_api.domain.services.exceptions.EntidadeNaoEncontradaException;
import com.dutra.food_api.domain.services.exceptions.PatchMergeFieldsException;
import com.dutra.food_api.domain.services.interfaces.CadastroRestauranteInterface;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ReflectionUtils;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.SmartValidator;

import java.lang.reflect.Field;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Service
public class CadastroRestauranteService implements CadastroRestauranteInterface {

    private static final String RESTAURANTE_NOT_FOUND = "Restaurante não encontrado";

    private final RestauranteRepository restauranteRepository;
    private final CadastroCozinhaService cozinhaService;
    private final SmartValidator validator;

    public CadastroRestauranteService(RestauranteRepository restauranteRepository,
                                      CadastroCozinhaService cozinhaService,
                                      SmartValidator validator) {
        this.restauranteRepository = restauranteRepository;
        this.cozinhaService = cozinhaService;
        this.validator = validator;
    }

    @Transactional(readOnly = true)
    @Override
    public Restaurante buscarPrimeiro() {
        return restauranteRepository.buscarPrimeiro()
                .orElseThrow(() -> new EmptyResultDataAccessException(1));
    }

    @Transactional
    @Override
    public Restaurante salvar(RestauranteInput restaurante) {
        Restaurante restauranteNovo = new Restaurante();

        restauranteNovo.setNome(restaurante.getNome());
        restauranteNovo.setTaxaFrete(restaurante.getTaxaFrete());
        restauranteNovo.setCozinha(cozinhaService.buscar(restaurante.getCozinhaId()));

        return restauranteRepository.save(restauranteNovo);
    }

    @Transactional(readOnly = true)
    @Override
    public Restaurante buscar(Long id) {
        return findRestaurante(id);
    }

    @Transactional(readOnly = true)
    @Override
    public List<Restaurante> buscarTodos() {
        return restauranteRepository.buscarTodosSemN1();
    }

    @Transactional
    @Override
    public Restaurante atualizarTudo(Long id, Restaurante restaurante) {
        Restaurante restauranteAlvo = findRestaurante(id);

        ObjectMapper objectMapper = new ObjectMapper();
        Restaurante restauranteAtualizado = objectMapper.convertValue(restaurante, Restaurante.class);

        restauranteAtualizado.setId(restauranteAlvo.getId());

        return restauranteRepository.save(restauranteAtualizado);
    }

    @Transactional
    @Override
    public Restaurante atualizarParcial(Long id, Map<String, Object> camposInformados) {

        Restaurante  restaurante = findRestaurante(id);

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_IGNORED_PROPERTIES, true);
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, true);

        Restaurante restauranteOrigem = objectMapper.convertValue(camposInformados, Restaurante.class);

        camposInformados.forEach((campoInformado, valor) -> {
            try {
                Field campo = Restaurante.class.getDeclaredField(campoInformado);
                ReflectionUtils.makeAccessible(campo);

                Object novoValor = ReflectionUtils.getField(campo, restauranteOrigem);
                ReflectionUtils.setField(campo, restaurante, novoValor);

            } catch (NoSuchFieldException _) {
                throw new PatchMergeFieldsException("Erro ao atualizar o campo: " + campoInformado);
            }
        });

        //Não funcional
        validarPatch(restaurante);

        return restauranteRepository.save(restaurante);
    }

    private Restaurante findRestaurante(Long id) {
        return restauranteRepository.findById(id)
                .orElseThrow(() -> new EntidadeNaoEncontradaException(RESTAURANTE_NOT_FOUND));
    }

    private void validarPatch(Restaurante restaurante) {
        BeanPropertyBindingResult  bindingResult = new BeanPropertyBindingResult(restaurante, "restaurante");

        validator.validate(restaurante, bindingResult);

        if (bindingResult.hasErrors()) {
            throw new PatchMergeFieldsException("Erro nos campos informados.");
        }
    }
}
