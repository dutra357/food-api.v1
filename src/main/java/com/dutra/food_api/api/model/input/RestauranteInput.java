package com.dutra.food_api.api.model.input;

import com.dutra.food_api.domain.models.Restaurante;
import jakarta.validation.constraints.*;

import java.math.BigDecimal;


public class RestauranteInput {

    @NotBlank(message = "Campo requerido")
    @Size(min = 5, max = 80, message = "Nome deve ter entre 5 e 80 caracteres")
    private String nome;

    @NotNull(message = "Campo requerido")
    @DecimalMin(value = "0", message = "Deve ser informado um valor positivo ou zero para Taxa de Frete")
    private BigDecimal taxaFrete;

    @NotNull(message = "Campo requerido")
    @Positive(message = "Deve ser informado um ID positivo para Cozinha")
    private Long cozinhaId;

    private EnderecoInput endereco;

    public RestauranteInput() {
    }

    public RestauranteInput(String nome, BigDecimal taxaFrete,
                            Long cozinhaId, EnderecoInput endereco) {
        this.nome = nome;
        this.taxaFrete = taxaFrete;
        this.cozinhaId = cozinhaId;
        this.endereco = endereco;
    }

    public Restaurante toEntity() {
        Restaurante  restaurante = new Restaurante();
        restaurante.setNome(nome);
        restaurante.setTaxaFrete(taxaFrete);
        restaurante.setEndereco(endereco.toEntity());
        return restaurante;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public BigDecimal getTaxaFrete() {
        return taxaFrete;
    }

    public void setTaxaFrete(BigDecimal taxaFrete) {
        this.taxaFrete = taxaFrete;
    }

    public Long getCozinhaId() {
        return cozinhaId;
    }

    public void setCozinhaId(Long cozinhaId) {
        this.cozinhaId = cozinhaId;
    }

    public EnderecoInput getEndereco() {
        return endereco;
    }

    public void setEndereco(EnderecoInput endereco) {
        this.endereco = endereco;
    }
}
