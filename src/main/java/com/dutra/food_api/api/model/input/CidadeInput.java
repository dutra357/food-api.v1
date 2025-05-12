package com.dutra.food_api.api.model.input;

import com.dutra.food_api.domain.models.Cidade;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;


public class CidadeInput {

    @NotNull(message = "Campo requerido")
    private String nome;

    @Positive(message = "Deve ser informado um ID positivo para Estado")
    @NotNull(message = "Campo requerido")
    private Long estadoId;

    public CidadeInput() {

    }

    public CidadeInput(Long estadoId, String nome) {
        this.estadoId = estadoId;
        this.nome = nome;
    }

    public Cidade toEntity() {
        Cidade cidade = new Cidade();
        cidade.setNome(nome);
        return cidade;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Long getEstadoId() {
        return estadoId;
    }

    public void setEstadoId(Long estadoId) {
        this.estadoId = estadoId;
    }
}
