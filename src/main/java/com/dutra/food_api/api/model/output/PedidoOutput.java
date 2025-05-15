package com.dutra.food_api.api.model.output;

import com.dutra.food_api.domain.models.*;
import com.dutra.food_api.domain.models.enumerations.StatusPedido;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class PedidoOutput {


    private Long id;
    private BigDecimal subTotal;
    private BigDecimal taxaFrete;
    private BigDecimal valorTotal;

    private StatusPedido status;

    private LocalDate dataCriacao;
    private LocalDate dataConfirmacao;
    private LocalDate dataEntrega;
    private LocalDate dataCancelamento;

    private RestauranteOutput restaurante;
    private UsuarioOutput cliente;
    private FormaPagamento formaPagamento;
    private EnderecoOutput enderecoEntrega;

    private final List<ItemPedidoOutput> itensPedido = new ArrayList<>();

    public PedidoOutput() {
    }

    public PedidoOutput(Long id, BigDecimal subTotal, BigDecimal taxaFrete,
                        BigDecimal valorTotal, StatusPedido status, LocalDate dataCriacao,
                        LocalDate dataConfirmacao, LocalDate dataEntrega,
                        LocalDate dataCancelamento, RestauranteOutput restaurante,
                        UsuarioOutput cliente, FormaPagamento formaPagamento,
                        EnderecoOutput enderecoEntrega) {
        this.id = id;
        this.subTotal = subTotal;
        this.taxaFrete = taxaFrete;
        this.valorTotal = valorTotal;
        this.status = status;
        this.dataCriacao = dataCriacao;
        this.dataConfirmacao = dataConfirmacao;
        this.dataEntrega = dataEntrega;
        this.dataCancelamento = dataCancelamento;
        this.restaurante = restaurante;
        this.cliente = cliente;
        this.formaPagamento = formaPagamento;
        this.enderecoEntrega = enderecoEntrega;
    }

    public static PedidoOutput toPedidoOutput(Pedido pedido) {
        if (pedido == null) return null;
        PedidoOutput pedidoOutput = new PedidoOutput(
                pedido.getId(),
                pedido.getSubTotal(),
                pedido.getTaxaFrete(),
                pedido.getValorTotal(),
                pedido.getStatus(),
                pedido.getDataCriacao(),
                pedido.getDataConfirmacao(),
                pedido.getDataEntrega(),
                pedido.getDataCancelamento(),
                RestauranteOutput.toRestauranteOutput(pedido.getRestaurante()),
                UsuarioOutput.toUsuarioOutput(pedido.getCliente()),
                pedido.getFormaPagamento(),
                EnderecoOutput.toEnderecoOutput(pedido.getEnderecoEntrega())
        );

        for (ItemPedido itemPedido : pedido.getItemPedido()) {
            pedidoOutput.getItensPedido().add(ItemPedidoOutput.toItemPedidoOutput(itemPedido));
        }

        return pedidoOutput;
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

    public LocalDate getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(LocalDate dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    public LocalDate getDataConfirmacao() {
        return dataConfirmacao;
    }

    public void setDataConfirmacao(LocalDate dataConfirmacao) {
        this.dataConfirmacao = dataConfirmacao;
    }

    public LocalDate getDataEntrega() {
        return dataEntrega;
    }

    public void setDataEntrega(LocalDate dataEntrega) {
        this.dataEntrega = dataEntrega;
    }

    public LocalDate getDataCancelamento() {
        return dataCancelamento;
    }

    public void setDataCancelamento(LocalDate dataCancelamento) {
        this.dataCancelamento = dataCancelamento;
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

    public FormaPagamento getFormaPagamento() {
        return formaPagamento;
    }

    public void setFormaPagamento(FormaPagamento formaPagamento) {
        this.formaPagamento = formaPagamento;
    }

    public EnderecoOutput getEnderecoEntrega() {
        return enderecoEntrega;
    }

    public void setEnderecoEntrega(EnderecoOutput enderecoEntrega) {
        this.enderecoEntrega = enderecoEntrega;
    }

    public List<ItemPedidoOutput> getItensPedido() {
        return itensPedido;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        PedidoOutput that = (PedidoOutput) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
