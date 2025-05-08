package com.dutra.food_api.api.model.output;

import com.dutra.food_api.domain.models.Estado;

import java.util.Objects;

public class EstadoOutput {

    private Long id;
    private String nome;

    public EstadoOutput() {
    }

    public EstadoOutput(Long id, String nome) {
        this.id = id;
        this.nome = nome;
    }

    public static EstadoOutput toEstadoOutput(Estado estado) {
        return new EstadoOutput(estado.getId(), estado.getNome());
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        EstadoOutput that = (EstadoOutput) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
