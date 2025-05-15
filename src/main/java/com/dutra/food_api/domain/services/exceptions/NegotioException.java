package com.dutra.food_api.domain.services.exceptions;

public class NegotioException extends RuntimeException {

    private Long entidadeId;
    private String option;

    public NegotioException(String message) {
        super(message);
    }

    public NegotioException(String message, Long entidadeId) {
        super(message);
        this.entidadeId = entidadeId;
    }

    public NegotioException(String message,String option, Long entidadeId) {
        super(message);
        this.entidadeId = entidadeId;
        this.option = option;
    }

    public Long getEntidadeId() {
        return entidadeId;
    }

    public void setEntidadeId(Long entidadeId) {
        this.entidadeId = entidadeId;
    }

    public String getOption() {
        return option;
    }

    public void setOption(String option) {
        this.option = option;
    }
}
