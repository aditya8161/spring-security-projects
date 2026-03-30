package com.demo.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.context.request.WebRequest;

import javax.naming.AuthenticationException;
import java.time.LocalDateTime;

@RestControllerAdvice
@Slf4j
public class GlobleExceptionHandler
{

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<ErrorData> handleBadCredentialsException(BadCredentialsException ex,WebRequest request){
        String path = request.getDescription(false).replace("uri=", "");
        ErrorData errorData = ErrorData.builder()
                .message(ex.getMessage())
                .path(path)
                .errorDateTime(LocalDateTime.now())
                .statusCode(HttpStatus.BAD_REQUEST)
                .build();

        log.error("Error Occurred : {}{}",ex.getMessage(),errorData);
        return new ResponseEntity<>(errorData, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ErrorData> handleUserNotFoundException(UserNotFoundException ex, WebRequest request)
    {
        String path = request.getDescription(false).replace("uri=", "");
        ErrorData errorData = ErrorData.builder()
                .message(ex.getMessage())
                .path(path)
                .errorDateTime(LocalDateTime.now())
                .statusCode(HttpStatus.NOT_FOUND)
                .build();

        log.error("Error Occurred : {}{}",ex.getMessage(),errorData);
        return new ResponseEntity<>(errorData, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ErrorData> handleRuntimeException(RuntimeException ex, WebRequest request)
    {
        String path = request.getDescription(false).replace("uri=", "");
        ErrorData errorData = ErrorData.builder()
                .message(ex.getMessage())
                .path(path)
                .errorDateTime(LocalDateTime.now())
                .statusCode(HttpStatus.INTERNAL_SERVER_ERROR)
                .build();

        log.error("Error Occurred : {}{}",ex.getMessage(),errorData);
        return new ResponseEntity<>(errorData, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
