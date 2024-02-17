package lh.juicecompany.Exceptions;

public class NoSuchCredentialVariableException extends IllegalArgumentException {

    public NoSuchCredentialVariableException(String message) {
        super(message);
    }

    public NoSuchCredentialVariableException(String message, Throwable cause) {
        super(message, cause);
    }
}
