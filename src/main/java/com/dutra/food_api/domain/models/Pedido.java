package com.dutra.food_api.domain.models;

import com.dutra.food_api.domain.models.enumerations.StatusPedido;
import com.dutra.food_api.domain.services.exceptions.NegotioException;
import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.*;

@Entity
@Table(name = "tb_pedido")
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private UUID codigo = UUID.randomUUID();

    @Column(nullable = false)
    private BigDecimal subTotal;

    @Column(nullable = false)
    private BigDecimal taxaFrete;

    @Column(nullable = false)
    private BigDecimal valorTotal;

    @CreationTimestamp
    @Column(columnDefinition = "timestamp with time zone", nullable = false)
    private OffsetDateTime  dataCriacao;

    @Column(columnDefinition = "timestamp with time zone")
    private OffsetDateTime dataConfirmacao;

    @Column(columnDefinition = "timestamp with time zone")
    private OffsetDateTime dataCancelamento;

    @Column(columnDefinition = "timestamp with time zone")
    private OffsetDateTime dataEntrega;

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

    @OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL)
    private final List<ItemPedido> itemPedido = new ArrayList<>();


    public Pedido() {
    }

    public Pedido(Long id, BigDecimal subTotal, BigDecimal taxaFrete,
                  BigDecimal valorTotal, OffsetDateTime dataCriacao, OffsetDateTime dataConfirmacao,
                  OffsetDateTime dataCancelamento, OffsetDateTime dataEntrega, Endereco enderecoEntrega,
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
        this.valorTotal = getSubTotal().add(getTaxaFrete());
    }

    public void calcularSubTotal() {
        this.subTotal = getItemPedido().stream()
                .map(ItemPedido::getPrecoTotal)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public void definirFrete() {
        setTaxaFrete(getRestaurante().getTaxaFrete());
    }

    public void atribuirPedidoAosItens() {
        getItemPedido().forEach(item -> item.setPedido(this));
    }

    public void confirmar() {
        setDataConfirmacao(OffsetDateTime.now());
        setStatus(StatusPedido.CONFIRMADO);
    }

    public void cancelar() {
        setDataCancelamento(OffsetDateTime.now());
        setStatus(StatusPedido.CANCELADO);
    }

    public void entregue() {
        setDataEntrega(OffsetDateTime.now());
        setStatus(StatusPedido.ENTREGUE);
    }

    public void setStatus(StatusPedido novoStatus) {
        if (getStatus().naoPodeAlterarPara(novoStatus)) {
            throw new NegotioException(
                    String.format("Impossível alterar o status de %s para %s",
                            getStatus().getDescricao(),
                            novoStatus.getDescricao())
            );
        }

        this.status = novoStatus;
    }

    public UUID getCodigo() {
        return codigo;
    }

    public void setCodigo(UUID codigo) {
        this.codigo = codigo;
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

    public OffsetDateTime getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(OffsetDateTime dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    public OffsetDateTime getDataConfirmacao() {
        return dataConfirmacao;
    }

    public void setDataConfirmacao(OffsetDateTime dataConfirmacao) {
        this.dataConfirmacao = dataConfirmacao;
    }

    public OffsetDateTime getDataCancelamento() {
        return dataCancelamento;
    }

    public void setDataCancelamento(OffsetDateTime dataCancelamento) {
        this.dataCancelamento = dataCancelamento;
    }

    public OffsetDateTime getDataEntrega() {
        return dataEntrega;
    }

    public void setDataEntrega(OffsetDateTime dataEntrega) {
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

    public List<ItemPedido> getItemPedido() {
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

    @Override
    public String toString() {
        return "Pedido{" +
                "id=" + id +
                ", valorTotal=" + valorTotal +
                ", dataCriacao=" + dataCriacao +
                ", status=" + status +
                ", restaurante=" + restaurante +
                ", cliente=" + cliente +
                '}';
    }
}
