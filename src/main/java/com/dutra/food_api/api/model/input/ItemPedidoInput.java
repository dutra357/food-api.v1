package com.dutra.food_api.api.model.input;

import com.dutra.food_api.domain.models.ItemPedido;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;

public class ItemPedidoInput {

    @NotNull(message = "Campo requerido")
    @Positive(message = "Deve ser informado um ID positivo para Produto")
    private Long produtoId;

    @NotNull(message = "Campo requerido")
    @PositiveOrZero(message = "Deve ser informado uma quantidade para ItemProduto")
    private Integer quantidade;

    private String observacao = "..";

    public ItemPedidoInput() {

    }

    public ItemPedidoInput(Long produtoId, Integer quantidade, String observacao) {
        this.produtoId = produtoId;
        this.quantidade = quantidade;
        this.observacao = observacao;
    }

    public ItemPedido toEntity() {
        ItemPedido itemPedido = new ItemPedido();
        itemPedido.setQuantidade(quantidade);
        itemPedido.setObservacao(observacao);
        return itemPedido;
    }

    public Long getProdutoId() {
        return produtoId;
    }

    public void setProdutoId(Long produtoId) {
        this.produtoId = produtoId;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }
}
