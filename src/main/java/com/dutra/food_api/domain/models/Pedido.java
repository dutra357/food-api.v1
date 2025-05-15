package com.dutra.food_api.domain.models;

import com.dutra.food_api.domain.models.enumerations.StatusPedido;
import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.*;

@Entity
@Table(name = "tb_pedido")
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private BigDecimal subTotal;

    @Column(nullable = false)
    private BigDecimal taxaFrete;

    @Column(nullable = false)
    private BigDecimal valorTotal;

    @CreationTimestamp
    @Column(columnDefinition = "datetime", nullable = false)
    private LocalDate  dataCriacao;

    @Column(columnDefinition = "datetime")
    private LocalDate dataConfirmacao;

    @Column(columnDefinition = "datetime")
    private LocalDate dataCancelamento;

    @Column(columnDefinition = "datetime")
    private LocalDate dataEntrega;

    @Embedded
    @Column(nullable = false)
    private Endereco enderecoEntrega;

    @Column(nullable = false)
    private StatusPedido status = StatusPedido.CRIADO;

    @ManyToOne
    @JoinColumn(name = "forma_pagamento_id", nullable = false)
    private FormaPagamento formaPagamento;

    @ManyToOne
    @JoinColumn(name = "restaurante_id", nullable = false)
    private Restaurante restaurante;

    @ManyToOne
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario cliente;

    @OneToMany(mappedBy = "pedido")
    private final Set<ItemPedido> itemPedido = new HashSet<>();


    public Pedido() {
    }

    public Pedido(Long id, BigDecimal subTotal, BigDecimal taxaFrete,
                  BigDecimal valorTotal, LocalDate dataCriacao, LocalDate dataConfirmacao,
                  LocalDate dataCancelamento, LocalDate dataEntrega, Endereco enderecoEntrega,
                  StatusPedido status, FormaPagamento formaPagamento, Restaurante restaurante, Usuario cliente) {
        this.id = id;
        this.subTotal = subTotal;
        this.taxaFrete = taxaFrete;
        this.valorTotal = valorTotal;
        this.dataCriacao = dataCriacao;
        this.dataConfirmacao = dataConfirmacao;
        this.dataCancelamento = dataCancelamento;
        this.dataEntrega = dataEntrega;
        this.enderecoEntrega = enderecoEntrega;
        this.status = status;
        this.formaPagamento = formaPagamento;
        this.restaurante = restaurante;
        this.cliente = cliente;
    }

    public void calcularValorTotal() {
        this.subTotal = getItemPedido().stream()
                .map(ItemPedido::getPrecoTotal)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        this.valorTotal = this.subTotal.add(this.taxaFrete);
    }

    public void definirFrete() {
        setTaxaFrete(getRestaurante().getTaxaFrete());
    }

    public void atribuirPedidoAosItens() {
        getItemPedido().forEach(item -> item.setPedido(this));
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

    public LocalDate getDataCancelamento() {
        return dataCancelamento;
    }

    public void setDataCancelamento(LocalDate dataCancelamento) {
        this.dataCancelamento = dataCancelamento;
    }

    public LocalDate getDataEntrega() {
        return dataEntrega;
    }

    public void setDataEntrega(LocalDate dataEntrega) {
        this.dataEntrega = dataEntrega;
    }

    public Endereco getEnderecoEntrega() {
        return enderecoEntrega;
    }

    public void setEnderecoEntrega(Endereco enderecoEntrega) {
        this.enderecoEntrega = enderecoEntrega;
    }

    public StatusPedido getStatus() {
        return status;
    }

    public void setStatus(StatusPedido status) {
        this.status = status;
    }

    public FormaPagamento getFormaPagamento() {
        return formaPagamento;
    }

    public void setFormaPagamento(FormaPagamento formaPagamento) {
        this.formaPagamento = formaPagamento;
    }

    public Restaurante getRestaurante() {
        return restaurante;
    }

    public void setRestaurante(Restaurante restaurante) {
        this.restaurante = restaurante;
    }

    public Usuario getCliente() {
        return cliente;
    }

    public void setCliente(Usuario cliente) {
        this.cliente = cliente;
    }

    public Set<ItemPedido> getItemPedido() {
        return itemPedido;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Pedido pedido = (Pedido) o;
        return Objects.equals(id, pedido.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
