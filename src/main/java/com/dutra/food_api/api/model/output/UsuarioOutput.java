package com.dutra.food_api.api.model.output;

import com.dutra.food_api.domain.models.Usuario;

import java.util.Objects;

public class UsuarioOutput {

    private Long id;
    private String nome;
    private String email;

    public UsuarioOutput() {}

    public UsuarioOutput(Long id, String nome, String email) {
        this.id = id;
        this.nome = nome;
        this.email = email;
    }

    public static UsuarioOutput toUsuarioOutput(Usuario  usuario) {
        return new UsuarioOutput(usuario.getId(), usuario.getNome(), usuario.getEmail());
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        UsuarioOutput that = (UsuarioOutput) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
