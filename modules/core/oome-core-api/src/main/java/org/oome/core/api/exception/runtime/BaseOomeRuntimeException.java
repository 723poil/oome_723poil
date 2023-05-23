package org.oome.core.api.exception.runtime;

public class BaseOomeRuntimeException extends RuntimeException {

    /**
     * Constructs a BaseOomeRuntimeException with no detail message.
     */
    public BaseOomeRuntimeException() {
        super();
    }

    /**
     * Constructs a BaseOomeRuntimeException with the specified detail
     * message.
     * A detail message is a String that describes this particular
     * exception.
     *
     * @param message the detail message.
     */
    public BaseOomeRuntimeException(String message) {
        super(message);
    }

    /**
     * Creates a {@code BaseOomeRuntimeException} with the specified
     * detail message and cause.
     *
     * @param message the detail message (which is saved for later retrieval
     *        by the {@link #getMessage()} method).
     * @param cause the cause (which is saved for later retrieval by the
     *        {@link #getCause()} method).  (A {@code null} value is permitted,
     *        and indicates that the cause is nonexistent or unknown.)
     */
    public BaseOomeRuntimeException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * Creates a {@code BaseOomeException} with the specified cause
     * and a detail message of {@code (cause==null ? null : cause.toString())}
     * (which typically contains the class and detail message of
     * {@code cause}).
     *
     * @param cause the cause (which is saved for later retrieval by the
     *        {@link #getCause()} method).  (A {@code null} value is permitted,
     *        and indicates that the cause is nonexistent or unknown.)
     */
    public BaseOomeRuntimeException(Throwable cause) {
        super(cause);
    }
}
