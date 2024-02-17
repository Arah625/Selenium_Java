package lh.juicecompany.Exceptions;

public class UnknownBrowserTypeException extends RuntimeException {

    public UnknownBrowserTypeException(String message) {
        super(message);
    }

    public UnknownBrowserTypeException(String message, Throwable cause) {
        super(message, cause);
    }
}
