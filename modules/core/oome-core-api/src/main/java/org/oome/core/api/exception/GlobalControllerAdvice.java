package org.oome.core.api.exception;

import org.oome.core.api.exception.runtime.FileOomeRuntimeException;
import org.oome.core.api.http.OomeResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

;

@RestControllerAdvice
public class GlobalControllerAdvice {

    @ExceptionHandler(FileOomeRuntimeException.class)
    public OomeResponse<?> handleFileOomeRuntimeException(FileOomeRuntimeException e) {
        return new OomeResponse<>("파일 처리중 오류가 발생했습니다.", HttpStatus.BAD_REQUEST);
    }
}
