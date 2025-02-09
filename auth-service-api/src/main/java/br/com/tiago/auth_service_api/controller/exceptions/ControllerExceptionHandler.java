package br.com.tiago.auth_service_api.controller.exceptions;

import jakarta.servlet.http.HttpServletRequest;
import models.exceptions.StandardError;
import models.exceptions.ValidationException;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.ArrayList;

import static java.time.LocalDateTime.now;
import static org.springframework.http.HttpStatus.*;

@ControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler(BadCredentialsException.class)
    ResponseEntity<StandardError> handleBadCredentialsException(
            final BadCredentialsException ex, final HttpServletRequest request)
    {
        return ResponseEntity.status(UNAUTHORIZED).body(
                ValidationException.builder()
                        .timeStamp(now())
                        .status(UNAUTHORIZED.value())
                        .error(UNAUTHORIZED.getReasonPhrase())
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
}

