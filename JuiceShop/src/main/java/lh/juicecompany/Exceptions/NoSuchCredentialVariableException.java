package lh.juicecompany.Exceptions;

/**
 * Thrown to indicate that a requested credential variable could not be found. This exception is
 * typically raised in scenarios where the application configuration or environment setup requires
 * specific credentials that are missing or not set.
 * <p>
 * Extending {@link IllegalArgumentException}, this class is used to clearly identify issues related
 * to configuration and environment setup, facilitating easier diagnosis and correction of problems
 * related to missing credentials.
 */
public class NoSuchCredentialVariableException extends IllegalArgumentException {

    /**
     * Constructs a new exception with the specified detail message. The message provides
     * insights into which credential variable was expected but not found, aiding in troubleshooting.
     *
     * @param message The detail message explaining which credential variable is missing.
     */
    public NoSuchCredentialVariableException(String message) {
        super(message);
    }

    /**
     * Constructs a new exception with the specified detail message and cause. This constructor allows
     * for chaining exceptions where the absence of a credential variable leads to a more complex error scenario.
     *
     * @param message The detail message explaining which credential variable is missing.
     * @param cause   The cause of the exception, providing more context or the original error that necessitated this exception.
     */
    public NoSuchCredentialVariableException(String message, Throwable cause) {
        super(message, cause);
    }
}