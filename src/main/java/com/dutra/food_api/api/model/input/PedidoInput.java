package com.dutra.food_api.api.model.input;

import com.dutra.food_api.domain.models.Pedido;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.util.ArrayList;
import java.util.List;

public class PedidoInput {

    @NotNull(message = "Campo requerido")
    @Positive(message = "Deve ser informado um ID positivo para Restaurante")
    private Long usuarioId;

    @NotNull(message = "Campo requerido")
    @Positive(message = "Deve ser informado um ID positivo para Restaurante")
    private Long restauranteId;

    @NotNull(message = "Campo requerido")
    @Positive(message = "Deve ser informado um ID positivo para Forma de Pagamento")
    private Long formaPagamentoId;

    @Valid
    @NotNull(message = "Campo requerido")
    private EnderecoInput enderecoEntrega;

    @Valid
    @NotNull(message = "Campo requerido")
    private final List<ItemPedidoInput> itens = new ArrayList<>();

    public PedidoInput() {

    }

    public PedidoInput(Long usuarioId, Long restauranteId,
                       Long formaPagamentoId, EnderecoInput enderecoEntrega) {
        this.restauranteId = restauranteId;
        this.formaPagamentoId = formaPagamentoId;
        this.enderecoEntrega = enderecoEntrega;
        this.usuarioId = usuarioId;
    }

    public Pedido toEntity() {
        Pedido pedido = new Pedido();
        pedido.setEnderecoEntrega(enderecoEntrega.toEntity());
        return pedido;
    }

    public Long getRestauranteId() {
        return restauranteId;
    }

    public void setRestauranteId(Long restauranteId) {
        this.restauranteId = restauranteId;
    }

    public Long getFormaPagamentoId() {
        return formaPagamentoId;
    }

    public void setFormaPagamentoId(Long formaPagamentoId) {
        this.formaPagamentoId = formaPagamentoId;
    }

    public EnderecoInput getEnderecoEntrega() {
        return enderecoEntrega;
    }

    public void setEnderecoEntrega(EnderecoInput enderecoEntrega) {
        this.enderecoEntrega = enderecoEntrega;
    }

    public List<ItemPedidoInput> getItens() {
        return itens;
    }

    public Long getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(Long usuarioId) {
        this.usuarioId = usuarioId;
    }
}
