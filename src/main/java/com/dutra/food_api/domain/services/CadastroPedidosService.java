package com.dutra.food_api.domain.services;

import com.dutra.food_api.api.model.input.ItemPedidoInput;
import com.dutra.food_api.api.model.input.PedidoInput;
import com.dutra.food_api.api.model.output.PedidoOutput;
import com.dutra.food_api.api.model.output.PedidoOutputShort;
import com.dutra.food_api.domain.models.*;
import com.dutra.food_api.domain.repositories.PedidosRepository;
import com.dutra.food_api.domain.repositories.specifications.PedidosFilterSpec;
import com.dutra.food_api.domain.services.exceptions.EntidadeNaoEncontradaException;
import com.dutra.food_api.domain.services.exceptions.NegotioException;
import com.dutra.food_api.domain.services.interfaces.CadastroPedidosInterface;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class CadastroPedidosService implements CadastroPedidosInterface {

    private final CadastroRestauranteService restauranteService;
    private final CadastroUsuarioService  usuarioService;
    private final PedidosRepository  pedidosRepository;
    private final CadastroProdutoService produtoService;
    private final FormaPagamentoService formaPagamentoService;
    private final CadastroCidadeService cidadeService;



    public CadastroPedidosService(CadastroRestauranteService restauranteService, CadastroCidadeService cidadeService,
                                  CadastroUsuarioService usuarioService, PedidosRepository pedidosRepository,
                                  CadastroProdutoService produtoService, FormaPagamentoService formaPagamentoService) {
        this.restauranteService = restauranteService;
        this.usuarioService = usuarioService;
        this.pedidosRepository = pedidosRepository;
        this.produtoService = produtoService;
        this.formaPagamentoService = formaPagamentoService;
        this.cidadeService = cidadeService;
    }

    @Override
    @Transactional
    public PedidoOutput salvar(PedidoInput pedidoInput) {
        Pedido pedido = criarPedidoBasico(pedidoInput);
        validarEAdicionarItens(pedidoInput, pedido);
        calcularTotais(pedido);
        return PedidoOutput.toPedidoOutput(pedidosRepository.save(pedido));
    }

    @Override
    @Transactional(readOnly = true)
    public PedidoOutput buscarPorId(Long pedidoId) {
        return PedidoOutput.toPedidoOutput(buscaPorPedido(pedidoId));
    }

    @Override
    @Transactional(readOnly = true)
    public PedidoOutput buscarPedidoPorCodigo(String codigo) {
        return PedidoOutput.toPedidoOutput(buscaPorPedidoPorCodigo(codigo));
    }

    @Override
    @Transactional(readOnly = true)
    public List<PedidoOutputShort> buscarTodos() {
        return pedidosRepository.buscarPedidosComDetalhes()
                .stream().map(PedidoOutputShort::toPedidoOutputShort).toList();
    }

    @Override
    @Transactional(readOnly = true)
    public List<PedidoOutputShort> buscarTodosComSpec(Specification<Pedido> specification) {
        return pedidosRepository.findAll(specification)
                .stream().map(PedidoOutputShort::toPedidoOutputShort).toList();
    }

    private Pedido criarPedidoBasico(PedidoInput input) {
        Pedido pedido = input.toEntity();
        pedido.setCliente(usuarioService.buscaUsuarioInterna(input.getUsuarioId()));
        pedido.setRestaurante(restauranteService.findRestaurante(input.getRestauranteId()));

        FormaPagamento formaPagamento = formaPagamentoService.buscaInternaFormaPagamento(input.getFormaPagamentoId());

        if (!pedido.getRestaurante().aceitaFormaPagamento(formaPagamento)) {
            throw new NegotioException("Forma de pagamento não aceita nesse estabelecimento.");
        }

        pedido.setFormaPagamento(formaPagamento);

        Endereco endereco = input.getEnderecoEntrega().toEntity();
        endereco.setCidade(cidadeService.buscaInternaCidade(input.getEnderecoEntrega().getCidadeId()));
        endereco.setEstado(endereco.getCidade().getEstado());
        pedido.setEnderecoEntrega(endereco);

        return pedido;
    }

    private void validarEAdicionarItens(PedidoInput input, Pedido pedido) {
        Long restauranteId = pedido.getRestaurante().getId();
        List<Long> produtoIds = input.getItens().stream()
                .map(ItemPedidoInput::getProdutoId)
                .distinct()
                .toList();

        List<Produto> produtos = produtoService.buscarPorIdsEPorRestaurante(produtoIds, restauranteId);
        if (produtos.size() != produtoIds.size()) {
            throw new NegotioException("Um ou mais produtos não pertencem ao restaurante.");
        }

        Map<Long, Produto> produtoMap = produtos.stream()
                .collect(Collectors.toMap(Produto::getId, Function.identity()));

        for (ItemPedidoInput itemInput : input.getItens()) {
            Produto produto = produtoMap.get(itemInput.getProdutoId());
            ItemPedido item = liquidaItemPedido(itemInput, pedido, produto);
            pedido.getItemPedido().add(item);
        }
    }

    private void calcularTotais(Pedido pedido) {
        pedido.definirFrete();
        pedido.calcularSubTotal();
        pedido.calcularValorTotal();
    }

    protected ItemPedido liquidaItemPedido(ItemPedidoInput itemPedidoInput,
                                           Pedido pedido, Produto produto) {
        ItemPedido itemPedido = itemPedidoInput.toEntity();
        itemPedido.setPrecoUnitario(produto.getPreco());
        itemPedido.setProduto(produto);
        itemPedido.setPedido(pedido);
        itemPedido.calcularPrecoTotal();
        return itemPedido;
    }

    protected Pedido buscaPorPedidoPorCodigo(String codigo){
        return pedidosRepository.buscarPedidoPorCodigo(UUID.fromString(codigo))
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Pedido não encontrado."));
    }

    protected Pedido buscaPorPedido(Long pedidoId){
        return pedidosRepository.findById(pedidoId)
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Pedido não encontrado."));
    }
}
