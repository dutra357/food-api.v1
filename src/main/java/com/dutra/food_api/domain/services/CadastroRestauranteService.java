package com.dutra.food_api.domain.services;

import com.dutra.food_api.api.model.input.RestauranteInput;
import com.dutra.food_api.api.model.output.FormaPagamentoOutput;
import com.dutra.food_api.api.model.output.RestauranteOutput;
import com.dutra.food_api.domain.models.Restaurante;
import com.dutra.food_api.domain.repositories.RestauranteRepository;
import com.dutra.food_api.domain.services.exceptions.EntidadeEmUsoException;
import com.dutra.food_api.domain.services.exceptions.EntidadeNaoEncontradaException;
import com.dutra.food_api.domain.services.exceptions.PatchMergeFieldsException;
import com.dutra.food_api.domain.services.interfaces.CadastroFormaPagamentoInterface;
import com.dutra.food_api.domain.services.interfaces.CadastroRestauranteInterface;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ReflectionUtils;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.SmartValidator;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;

@Service
public class CadastroRestauranteService implements CadastroRestauranteInterface {

    private static final String RESTAURANTE_NOT_FOUND = "Restaurante não encontrado";

    private final RestauranteRepository restauranteRepository;
    private final CadastroCozinhaService cozinhaService;
    private final CadastroCidadeService cidadeService;
    private final FormaPagamentoService  serviceFormaPagamento;

    private final SmartValidator validator;

    public CadastroRestauranteService(RestauranteRepository restauranteRepository,
                                      CadastroCozinhaService cozinhaService, CadastroCidadeService cidadeService,
                                      FormaPagamentoService serviceFormaPagamento, SmartValidator validator) {
        this.restauranteRepository = restauranteRepository;
        this.cozinhaService = cozinhaService;
        this.cidadeService = cidadeService;
        this.serviceFormaPagamento = serviceFormaPagamento;
        this.validator = validator;
    }

    @Transactional(readOnly = true)
    @Override
    public RestauranteOutput buscarPrimeiro() {
        return RestauranteOutput.toRestauranteOutput(restauranteRepository.buscarPrimeiro()
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Restaurante não encontrado.")));
    }

    @Transactional
    @Override
    public RestauranteOutput salvar(RestauranteInput restauranteInput) {

        Restaurante restauranteNovo = restauranteInput.toEntity();
        restauranteNovo.setCozinha(cozinhaService.buscarCozinha(restauranteInput.getCozinhaId()));

        restauranteNovo.getEndereco()
                .setCidade(cidadeService.buscaInternaCidade(restauranteInput.getEndereco().getCidadeId()));

        restauranteNovo.getEndereco()
                .setEstado(restauranteNovo.getEndereco().getCidade().getEstado());

        return RestauranteOutput.toRestauranteOutput(restauranteRepository.save(restauranteNovo));
    }

    @Transactional(readOnly = true)
    @Override
    public RestauranteOutput buscar(Long id) {
        return RestauranteOutput.toRestauranteOutput(findRestaurante(id));
    }

    @Transactional(readOnly = true)
    @Override
    public List<RestauranteOutput> buscarTodos() {
        return restauranteRepository.buscarTodosSemN1()
                .stream().map(RestauranteOutput::toRestauranteOutput).toList();
    }

    @Transactional
    @Override
    public RestauranteOutput atualizarTudo(Long id, RestauranteInput restaurante) {
        Restaurante restauranteAlvo = findRestaurante(id);

        ObjectMapper objectMapper = new ObjectMapper();
        Restaurante restauranteAtualizado = objectMapper.convertValue(restaurante, Restaurante.class);

        restauranteAtualizado.setId(restauranteAlvo.getId());

        return RestauranteOutput.toRestauranteOutput(restauranteRepository.save(restauranteAtualizado));
    }

    @Transactional
    @Override
    public RestauranteOutput atualizarParcial(Long id, Map<String, Object> camposInformados) {

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

        return RestauranteOutput.toRestauranteOutput(restauranteRepository.save(restaurante));
    }

    @Transactional
    @Override
    public void remover(Long id) {
        Restaurante restaurante = findRestaurante(id);

        try {
            restauranteRepository.delete(restaurante);
            restauranteRepository.flush();

        } catch (DataIntegrityViolationException _) {
            throw new EntidadeEmUsoException(
                    String.format("Restaurante de código %d não pode ser removida, pois está em uso", id));
        }
    }

    @Transactional(propagation = Propagation.MANDATORY)
    @Override
    public void ativar(Long id) {
        Restaurante restaurante = findRestaurante(id);
        restaurante.ativar();
        //Instância em estado gerenciado
        //dispensado o 'save'.
    }

    @Transactional(propagation = Propagation.MANDATORY)
    @Override
    public void inativar(Long id) {
        Restaurante restaurante = findRestaurante(id);
        restaurante.inativar();
    }

    @Transactional(readOnly = true)
    @Override
    public List<FormaPagamentoOutput> buscarFormasPagamento(Long restauranteId) {
        Restaurante restaurante = findRestaurante(restauranteId);
        return restaurante.getFormasPagamento()
                .stream().map(FormaPagamentoOutput::toFormaPagamentoOutput).toList();
    }

    @Transactional
    @Override
    public void removerFormaPagamento(Long restauranteId, Long formaPagamentoId) {
        Restaurante restaurante = findRestaurante(restauranteId);
        restaurante.getFormasPagamento()
                .removeIf(formaPagamento -> formaPagamento.getId().equals(formaPagamentoId));
    }

    @Transactional
    @Override
    public void associarFormaPagamento(Long restauranteId, Long formaPagamentoId) {
        Restaurante restaurante = findRestaurante(restauranteId);
        restaurante.getFormasPagamento()
                .add(serviceFormaPagamento.buscaPagamentoRestaurante(formaPagamentoId));
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
