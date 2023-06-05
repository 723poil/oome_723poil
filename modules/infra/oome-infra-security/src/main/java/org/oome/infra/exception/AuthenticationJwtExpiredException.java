package org.oome.infra.exception;

import org.springframework.security.core.AuthenticationException;

public class AuthenticationJwtExpiredException extends AuthenticationException {
    public AuthenticationJwtExpiredException(String msg, Throwable cause) {
        super(msg, cause);
    }

    public AuthenticationJwtExpiredException(String msg) {
        super(msg);
    }
}
