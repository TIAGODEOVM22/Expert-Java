package br.com.tiago.user_service_api.controller.exception;

import jakarta.servlet.http.HttpServletRequest;
import models.exceptions.ResourceNotFoundException;
import models.exceptions.StandardError;
import models.exceptions.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.ArrayList;

import static java.time.LocalDateTime.now;
import static org.springframework.http.HttpStatus.*;

@ControllerAdvice
public class ControllerExceptionHandler {


    /*Tratamento personalizado para Recurso não encontrado*/
    @ExceptionHandler(ResourceNotFoundException.class)
    ResponseEntity<models.exceptions.StandardError> handleNotFoundException(
            final ResourceNotFoundException ex, final HttpServletRequest request)
    {
        return ResponseEntity.status(NOT_FOUND).body(
                StandardError.builder()
                        .timeStamp(now())
                        .status(NOT_FOUND.value())
                        .error(NOT_FOUND.getReasonPhrase())
                        .message(ex.getMessage())
                        .path(request.getRequestURI())
                        .build()
        );
    }

/*Tratamento personalizado para save user*/
    @ExceptionHandler(MethodArgumentNotValidException.class)
    ResponseEntity<StandardError> handleMethodArgumentNotValidException(
            final MethodArgumentNotValidException ex, final HttpServletRequest request)
    {
            var errors = ValidationException.builder()
                    .timeStamp(now())
                    .status(BAD_REQUEST.value())
                    .error("Validation Exception")
                    .message("Exception validation in attributes")
                    .path(request.getRequestURI())
                    .errors(new ArrayList<>())
                    .build();
            for(FieldError fieldError : ex.getBindingResult().getFieldErrors()){
                errors.addError(fieldError.getField(), fieldError.getDefaultMessage());

            }
            return ResponseEntity.badRequest().body(errors);
    }

/*Tratamento personalizado para validação de email*/
    @ExceptionHandler(DataIntegrityViolationException.class)
    ResponseEntity<StandardError> handleDataIntegrityViolationException(
            final DataIntegrityViolationException ex, final HttpServletRequest request)
    {
        return ResponseEntity.status(CONFLICT).body(
                models.exceptions.StandardError.builder()
                        .timeStamp(now())
                        .status(CONFLICT.value())
                        .error(CONFLICT.getReasonPhrase())
                        .message(ex.getMessage())
                        .path(request.getRequestURI())
                        .build()
        );
    }
}
