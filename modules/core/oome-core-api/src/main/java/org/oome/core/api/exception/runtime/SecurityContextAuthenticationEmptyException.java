package org.oome.core.api.exception.runtime;

public class SecurityContextAuthenticationEmptyException extends BaseOomeRuntimeException {
    public SecurityContextAuthenticationEmptyException() {
        super("인증되지 않은 사용자입니다.");
    }

    public SecurityContextAuthenticationEmptyException(String message) {
        super(message);
    }

    public SecurityContextAuthenticationEmptyException(String message, Throwable cause) {
        super(message, cause);
    }

    public SecurityContextAuthenticationEmptyException(Throwable cause) {
        super(cause);
    }
}
