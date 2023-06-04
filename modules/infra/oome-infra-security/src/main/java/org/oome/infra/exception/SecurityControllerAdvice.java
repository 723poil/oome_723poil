package org.oome.infra.exception;

import io.jsonwebtoken.ExpiredJwtException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class SecurityControllerAdvice {

    @ExceptionHandler(ExpiredJwtException.class)
    public ResponseEntity<String> handleException(Exception ex) {
        String errorMsg = ex.getMessage();

        return new ResponseEntity<>(errorMsg, HttpStatus.NOT_ACCEPTABLE);
    }
}
