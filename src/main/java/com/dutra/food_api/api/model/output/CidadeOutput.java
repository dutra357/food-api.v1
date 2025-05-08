package com.dutra.food_api.api.model.output;

import com.dutra.food_api.domain.models.Cidade;

import java.util.Objects;

public class CidadeOutput {

    private Long id;
    private String nome;
    private EstadoOutput estado;

    public CidadeOutput() {

    }

    public CidadeOutput(Long id, String nome, EstadoOutput estado) {
        this.id = id;
        this.nome = nome;
        this.estado = estado;
    }

    public static CidadeOutput toCidadeOutput(Cidade cidade) {
        return new CidadeOutput(cidade.getId(), cidade.getNome(),
                EstadoOutput.toEstadoOutput(cidade.getEstado()));
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

    public EstadoOutput getEstado() {
        return estado;
    }

    public void setEstado(EstadoOutput estado) {
        this.estado = estado;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        CidadeOutput that = (CidadeOutput) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
