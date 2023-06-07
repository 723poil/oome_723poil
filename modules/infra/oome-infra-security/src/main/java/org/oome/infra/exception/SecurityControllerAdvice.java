package org.oome.infra.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

@RestControllerAdvice
public class SecurityControllerAdvice {

    @ExceptionHandler({Exception.class, NoHandlerFoundException.class})
    public ResponseEntity<String> handleException(Exception ex) {
        String errorMsg = ex.getMessage();

        Throwable cause = ex.getCause();
        if (ex instanceof AuthenticationJwtExpiredException) {
            return new ResponseEntity<>(errorMsg, HttpStatus.NOT_ACCEPTABLE);
        }
        return new ResponseEntity<>(errorMsg, HttpStatus.BAD_REQUEST);
    }
}
