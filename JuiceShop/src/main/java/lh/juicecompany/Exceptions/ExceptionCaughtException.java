package lh.juicecompany.Exceptions;

public class ExceptionCaughtException extends RuntimeException {
    public ExceptionCaughtException(String message) {
        super(message);
    }

    public ExceptionCaughtException(String message, Throwable cause) {
        super(message, cause);
    }
}
