package com.dutra.food_api.domain.models.manager;

import com.dutra.food_api.FoodApiApplication;
import com.dutra.food_api.domain.models.Cozinha;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

import java.util.List;

public class CozinhaMain {
    public static void main(String[] args) {

        /**
         * Inicia e pára
         */
        ConfigurableApplicationContext applicationContext = new SpringApplicationBuilder(FoodApiApplication.class)
                .web(WebApplicationType.NONE)
                .run(args);

        ManagerJPA cadastroCozinha = applicationContext.getBean(ManagerJPA.class);
        List<Cozinha> cozinhas = cadastroCozinha.listarCozinhas();

        for (Cozinha cozinha : cozinhas) {
            System.out.println(cozinha);
        }
    }
}
