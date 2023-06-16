package org.oome.core.api.http;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;

public class OomeResponse<T> extends ResponseEntity<T> {

    private String errorCode;

    public OomeResponse(T body, String errorCode, HttpStatus status) {
        super(body, status);
        this.errorCode = errorCode;
    }
    public OomeResponse(HttpStatus status) {
        super(status);
    }

    public OomeResponse(T body, HttpStatus status) {
        super(body, status);
    }

    public OomeResponse(MultiValueMap<String, String> headers, HttpStatus status) {
        super(headers, status);
    }

    public OomeResponse(T body, MultiValueMap<String, String> headers, HttpStatus status) {
        super(body, headers, status);
    }

    public OomeResponse(T body, MultiValueMap<String, String> headers, int rawStatus) {
        super(body, headers, rawStatus);
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public static <T> OomeResponse<T> ok(T body) {
        return new OomeResponse<>(body, HttpStatus.OK);
    }

    public static <T> OomeResponse<T> ok(T body, HttpStatus status) {
        return new OomeResponse<>(body, status);
    }


    public static <T> OomeResponse<T> error(T body, String errorCode, HttpStatus status) {
        return new OomeResponse<>(body, errorCode, status);
    }
}
