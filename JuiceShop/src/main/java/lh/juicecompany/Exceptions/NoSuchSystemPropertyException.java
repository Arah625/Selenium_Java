package lh.juicecompany.Exceptions;

public class NoSuchSystemPropertyException extends IllegalArgumentException {

    public NoSuchSystemPropertyException(String message) {
        super(message);
    }

    public NoSuchSystemPropertyException(String message, Throwable cause) {
        super(message, cause);
    }
}
