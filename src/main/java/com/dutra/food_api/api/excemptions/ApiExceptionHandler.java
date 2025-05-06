package com.dutra.food_api.api.excemptions;

import com.dutra.food_api.domain.services.exceptions.EntidadeEmUsoException;
import com.dutra.food_api.domain.services.exceptions.EntidadeNaoEncontradaException;
import com.dutra.food_api.domain.services.exceptions.PatchMergeFieldsException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.net.URI;

@ControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler({EntidadeNaoEncontradaException.class})
    public ProblemDetail handleEntidadeNaoEncontradaException(EntidadeNaoEncontradaException e) {

        ProblemDetail problemDetail = ProblemDetail.forStatus(HttpStatus.NOT_FOUND);
        problemDetail.setTitle(e.getMessage());
        problemDetail.setType(URI.create("/errors/not-found"));
        return problemDetail;
    }

    @ExceptionHandler(EntidadeEmUsoException.class)
    public ProblemDetail handleEntidadeEmUsoException(EntidadeEmUsoException e) {

        ProblemDetail problemDetail = ProblemDetail.forStatus(HttpStatus.CONFLICT);
        problemDetail.setTitle("Recurso em uso");
        problemDetail.setType(URI.create("/errors/conflict"));
        return problemDetail;
    }

    @ExceptionHandler(PatchMergeFieldsException.class)
    public ProblemDetail handlePatchMergeFieldsException(PatchMergeFieldsException e) {

        ProblemDetail problemDetail = ProblemDetail.forStatus(HttpStatus.UNPROCESSABLE_ENTITY);

        problemDetail.setType(URI.create("/errors/patch-error-update"));
        problemDetail.setTitle("e.getMessage()");
        problemDetail.setDetail("set-detail");
        problemDetail.setInstance(URI.create("/errors/patch-error-update"));

        return problemDetail;
    }


/**
 *     @ExceptionHandler(MethodArgumentNotValidException.class)
 *     public ResponseEntity<ValidationError> handleMethodArgumentNotValid(MethodArgumentNotValidException exception, HttpServletRequest request) {
 *         HttpStatus status = HttpStatus.UNPROCESSABLE_ENTITY;
 *
 *         ValidationError error = new ValidationError(Instant.now(), status.value(), "Validation exception.", exception.getMessage(), request.getRequestURI());
 *
 *         for (FieldError err : exception.getBindingResult().getFieldErrors()) {
 *             error.addError(err.getField(), err.getDefaultMessage());
 *         }
 *
 *         return ResponseEntity.status(status).body(error);
 *     }
 */
}
