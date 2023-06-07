package org.oome.infra.exception;

import org.oome.core.api.exception.explicit.BaseOomeException;

public class AuthenticationJwtExpiredException extends BaseOomeException {
    public AuthenticationJwtExpiredException(String msg, Throwable cause) {
        super(msg, cause);
    }

    public AuthenticationJwtExpiredException(String msg) {
        super(msg);
    }
}
