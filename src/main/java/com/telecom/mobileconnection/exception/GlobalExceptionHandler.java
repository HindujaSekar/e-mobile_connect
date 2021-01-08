package com.telecom.mobileconnection.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> globalExceptionHandler(Exception exception) {

        return new ResponseEntity<>(
                ErrorResponse.builder().message(exception.getMessage()).statusCode(HttpStatus.NOT_FOUND.value()).build(),
                HttpStatus.NOT_FOUND);

    }

    @ExceptionHandler(InvalidCredentialsException.class)
    public ResponseEntity<ErrorResponse> invalidCredentialsExceptionHandler(InvalidCredentialsException ex) {
        ErrorResponse responseDto = ErrorResponse.builder().message(ex.getMessage())
                .statusCode(HttpStatus.UNAUTHORIZED.value()).build();
        return new ResponseEntity<>(responseDto, HttpStatus.UNAUTHORIZED);

    }


    @ExceptionHandler(DatabaseConnectionException.class)
    public ResponseEntity<ErrorResponse> databaseConnectionExceptionHandler(DatabaseConnectionException ex) {
        ErrorResponse responseDto = ErrorResponse.builder().message(ex.getMessage())
                .statusCode(HttpStatus.INTERNAL_SERVER_ERROR.value()).build();
        return new ResponseEntity<>(responseDto, HttpStatus.INTERNAL_SERVER_ERROR);

    }

}
