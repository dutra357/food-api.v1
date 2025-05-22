package com.dutra.food_api.api.model.output;

import com.dutra.food_api.domain.models.Pedido;
import com.dutra.food_api.domain.models.enumerations.StatusPedido;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.Objects;

public class PedidoOutputShort {


    private Long id;

    private BigDecimal subTotal;
    private BigDecimal taxaFrete;
    private BigDecimal valorTotal;

    private StatusPedido status;

    private OffsetDateTime dataCriacao;
    private RestauranteOutput restaurante;
    private UsuarioOutput cliente;

    public PedidoOutputShort() {
    }

    public PedidoOutputShort(Long id, BigDecimal subTotal,
                             BigDecimal taxaFrete, BigDecimal valorTotal,
                             StatusPedido status, OffsetDateTime dataCriacao,
                             RestauranteOutput restaurante, UsuarioOutput cliente) {
        this.id = id;
        this.subTotal = subTotal;
        this.taxaFrete = taxaFrete;
        this.valorTotal = valorTotal;
        this.status = status;
        this.dataCriacao = dataCriacao;
        this.restaurante = restaurante;
        this.cliente = cliente;
    }

    public static PedidoOutputShort toPedidoOutputShort(Pedido pedido) {
        return new PedidoOutputShort(
                pedido.getId(),
                pedido.getSubTotal(),
                pedido.getTaxaFrete(),
                pedido.getValorTotal(),
                pedido.getStatus(),
                pedido.getDataCriacao(),
                RestauranteOutput.toRestauranteOutput(pedido.getRestaurante()),
                UsuarioOutput.toUsuarioOutput(pedido.getCliente())
        );
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getSubTotal() {
        return subTotal;
    }

    public void setSubTotal(BigDecimal subTotal) {
        this.subTotal = subTotal;
    }

    public BigDecimal getTaxaFrete() {
        return taxaFrete;
    }

    public void setTaxaFrete(BigDecimal taxaFrete) {
        this.taxaFrete = taxaFrete;
    }

    public BigDecimal getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(BigDecimal valorTotal) {
        this.valorTotal = valorTotal;
    }

    public StatusPedido getStatus() {
        return status;
    }

    public void setStatus(StatusPedido status) {
        this.status = status;
    }

    public OffsetDateTime getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(OffsetDateTime dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    public RestauranteOutput getRestaurante() {
        return restaurante;
    }

    public void setRestaurante(RestauranteOutput restaurante) {
        this.restaurante = restaurante;
    }

    public UsuarioOutput getCliente() {
        return cliente;
    }

    public void setCliente(UsuarioOutput cliente) {
        this.cliente = cliente;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        PedidoOutputShort that = (PedidoOutputShort) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
