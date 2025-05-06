package com.dutra.food_api.api.model;

import com.dutra.food_api.domain.models.Endereco;
import jakarta.validation.constraints.*;

import java.math.BigDecimal;
import java.util.List;

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

    private Endereco endereco;

    private List<Long> produtosIds;
    private List<Long> formasPagamentoIds;


    public RestauranteInput() {
    }

    public RestauranteInput(String nome, BigDecimal taxaFrete,
                            Long cozinhaId, Endereco endereco) {
        this.nome = nome;
        this.taxaFrete = taxaFrete;
        this.cozinhaId = cozinhaId;
        this.endereco = endereco;
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

    public List<Long> getProdutosIds() {
        return produtosIds;
    }

    public List<Long> getFormasPagamentoIds() {
        return formasPagamentoIds;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }
}
