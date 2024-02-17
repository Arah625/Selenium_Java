package lh.juicecompany.Exceptions;

public class IllegalLocatorTypeException extends IllegalArgumentException {
    public IllegalLocatorTypeException(String message) {
        super(message);
    }

    public IllegalLocatorTypeException(String message, Throwable cause) {
        super(message, cause);
    }
}
