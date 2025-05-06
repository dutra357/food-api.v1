package com.dutra.food_api.api.excemptions;

import com.dutra.food_api.domain.services.exceptions.EntidadeEmUsoException;
import com.dutra.food_api.domain.services.exceptions.EntidadeNaoEncontradaException;
import com.dutra.food_api.domain.services.exceptions.PatchMergeFieldsException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.NoHandlerFoundException;

import java.net.URI;
import java.time.OffsetDateTime;

@ControllerAdvice
public class ApiExceptionHandler {

    private static final String TIME_STAMP = "TimeStamp:";

    //Handler para entidade não encontrada
    @ExceptionHandler({EntidadeNaoEncontradaException.class})
    private ProblemDetail handleEntidadeNaoEncontradaException(EntidadeNaoEncontradaException e) {

        ProblemDetail problemDetail = ProblemDetail.forStatus(HttpStatus.NOT_FOUND);
        problemDetail.setTitle(e.getMessage());
        problemDetail.setType(URI.create("/errors/not-found"));

        problemDetail.setProperty(TIME_STAMP, OffsetDateTime.now());

        return problemDetail;
    }

    //Handler para exclusão de entidade vinculada
    @ExceptionHandler(EntidadeEmUsoException.class)
    private ProblemDetail handleEntidadeEmUsoException(EntidadeEmUsoException e) {

        ProblemDetail problemDetail = ProblemDetail.forStatus(HttpStatus.CONFLICT);
        problemDetail.setTitle("Recurso em uso");
        problemDetail.setType(URI.create("/errors/conflict"));
        problemDetail.setProperty(TIME_STAMP, OffsetDateTime.now());

        return problemDetail;
    }

    //Handler para exception com PUT incongruente
    @ExceptionHandler(PatchMergeFieldsException.class)
    private ProblemDetail handlePatchMergeFieldsException(PatchMergeFieldsException e) {

        ProblemDetail problemDetail = ProblemDetail.forStatus(HttpStatus.UNPROCESSABLE_ENTITY);

        problemDetail.setType(URI.create("/errors/patch-error-update"));
        problemDetail.setTitle("e.getMessage()");
        problemDetail.setDetail("set-detail");
        problemDetail.setInstance(URI.create("/errors/patch-error-update"));

        problemDetail.setProperty(TIME_STAMP, OffsetDateTime.now());

        return problemDetail;
    }

    //Handler para path variable de tipo errado
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    private ResponseEntity<ProblemDetail> handleMethodArgumentNotValid(MethodArgumentTypeMismatchException exception, HttpServletRequest request) {

        ProblemDetail problemDetail = ProblemDetail.forStatus(HttpStatus.BAD_REQUEST);
        problemDetail.setType(URI.create("/errors/parametro-invalido"));
        problemDetail.setTitle(exception.getMessage());
        problemDetail.setDetail(exception.getCause() != null ? exception.getCause().toString() : "Tipo do argumento inválido");
        problemDetail.setInstance(URI.create(request.getRequestURI()));

        problemDetail.setProperty(TIME_STAMP, OffsetDateTime.now());

        return ResponseEntity.of(problemDetail).build();
    }

    //Handler para endpoint inexistente
    @ExceptionHandler(NoHandlerFoundException.class)
    private ResponseEntity<ProblemDetail> handleNoHandlerFoundException(NoHandlerFoundException exception, HttpServletRequest request) {

        ProblemDetail problemDetail = ProblemDetail.forStatus(HttpStatus.BAD_REQUEST);
        problemDetail.setType(URI.create("/errors/uri-inexistente"));
        problemDetail.setTitle(exception.getMessage());
        problemDetail.setDetail(exception.getCause() != null ? exception.getCause().toString() : "O endereçamento de URi não existe para o caminho informado.");
        problemDetail.setInstance(URI.create(request.getRequestURI()));

        problemDetail.setProperty(TIME_STAMP, OffsetDateTime.now());

        return ResponseEntity.of(problemDetail).build();
    }

    //Handler geral
    @ExceptionHandler(Exception.class)
    private ResponseEntity<ProblemDetail> handleException(Exception exception, HttpServletRequest request) {

        ProblemDetail problemDetail = ProblemDetail.forStatus(HttpStatus.BAD_REQUEST);
        problemDetail.setType(URI.create("/errors/general-error"));
        problemDetail.setTitle(exception.getMessage());

        problemDetail.setDetail(exception.getCause() != null ? exception
                .getCause().toString() : "Ocorreu um erro interno inesperado no sistema. Tente novamente, e, se o problema persistir, entre em contato com o administrador do sistema.");

        problemDetail.setInstance(URI.create(request.getRequestURI()));

        problemDetail.setProperty("StackTrace:", exception.getStackTrace());
        problemDetail.setProperty(TIME_STAMP, OffsetDateTime.now());

        return ResponseEntity.of(problemDetail).build();
    }


}
