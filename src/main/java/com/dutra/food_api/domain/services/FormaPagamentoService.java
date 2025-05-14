package com.dutra.food_api.domain.services;

import com.dutra.food_api.api.model.input.FormaPagamentoInput;
import com.dutra.food_api.api.model.output.FormaPagamentoOutput;
import com.dutra.food_api.domain.models.FormaPagamento;
import com.dutra.food_api.domain.repositories.FormaPagamentoRepository;
import com.dutra.food_api.domain.services.exceptions.EntidadeEmUsoException;
import com.dutra.food_api.domain.services.exceptions.EntidadeNaoEncontradaException;
import com.dutra.food_api.domain.services.interfaces.CadastroFormaPagamentoInterface;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class FormaPagamentoService implements CadastroFormaPagamentoInterface {

    public final FormaPagamentoRepository formaPagamentoRepository;

    public FormaPagamentoService(FormaPagamentoRepository formaPagamentoRepository) {
        this.formaPagamentoRepository = formaPagamentoRepository;
    }


    @Transactional
    @Override
    public FormaPagamentoOutput salvar(FormaPagamentoInput formaPagamentoInput) {
        FormaPagamento formaPagamento = formaPagamentoInput.toEntity();
        return FormaPagamentoOutput.toFormaPagamentoOutput(formaPagamentoRepository.save(formaPagamento));
    }

    @Transactional
    @Override
    public FormaPagamentoOutput atualizar(Long formaPagamentoId, FormaPagamentoInput formaPagamentoInput) {
        FormaPagamento formaPagamentoAtual = buscaInternaFormaPagamento(formaPagamentoId);
        formaPagamentoAtual.setDescricao(formaPagamentoInput.getDescricao());

        return FormaPagamentoOutput.toFormaPagamentoOutput(formaPagamentoRepository.save(formaPagamentoAtual));
    }

    @Transactional
    @Override
    public void excluir(Long formaPagamentoId) {
        FormaPagamento formaPagamento = buscaInternaFormaPagamento(formaPagamentoId);

        try {
            formaPagamentoRepository.delete(formaPagamento);
            formaPagamentoRepository.flush();

        } catch (DataIntegrityViolationException _) {
            throw new EntidadeEmUsoException(
                    String.format("Forma de Pagamento de código %d não pode ser removida, pois está em uso", formaPagamentoId));
        }

    }

    @Transactional(readOnly = true)
    @Override
    public FormaPagamentoOutput buscarPorId(Long formaPagamentoId) {
        return FormaPagamentoOutput.toFormaPagamentoOutput(buscaInternaFormaPagamento(formaPagamentoId));
    }

    @Transactional(readOnly = true)
    @Override
    public List<FormaPagamentoOutput> buscarTodas() {
        return formaPagamentoRepository.findAll().stream().map(FormaPagamentoOutput::toFormaPagamentoOutput).toList();
    }

    private FormaPagamento buscaInternaFormaPagamento(Long id) {
        return formaPagamentoRepository.findById(id)
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Forma de pagamento não encontrada"));

    }

    protected FormaPagamento buscaPagamentoRestaurante(Long id) {
        return formaPagamentoRepository.findById(id)
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Forma de pagamento não encontrada"));

    }
}
