package com.prealkemy.disney.controller;

import com.prealkemy.disney.dto.APIErrorDTO;
import com.prealkemy.disney.exception.ParamNotFound;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(value = {ParamNotFound.class})
    protected ResponseEntity<Object> handleParamNotFound(RuntimeException ex, WebRequest request) {
        APIErrorDTO errorDTO = new APIErrorDTO(
                HttpStatus.BAD_REQUEST,
                ex.getMessage(),
                Arrays.asList("PARAM NOT FOUND"));
        return handleExceptionInternal(ex, errorDTO, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }

    /**
     * Handler for @Valid annotation
     */

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex,
            HttpHeaders headers,
            HttpStatus status,
            WebRequest request) {
        List<String> errors = new ArrayList<>();
        for (FieldError error: ex.getBindingResult().getFieldErrors()) {
            errors.add(error.getField() + ":" + error.getDefaultMessage());
        }
        for (ObjectError error: ex.getBindingResult().getGlobalErrors()) {
            errors.add(error.getObjectName() + ":" + error.getDefaultMessage());
        }
        APIErrorDTO apiError = new APIErrorDTO(HttpStatus.BAD_REQUEST, ex.getLocalizedMessage(), errors);
        return handleExceptionInternal(ex, apiError, headers, apiError.getStatus(), request);
    }

    @ExceptionHandler(value = {IllegalArgumentException.class, IllegalStateException.class})
    public ResponseEntity<Object> handleConflict(RuntimeException ex, WebRequest request) {
        String bodyOfResponse = "THIS SHOULD BE AN SPECIFIC APPLICATION";
        return handleExceptionInternal(ex, bodyOfResponse, new HttpHeaders(), HttpStatus.CONFLICT, request);
    }
}
