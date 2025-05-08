package com.dutra.food_api.api;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.hamcrest.Matchers;
import org.junit.Test;
import org.junit.jupiter.api.BeforeAll;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class CozinhaControllerIT {

    @BeforeAll
    void setUp() {
        RestAssured.baseURI = "http://localhost";
        RestAssured.port = 8080;
    }

    @Test
    public void deveRetornarStatus200QuandoConsultarCozinhas() {

        RestAssured.given()
                .accept(ContentType.JSON)
                .when()
                .get("/cozinhas")
                .then()
                .log().all()
                .statusCode(HttpStatus.OK.value())
                .body("size()", Matchers.greaterThan(0));
    }

    @Test
    public void deveRetornarStatus200QuandoConsultarUmaCozinhaComBody() {

        RestAssured.given()
                .accept(ContentType.JSON)
                .when()
                .get("/cozinhas/1")
                .then()
                .log().all()
                .statusCode(HttpStatus.OK.value())
                .body("nome", Matchers.equalTo("Tailandesa"));
    }

    @Test
    public void deveRetornarStatus200EUmaListaQuandoConsultarCozinhas() {

        RestAssured.given()
                .accept(ContentType.JSON)
                .when()
                .get("/cozinhas")
                .then()
                .log().all()
                .statusCode(HttpStatus.OK.value())
                .body("nome", Matchers.hasItems("Tailandesa", "Indiana", "Argentina"));
    }

    @Test
    public void deveRetornarStatus201QuandoCadastrarCozinha() {

        RestAssured.given()
                .body("{ \"nome\": \"Chinesa\" }")
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .when()
                .post("/cozinhas")
                .then()
                .log().all()
                .statusCode(HttpStatus.CREATED.value());
    }
}
