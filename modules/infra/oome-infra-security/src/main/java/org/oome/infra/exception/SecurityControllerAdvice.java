package org.oome.infra.exception;

import org.oome.core.api.http.OomeResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

@RestControllerAdvice
public class SecurityControllerAdvice {

    @ExceptionHandler({NoHandlerFoundException.class})
    public OomeResponse<String> handleException(Exception ex) {
        String errorMsg = ex.getMessage();

        Throwable cause = ex.getCause();
        if (ex instanceof AuthenticationJwtExpiredException) {
            return new OomeResponse<>(errorMsg, HttpStatus.NOT_ACCEPTABLE);
        }
        return new OomeResponse<>(errorMsg, HttpStatus.BAD_REQUEST);
    }
}
