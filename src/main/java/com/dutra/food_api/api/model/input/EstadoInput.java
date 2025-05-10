package com.dutra.food_api.api.model.input;

import com.dutra.food_api.domain.models.Estado;


public class EstadoInput {

    private String nome;

    public EstadoInput() {
    }

    public EstadoInput(String nome) {
        this.nome = nome;
    }

    public Estado toEntity() {
        Estado estado = new Estado();
        estado.setNome(nome);
        return estado;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
