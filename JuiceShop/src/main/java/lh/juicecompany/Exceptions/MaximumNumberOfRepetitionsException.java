package lh.juicecompany.Exceptions;

public class MaximumNumberOfRepetitionsException extends RuntimeException {

    public MaximumNumberOfRepetitionsException(String message) {
        super(message);
    }

    public MaximumNumberOfRepetitionsException(String message, Throwable cause) {
        super(message, cause);
    }
}
