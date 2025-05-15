package com.dutra.food_api.domain.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.*;

@Entity
@Table(name = "tb_restaurante")
public class Restaurante {

    /**
     * Problem N+1.
     * Terminando com ToOne, o carregamento é Eager, ou seja, carrega todos os dados
     * de uma vez.
     * Para isso, deve-se usar a anotação @Fetch(FetchMode.JOIN) em cima do atributo
     * que queremos carregar.
     *
     * Termiando com ToMany, o carregamento é Lazy, ou seja, carrega os dados apenas
     * quando for necessário.
     *
     * Para resolver o problema, deve-se usar a anotação @Fetch(FetchMode.SUBSELECT)
     * em cima do atributo que queremos carregar.
     */

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false)
    private BigDecimal taxaFrete;

    @ManyToOne
    @JoinColumn(name = "cozinha_id", nullable = false)
    private Cozinha cozinha;

    @ManyToMany
    @JoinTable(name = "tb_restaurante_forma_pagamento",
            joinColumns = @JoinColumn(name = "restaurante_id"),
            inverseJoinColumns = @JoinColumn(name = "forma_pagamento_id"))
    private final Set<FormaPagamento> formasPagamento = new HashSet<>();

    @Embedded
    private Endereco endereco;

    private boolean ativo = true;

    private boolean aberto = true;

    @CreationTimestamp
    @Column(columnDefinition = "datetime")
    private OffsetDateTime dataCadastro = OffsetDateTime.now();

    @UpdateTimestamp
    @Column(columnDefinition = "datetime")
    private OffsetDateTime dataAtualizacao;

    @OneToMany(mappedBy = "restaurante")
    private final Set<Produto> produtos = new HashSet<>();

    @ManyToMany
    @JoinTable(name = "tb_restaurante_usuario_responsavel",
            joinColumns = @JoinColumn(name = "restaurante_id"),
            inverseJoinColumns = @JoinColumn(name = "usuario_id"))
    private final Set<Usuario> responsaveis = new HashSet<>();

    public Restaurante() {
    }
    public Restaurante(Long id, String nome, BigDecimal taxaFrete,
                       Cozinha cozinha, Endereco endereco, boolean ativo,
                       OffsetDateTime dataCadastro, OffsetDateTime dataAtualizacao) {
        this.id = id;
        this.nome = nome;
        this.taxaFrete = taxaFrete;
        this.cozinha = cozinha;
        this.endereco = endereco;
        this.ativo = ativo;
        this.dataCadastro = dataCadastro;
        this.dataAtualizacao = dataAtualizacao;
    }

    public boolean aceitaFormaPagamento(FormaPagamento formaPagamento) {
        return getFormasPagamento().contains(formaPagamento);
    }

    public boolean naoAceitaFormaPagamento(FormaPagamento formaPagamento) {
        return !aceitaFormaPagamento(formaPagamento);
    }

    public Set<Usuario> getResponsaveis() {
        return responsaveis;
    }

    public boolean isAberto() {
        return aberto;
    }

    public void setAberto(boolean status) {
        this.aberto = status;
    }

    public void ativar() {
        setAtivo(true);
    }

    public void inativar() {
        setAtivo(false);
    }

    public boolean getAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }

    public Set<Produto> getProdutos() {
        return produtos;
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

    public Set<FormaPagamento> getFormasPagamento() {
        return formasPagamento;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
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

    public Cozinha getCozinha() {
        return cozinha;
    }

    public void setCozinha(Cozinha cozinha) {
        this.cozinha = cozinha;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Restaurante that = (Restaurante) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Restaurante{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", taxaFrete=" + taxaFrete +
                ", cozinha=" + cozinha +
                '}';
    }
}
