package com.dutra.food_api.domain.services.exceptions;


public class EntidadeNaoEncontradaException extends RuntimeException {

    public EntidadeNaoEncontradaException(String message) {
        super(message);
    }
}
