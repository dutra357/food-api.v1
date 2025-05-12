package com.dutra.food_api;

import com.dutra.food_api.domain.repositories.custom.CustomRepositoryImpl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.util.TimeZone;

@SpringBootApplication
@EnableJpaRepositories(repositoryBaseClass = CustomRepositoryImpl.class) //Especialização do SimpleJpa
public class FoodApiApplication {

	public static void main(String[] args) {

		TimeZone.setDefault(TimeZone.getTimeZone("UTC")); //Seta o timeZone 'Z' em UTZ para a aplicação


		SpringApplication.run(FoodApiApplication.class, args);
	}

}
