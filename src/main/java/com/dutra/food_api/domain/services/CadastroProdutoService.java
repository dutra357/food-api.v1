package com.dutra.food_api.domain.services;

import com.dutra.food_api.api.model.input.ProdutoInput;
import com.dutra.food_api.api.model.output.ProdutoOutput;
import com.dutra.food_api.domain.models.Produto;
import com.dutra.food_api.domain.repositories.ProdutoRepository;
import com.dutra.food_api.domain.services.exceptions.EntidadeEmUsoException;
import com.dutra.food_api.domain.services.exceptions.EntidadeNaoEncontradaException;
import com.dutra.food_api.domain.services.interfaces.CadastroProdutoInterface;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CadastroProdutoService implements CadastroProdutoInterface {

    private static final String PRODUTO_NOT_FOUND = "Produto não encontrado.";

    private final ProdutoRepository produtoRepository;

    public CadastroProdutoService(ProdutoRepository produtoRepository) {
        this.produtoRepository = produtoRepository;
    }

    @Transactional
    @Override
    public ProdutoOutput salvar(ProdutoInput produtoInput) {
        Produto produto = produtoInput.toEntity();
        return ProdutoOutput.toProdutoOutput(produtoRepository.save(produto));
    }

    @Transactional
    @Override
    public ProdutoOutput atualizar(Long produtoId, ProdutoInput produtoInput) {
        Produto produtoAtual = buscarProduto(produtoId);
        produtoAtual.setNome(produtoInput.getNome());
        return ProdutoOutput.toProdutoOutput(produtoRepository.save(produtoAtual));
    }

    @Override
    public void excluir(Long produtoId) {
        Produto produto = buscarProduto(produtoId);

        try {
            produtoRepository.delete(produto);
            produtoRepository.flush();

        } catch (DataIntegrityViolationException _) {
            throw new EntidadeEmUsoException(
                    String.format("Produto de código %d não pode ser removido, pois está em uso", produtoId));
        }
    }

    @Transactional(readOnly = true)
    @Override
    public ProdutoOutput buscarPorId(Long produtoId) {
        return ProdutoOutput.toProdutoOutput(buscarProduto(produtoId));
    }

    @Transactional(readOnly = true)
    @Override
    public List<ProdutoOutput> buscarTodas() {
        return produtoRepository.findAll().stream().map(ProdutoOutput::toProdutoOutput).toList();
    }

    @Override
    @Transactional(readOnly = true)
    public List<ProdutoOutput> findAtivoByRestaurante(Long restauranteId, boolean incluirInativos) {

        if (incluirInativos) {
            return produtoRepository.findAtivoByRestaurante(restauranteId)
                    .stream().map(ProdutoOutput::toProdutoOutput).toList();
        }

        return produtoRepository.findAllByRestaurante(restauranteId)
                .stream().map(ProdutoOutput::toProdutoOutput).toList();
    }

    protected List<Produto> buscarPorIdsEPorRestaurante(@Param("ids") List<Long> ids, Long restauranteId) {
        return produtoRepository.buscarPorIdsEPorRestaurante(ids, restauranteId);
    }

    protected Produto buscarOuFalhar(Long restauranteId, Long produtoId) {
        return produtoRepository.findById(restauranteId, produtoId)
                .orElseThrow(() -> new EntidadeNaoEncontradaException(PRODUTO_NOT_FOUND));
    }

    protected Produto buscarProduto(Long id) {
        return produtoRepository.findById(id)
                .orElseThrow(() -> new EntidadeNaoEncontradaException(PRODUTO_NOT_FOUND));
    }
}
