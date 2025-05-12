package com.dutra.food_api.api.model.input;

import com.dutra.food_api.domain.models.Endereco;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;


public class EnderecoInput {

    @NotBlank(message = "Campo requerido")
    private String cep;

    @NotBlank(message = "Campo requerido")
    private String logradouro;

    @NotBlank(message = "Campo requerido. Não havendo número use 'S/N'.")
    private String numero;

    private String complemento;

    @NotBlank(message = "Campo requerido")
    private String bairro;

    @NotNull(message = "Campo requerido")
    @Positive(message = "Deve ser informado um ID positivo para Cidade")
    private Long cidadeId;

    @NotNull(message = "Campo requerido")
    @Positive(message = "Deve ser informado um ID positivo para Estado")
    private Long estadoId;

    public EnderecoInput() {

    }

    public EnderecoInput(String cep, String logradouro,
                         String numero, String complemento,
                         String bairro, Long cidadeId, Long estadoId) {
        this.cep = cep;
        this.logradouro = logradouro;
        this.numero = numero;
        this.complemento = complemento;
        this.bairro = bairro;
        this.cidadeId = cidadeId;
        this.estadoId = estadoId;
    }

    public Endereco toEntity() {
        Endereco  endereco = new Endereco();
        endereco.setCep(cep);
        endereco.setLogradouro(logradouro);
        endereco.setNumero(numero);
        endereco.setComplemento(complemento);
        endereco.setBairro(bairro);
        return endereco;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getLogradouro() {
        return logradouro;
    }

    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public Long getCidadeId() {
        return cidadeId;
    }

    public void setCidadeId(Long cidadeId) {
        this.cidadeId = cidadeId;
    }

    public Long getEstadoId() {
        return estadoId;
    }

    public void setEstadoId(Long estadoId) {
        this.estadoId = estadoId;
    }
}
