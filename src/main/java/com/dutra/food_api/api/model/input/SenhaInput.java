package com.dutra.food_api.api.model.input;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class SenhaInput {

    @NotBlank(message = "Campo obrigatório")
    @Size(min = 8, max = 80, message = "Senha atual deve ter entre 8 e 80 caracteres")
    private String senhaAtual;

    @NotBlank(message = "Campo obrigatório")
    @Size(min = 8, max = 80, message = "Nova senha deve ter entre 8 e 80 caracteres")
    private String novaSenha;

    public SenhaInput() {}

    public SenhaInput(String senhaAtual, String novaSenha) {
        this.senhaAtual = senhaAtual;
        this.novaSenha = novaSenha;
    }

    public String getSenhaAtual() {
        return senhaAtual;
    }

    public void setSenhaAtual(String senhaAtual) {
        this.senhaAtual = senhaAtual;
    }

    public String getNovaSenha() {
        return novaSenha;
    }

    public void setNovaSenha(String novaSenha) {
        this.novaSenha = novaSenha;
    }
}
