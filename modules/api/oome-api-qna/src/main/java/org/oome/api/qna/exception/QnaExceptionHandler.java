package org.oome.api.qna.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;


@Slf4j
@ControllerAdvice("org.oome.api.qna")
public class QnaExceptionHandler {

    @ExceptionHandler(NullPointerException.class)
    public ResponseEntity<?> handleNullException(Exception e){
        log.debug("Bad request exception occurred: {}", e.getMessage(), e);
        return new ResponseEntity<>(e, HttpStatus.BAD_REQUEST);
    }
}
