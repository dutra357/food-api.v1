package com.dutra.food_api.api.model.input;

import com.dutra.food_api.domain.models.Cozinha;
import jakarta.validation.constraints.NotNull;

public class CozinhaInput {

    @NotNull(message = "Campo requerido")
    private String nome;

    public CozinhaInput() {

    }

    public CozinhaInput(String nome) {
        this.nome = nome;
    }

    public Cozinha toEntity() {
        Cozinha cozinha = new Cozinha();
        cozinha.setNome(nome);
        return cozinha;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
