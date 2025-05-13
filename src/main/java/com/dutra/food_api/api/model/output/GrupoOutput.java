package com.dutra.food_api.api.model.output;

import com.dutra.food_api.domain.models.Grupo;

import java.util.Objects;

public class GrupoOutput {

    private Long id;
    private String nome;

    public GrupoOutput() {}

    public GrupoOutput(Long id, String nome) {
        this.id = id;
        this.nome = nome;
    }

    public static GrupoOutput toGrupoOutput(Grupo grupo) {
        return new GrupoOutput(grupo.getId(), grupo.getNome());
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

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        GrupoOutput that = (GrupoOutput) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
