package com.dutra.food_api.domain.services;

import com.dutra.food_api.api.model.output.PedidoOutput;
import com.dutra.food_api.api.model.output.PedidoOutputShort;
import com.dutra.food_api.domain.models.Pedido;
import com.dutra.food_api.domain.repositories.ItemPedidoRepository;
import com.dutra.food_api.domain.repositories.PedidosRepository;
import com.dutra.food_api.domain.services.exceptions.EntidadeNaoEncontradaException;
import com.dutra.food_api.domain.services.interfaces.CadastroPedidosInterface;
import com.dutra.food_api.domain.services.interfaces.CadastroRestauranteInterface;
import com.dutra.food_api.domain.services.interfaces.CadastroUsuarioInterface;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CadastroPedidosService implements CadastroPedidosInterface {

    private final CadastroRestauranteInterface restauranteService;
    private final CadastroUsuarioInterface  usuarioService;
    private final PedidosRepository  pedidosRepository;



    public CadastroPedidosService(CadastroRestauranteInterface restauranteService,
                                  CadastroUsuarioInterface usuarioService, PedidosRepository pedidosRepository) {
        this.restauranteService = restauranteService;
        this.usuarioService = usuarioService;
        this.pedidosRepository = pedidosRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public PedidoOutput buscarPorId(Long pedidoId) {
        return PedidoOutput.toPedidoOutput(buscaPorPedido(pedidoId));
    }

    @Override
    @Transactional(readOnly = true)
    public List<PedidoOutputShort> buscarTodos() {
        return pedidosRepository.buscarPedidosComDetalhes()
                .stream().map(PedidoOutputShort::toPedidoOutputShort).toList();
    }


    protected Pedido buscaPorPedido(Long pedidoId){
        return pedidosRepository.findById(pedidoId)
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Pedido não encontrado."));
    }
}
