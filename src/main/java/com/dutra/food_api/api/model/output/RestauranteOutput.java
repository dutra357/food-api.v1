package com.dutra.food_api.api.model.output;

import com.dutra.food_api.domain.models.Restaurante;


import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.Objects;

public class RestauranteOutput {

    private Long id;
    private String nome;
    private BigDecimal taxaFrete;
    private Boolean ativo;
    private Boolean aberto;
    private CozinhaOutput cozinha;

    private EnderecoOutput enderecoOutput;

    private OffsetDateTime dataCadastro;
    private OffsetDateTime dataAtualizacao;

    public RestauranteOutput() {
    }

    public RestauranteOutput(Long id, String nome, BigDecimal taxaFrete,
                             CozinhaOutput cozinha, EnderecoOutput enderecoOutput,
                             OffsetDateTime dataCadastro, OffsetDateTime dataAtualizacao,
                             boolean  ativo, boolean aberto) {
        this.id = id;
        this.nome = nome;
        this.taxaFrete = taxaFrete;
        this.cozinha = cozinha;
        this.enderecoOutput = enderecoOutput;
        this.dataCadastro = dataCadastro;
        this.dataAtualizacao = dataAtualizacao;
        this.ativo = ativo;
        this.aberto = aberto;
    }

    public static RestauranteOutput toRestauranteOutput(Restaurante restaurante) {
        return new RestauranteOutput(
                restaurante.getId(),
                restaurante.getNome(),
                restaurante.getTaxaFrete(),
                CozinhaOutput.toCozinhaOutput(restaurante.getCozinha()),
                EnderecoOutput.toEnderecoOutput(restaurante.getEndereco()),
                restaurante.getDataCadastro(),
                restaurante.getDataAtualizacao(),
                restaurante.getAtivo(),
                restaurante.isAberto()
        );
    }

    public Boolean getAberto() {
        return aberto;
    }

    public void setAberto(Boolean aberto) {
        this.aberto = aberto;
    }

    public EnderecoOutput getEnderecoOutput() {
        return enderecoOutput;
    }

    public void setEnderecoOutput(EnderecoOutput enderecoOutput) {
        this.enderecoOutput = enderecoOutput;
    }

    public Boolean getAtivo() {
        return ativo;
    }

    public void setAtivo(Boolean ativo) {
        this.ativo = ativo;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public BigDecimal getTaxaFrete() {
        return taxaFrete;
    }

    public void setTaxaFrete(BigDecimal taxaFrete) {
        this.taxaFrete = taxaFrete;
    }

    public CozinhaOutput getCozinha() {
        return cozinha;
    }

    public void setCozinha(CozinhaOutput cozinha) {
        this.cozinha = cozinha;
    }

    public EnderecoOutput getEndereco() {
        return enderecoOutput;
    }

    public void setEndereco(EnderecoOutput enderecoOutput) {
        this.enderecoOutput = enderecoOutput;
    }

    public OffsetDateTime getDataCadastro() {
        return dataCadastro;
    }

    public void setDataCadastro(OffsetDateTime dataCadastro) {
        this.dataCadastro = dataCadastro;
    }

    public OffsetDateTime getDataAtualizacao() {
        return dataAtualizacao;
    }

    public void setDataAtualizacao(OffsetDateTime dataAtualizacao) {
        this.dataAtualizacao = dataAtualizacao;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        RestauranteOutput that = (RestauranteOutput) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
