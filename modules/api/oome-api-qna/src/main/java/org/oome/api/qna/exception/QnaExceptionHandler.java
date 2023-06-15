package org.oome.api.qna.exception;

import lombok.extern.slf4j.Slf4j;
import org.oome.core.api.http.OomeResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;


@Slf4j
@ControllerAdvice("org.oome.api.qna")
public class QnaExceptionHandler {

    @ExceptionHandler(NullPointerException.class)
    public OomeResponse<?> handleNullException(Exception e){
        log.debug("Bad request exception occurred: {}", e.getMessage(), e);
        return new OomeResponse<>(e, HttpStatus.BAD_REQUEST);
    }
}
