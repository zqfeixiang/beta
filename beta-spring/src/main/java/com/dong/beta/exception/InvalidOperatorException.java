package com.dong.beta.exception;

/**
 * @author dzq
 * @since 11/23/22 11:43
 */
public class InvalidOperatorException extends RuntimeException {
    private static final long serialVersionUID = 5398213408843386021L;

    public InvalidOperatorException() {
    }

    public InvalidOperatorException(String message) {
        super(message);
    }

    public InvalidOperatorException(String message, Throwable throwable) {
        super(message, throwable);
    }

    public InvalidOperatorException(Throwable throwable) {
        super(throwable);
    }
}
