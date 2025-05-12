package com.dutra.food_api.api.model.output;


import com.dutra.food_api.domain.models.Endereco;

public class EnderecoOutput {

    private String cep;
    private String logradouro;
    private String numero;
    private String complemento;
    private String bairro;
    private CidadeOutput cidade;

    public EnderecoOutput() {}

    public EnderecoOutput(String cep, String logradouro, String numero,
                          String complemento, String bairro,
                          CidadeOutput cidade) {
        this.cep = cep;
        this.logradouro = logradouro;
        this.numero = numero;
        this.complemento = complemento;
        this.bairro = bairro;
        this.cidade = cidade;
    }

    public static EnderecoOutput toEnderecoOutput(Endereco endereco) {
        EnderecoOutput  enderecoOutput = new EnderecoOutput();
        enderecoOutput.setCep(endereco.getCep());
        enderecoOutput.setLogradouro(endereco.getLogradouro());
        enderecoOutput.setNumero(endereco.getNumero());
        enderecoOutput.setComplemento(endereco.getComplemento());
        enderecoOutput.setBairro(endereco.getBairro());

        enderecoOutput.setCidade(CidadeOutput.toCidadeOutput(endereco.getCidade()));
        return enderecoOutput;
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

    public CidadeOutput getCidade() {
        return cidade;
    }

    public void setCidade(CidadeOutput cidade) {
        this.cidade = cidade;
    }
}
