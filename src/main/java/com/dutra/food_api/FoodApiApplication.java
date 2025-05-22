package com.dutra.food_api;

import com.dutra.food_api.domain.repositories.custom.CustomRepositoryImpl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


@SpringBootApplication
@EnableJpaRepositories(repositoryBaseClass = CustomRepositoryImpl.class) //Especialização do SimpleJpa
public class FoodApiApplication {

	public static void main(String[] args) {

		//Seta o timeZone 'Z' em UTC para a aplicação
		//Substituído pela config
		//TimeZone.setDefault(TimeZone.getTimeZone("UTC"));


		SpringApplication.run(FoodApiApplication.class, args);
	}

}
