package com.dutra.food_api.api.excemptions;

import com.dutra.food_api.domain.services.exceptions.*;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.*;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.NoHandlerFoundException;

import java.net.URI;
import java.time.OffsetDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
        problemDetail.setDetail(e.getMessage());

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

    //Handler para path variable de tipo errado, inclusive 'specification'
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    private ResponseEntity<ProblemDetail> handleMethodArgumentNotValid(MethodArgumentTypeMismatchException exception,
                                                                       HttpServletRequest request) {

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
    private ResponseEntity<ProblemDetail> handleNoHandlerFoundException(NoHandlerFoundException exception,
                                                                        HttpServletRequest request) {

        ProblemDetail problemDetail = ProblemDetail.forStatus(HttpStatus.BAD_REQUEST);
        problemDetail.setType(URI.create("/errors/uri-inexistente"));
        problemDetail.setTitle(exception.getMessage());
        problemDetail.setDetail(exception.getCause() != null ? exception.getCause().toString() : "O endereçamento de URi não existe para o caminho informado.");
        problemDetail.setInstance(URI.create(request.getRequestURI()));

        problemDetail.setProperty(TIME_STAMP, OffsetDateTime.now());

        return ResponseEntity.of(problemDetail).build();
    }

    //Handler para Jackson/JSON
    @ExceptionHandler(HttpMessageNotReadableException.class)
    private ResponseEntity<ProblemDetail> handleJsonParseException(HttpMessageNotReadableException  exception,
                                                                        HttpServletRequest request) {

        ProblemDetail problemDetail = ProblemDetail.forStatus(HttpStatus.BAD_REQUEST);
        problemDetail.setType(URI.create("/errors/json-parse-exception"));
        problemDetail.setTitle(exception.getMessage());
        problemDetail.setDetail("Erro na formatação do JSON: " + exception.getCause());
        problemDetail.setInstance(URI.create(request.getRequestURI()));

        return ResponseEntity.of(problemDetail).build();
    }

    //Handler para campos BeanValidation
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ProblemDetail> handleMethodArgumentNotValidException(MethodArgumentNotValidException exception,
                                                                               HttpServletRequest request) {
        HttpStatus status = HttpStatus.UNPROCESSABLE_ENTITY;

        ProblemDetail problemDetail = ProblemDetail.forStatus(status);
        problemDetail.setType(URI.create("/errors/validation-error"));
        problemDetail.setTitle("Erro de validação");
        problemDetail.setDetail("Um ou mais campos possuem valores inválidos.");
        problemDetail.setInstance(URI.create(request.getRequestURI()));
        problemDetail.setProperty("timestamp", OffsetDateTime.now());

        List<Map<String, String>> fieldErrors = exception.getBindingResult().getFieldErrors().stream()
                .map(error -> Map.of(
                        "campo", error.getField(),
                        "mensagem", error.getDefaultMessage()))
                .toList();

        problemDetail.setProperty("fieldErrors", fieldErrors);

        return ResponseEntity.status(status).body(problemDetail);
    }

    //Handler para validação de senha incorreta
    @ExceptionHandler(ValidationException.class)
    private ResponseEntity<ProblemDetail> handleValidationException(ValidationException  exception,
                                                                    HttpServletRequest request) {

        ProblemDetail problemDetail = ProblemDetail.forStatus(HttpStatus.BAD_REQUEST);
        problemDetail.setType(URI.create("/errors/incorrect-password"));
        problemDetail.setTitle(exception.getMessage());
        problemDetail.setDetail("Senha inválida: " + exception.getCause());
        problemDetail.setInstance(URI.create(request.getRequestURI()));

        return ResponseEntity.of(problemDetail).build();
    }

    //Handler para validação de usuário já cadastrado
    @ExceptionHandler(UsuarioExistenteException.class)
    private ResponseEntity<ProblemDetail> handleUsuarioExistenteException(UsuarioExistenteException  exception,
                                                                    HttpServletRequest request) {

        ProblemDetail problemDetail = ProblemDetail.forStatus(HttpStatus.BAD_REQUEST);
        problemDetail.setType(URI.create("/errors/user-already-exists"));
        problemDetail.setTitle(exception.getMessage());
        problemDetail.setDetail("Já existe um usuário cadastrado com este e-mail: " + exception.getCause());
        problemDetail.setInstance(URI.create(request.getRequestURI()));

        problemDetail.setProperty("StackTrace:", exception.getStackTrace());

        return ResponseEntity.of(problemDetail).build();
    }

    //Handler para validação de regra de negócio
    @ExceptionHandler(NegotioException.class)
    private ResponseEntity<ProblemDetail> handleNegotioException(NegotioException  exception,
                                                                          HttpServletRequest request) {

        ProblemDetail problemDetail = ProblemDetail.forStatus(HttpStatus.BAD_REQUEST);
        problemDetail.setType(URI.create("/errors/negotiate-rule-errors"));
        problemDetail.setTitle(exception.getOption() != null ? "Erro de negociação: " + exception.getOption() : "Erro de negociação");
        problemDetail.setDetail(exception.getMessage());
        problemDetail.setInstance(URI.create(request.getRequestURI()));

        return ResponseEntity.of(problemDetail).build();
    }

    //Handler geral
    @ExceptionHandler(Exception.class)
    private ResponseEntity<ProblemDetail> handleException(Exception exception,
                                                          HttpServletRequest request) {

        ProblemDetail problemDetail = ProblemDetail.forStatus(HttpStatus.BAD_REQUEST);
        problemDetail.setType(URI.create("/errors/general-error"));
        problemDetail.setTitle(exception.getMessage());

        problemDetail.setDetail(exception.getCause() != null ? exception
                .getCause().toString() : "Ocorreu um erro interno inesperado no sistema. Tente novamente, e, se o problema persistir, entre em contato com o administrador do sistema.");
        problemDetail.setProperty(TIME_STAMP, OffsetDateTime.now());
        problemDetail.setInstance(URI.create(request.getRequestURI()));

        problemDetail.setProperty("StackTrace:", exception.getStackTrace());

        return ResponseEntity.of(problemDetail).build();
    }
}
