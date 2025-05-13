package com.dutra.food_api.api.model.input;

import com.dutra.food_api.domain.models.Grupo;
import jakarta.validation.constraints.NotNull;

public class GrupoInput {

    @NotNull(message = "Campo requerido")
    private String nome;

    public GrupoInput() {}

    public GrupoInput(String nome) {
        this.nome = nome;
    }

    public Grupo toEntity() {
        Grupo grupo = new Grupo();
        grupo.setNome(this.getNome());
        return grupo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
