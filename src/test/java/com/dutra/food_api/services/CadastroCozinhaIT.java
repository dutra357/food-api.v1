package com.dutra.food_api.services;

import com.dutra.food_api.domain.models.Cozinha;
import com.dutra.food_api.domain.services.CadastroCozinhaService;
import com.dutra.food_api.domain.services.exceptions.EntidadeEmUsoException;
import com.dutra.food_api.domain.services.exceptions.EntidadeNaoEncontradaException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
class CadastroCozinhaIT {

    @Autowired
    private CadastroCozinhaService cadastroCozinhaService;

    @Test
    void whenCadastrarCozinhaComNomeThenDeveRetornarComId() {
        //cenário
        Cozinha cozinha = new Cozinha();
        cozinha.setNome("Japonesa");

        //ação
        Cozinha novaCozinha = cadastroCozinhaService.salvarCozinha(cozinha);

        //validação
        assertThat(novaCozinha).isNotNull();
        assertThat(novaCozinha.getId()).isNotNull();
    }

    @Test
    void whenExcluirCozinhaInexistenteThenDeveFalhar() {

        assertThrows(EntidadeNaoEncontradaException.class, () -> {
            cadastroCozinhaService.remover(100L);
        });
    }

    @Test
    void whenExcluirCozinhaEmUsoThenDeveFalhar() {

        assertThrows(EntidadeEmUsoException.class, () -> {
            cadastroCozinhaService.remover(4L);
        });
    }
}
