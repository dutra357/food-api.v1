package com.dutra.food_api.api.model;

import com.dutra.food_api.domain.models.Cozinha;

public class CozinhaOutput {

    private Long id;
    private String nome;

    public CozinhaOutput() {

    }
    public CozinhaOutput(Long id, String nome) {
        this.id = id;
        this.nome = nome;
    }

    public static CozinhaOutput toCozinhaOutput(Cozinha cozinha) {
        CozinhaOutput  cozinhaOutput = new CozinhaOutput();
        cozinhaOutput.setId(cozinha.getId());
        cozinhaOutput.setNome(cozinha.getNome());
        return cozinhaOutput;
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
}
