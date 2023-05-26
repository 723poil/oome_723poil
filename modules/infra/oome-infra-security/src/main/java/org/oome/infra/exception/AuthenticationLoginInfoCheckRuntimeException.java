package org.oome.infra.exception;

import org.oome.core.api.exception.runtime.BaseOomeRuntimeException;

public class AuthenticationLoginInfoCheckRuntimeException extends BaseOomeRuntimeException {
    public AuthenticationLoginInfoCheckRuntimeException() {
        super();
    }

    public AuthenticationLoginInfoCheckRuntimeException(String message) {
        super(message);
    }

    public AuthenticationLoginInfoCheckRuntimeException(String message, Throwable cause) {
        super(message, cause);
    }

    public AuthenticationLoginInfoCheckRuntimeException(Throwable cause) {
        super(cause);
    }
}
