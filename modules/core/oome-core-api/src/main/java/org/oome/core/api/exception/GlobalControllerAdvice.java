package org.oome.core.api.exception;

import org.oome.core.api.exception.runtime.FileOomeRuntimeException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalControllerAdvice {

    @ExceptionHandler(FileOomeRuntimeException.class)
    public ResponseEntity<?> handleFileOomeRuntimeException(FileOomeRuntimeException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("파일 처리중 오류가 발생했습니다.");
    }
}
