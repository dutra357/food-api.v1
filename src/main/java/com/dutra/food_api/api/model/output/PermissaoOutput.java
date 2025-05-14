package com.dutra.food_api.api.model.output;

import com.dutra.food_api.domain.models.Permissao;

import java.util.Objects;

public class PermissaoOutput {

    private Long id;
    private String nome;
    private String descricao;

    public PermissaoOutput() {}

    public PermissaoOutput(Long id, String nome, String descricao) {
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
    }

    public static PermissaoOutput toPermissaoOutput(Permissao permissao) {
        return new PermissaoOutput(permissao.getId(), permissao.getNome(), permissao.getDescricao());
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

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        PermissaoOutput that = (PermissaoOutput) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
