package lh.juicecompany.Exceptions;

public class ExceptionNotCaughtException extends RuntimeException {
    public ExceptionNotCaughtException(String message) {
        super(message);
    }

    public ExceptionNotCaughtException(String message, Throwable cause) {
        super(message, cause);
    }
}
