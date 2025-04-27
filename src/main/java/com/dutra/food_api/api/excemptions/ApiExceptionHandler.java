package com.dutra.food_api.api.excemptions;

import com.dutra.food_api.domain.services.exceptions.EntidadeEmUsoException;
import com.dutra.food_api.domain.services.exceptions.EntidadeNaoEncontradaException;
import com.dutra.food_api.domain.services.exceptions.PatchMergeFieldsException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.net.URI;

@ControllerAdvice
public class ApiExceptionHandler {

    @ExceptionHandler({EntidadeNaoEncontradaException.class})
    public ProblemDetail handleException(EntidadeNaoEncontradaException e) {
        ProblemDetail problemDetail = ProblemDetail.forStatus(HttpStatus.NOT_FOUND);
        problemDetail.setTitle(e.getMessage());
        problemDetail.setType(URI.create("/errors/not-found"));
        return problemDetail;
    }

    @ExceptionHandler(EntidadeEmUsoException.class)
    public ProblemDetail sensorMonitoringException(EntidadeEmUsoException e) {
        ProblemDetail problemDetail = ProblemDetail.forStatus(HttpStatus.CONFLICT);
        problemDetail.setTitle(e.getMessage());
        problemDetail.setType(URI.create("/errors/conflict"));
        return problemDetail;
    }

    @ExceptionHandler(PatchMergeFieldsException.class)
    public ProblemDetail sensorMonitoringException(PatchMergeFieldsException e) {
        ProblemDetail problemDetail = ProblemDetail.forStatus(HttpStatus.UNPROCESSABLE_ENTITY);
        problemDetail.setTitle(e.getMessage());
        problemDetail.setType(URI.create("/errors/patch-error-update"));
        return problemDetail;
    }
}
