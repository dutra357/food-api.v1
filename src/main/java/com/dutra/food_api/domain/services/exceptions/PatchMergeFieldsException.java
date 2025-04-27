package com.dutra.food_api.domain.services.exceptions;

public class PatchMergeFieldsException extends RuntimeException{
    public PatchMergeFieldsException(String message) {
        super(message);
    }
}
