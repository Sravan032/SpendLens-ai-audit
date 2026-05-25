package com.sravan.spendlens.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse>
    handleValidationExceptions(MethodArgumentNotValidException ex) {

        List<String> errors = new ArrayList<>();

        ex.getBindingResult()
                .getFieldErrors()
                .forEach(error -> {

                    errors.add(
                            error.getDefaultMessage()
                    );
                });

        ErrorResponse response =
                new ErrorResponse(
                        "Validation Failed",
                        errors
                );

        return new ResponseEntity<>(
                response,
                HttpStatus.BAD_REQUEST
        );
    }
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse>
    handleGenericException(
            Exception ex
    ) {

        List<String> errors = new ArrayList<>();

        errors.add(ex.getMessage());

        ErrorResponse response =
                new ErrorResponse(
                        "Something went wrong",
                        errors
                );

        return new ResponseEntity<>(
                response,
                HttpStatus.INTERNAL_SERVER_ERROR
        );
    }

    @ExceptionHandler(
            ResourceNotFoundException.class
    )
    public ResponseEntity<ErrorResponse>
    handleResourceNotFound(
            ResourceNotFoundException ex
    ) {

        List<String> errors = new ArrayList<>();

        errors.add(ex.getMessage());

        ErrorResponse response =
                new ErrorResponse(
                        "Resource Not Found",
                        errors
                );

        return new ResponseEntity<>(
                response,
                HttpStatus.NOT_FOUND
        );
    }
}