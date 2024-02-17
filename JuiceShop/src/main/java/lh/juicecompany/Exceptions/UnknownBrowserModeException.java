package lh.juicecompany.Exceptions;

public class UnknownBrowserModeException extends RuntimeException {

    public UnknownBrowserModeException(String message) {
        super(message);
    }

    public UnknownBrowserModeException(String message, Throwable cause) {
        super(message, cause);
    }
}
