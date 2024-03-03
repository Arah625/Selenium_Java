package lh.juicecompany.Exceptions;

/**
 * Thrown to indicate that a requested system property could not be found. This exception is
 * typically raised in scenarios where the application expects certain system properties to be
 * defined for proper operation, but one or more of these properties are missing or not set.
 * <p>
 * Extending {@link IllegalArgumentException}, this class helps in identifying and addressing
 * issues related to the configuration and setup of the application environment, by clearly
 * signaling the absence of required system properties.
 */
public class NoSuchSystemPropertyException extends IllegalArgumentException {
//TODO: why this is not used? Probably should be used in  @SystemProperties.java class

    /**
     * Constructs a new exception with the specified detail message. The message helps to
     * identify which system property was expected but not found, aiding in the process of
     * troubleshooting and resolving configuration issues.
     *
     * @param message The detail message explaining which system property is missing.
     */
    public NoSuchSystemPropertyException(String message) {
        super(message);
    }

    /**
     * Constructs a new exception with the specified detail message and cause. This constructor
     * is useful for exceptions that are the result of a more complex issue, allowing for the
     * underlying cause to be identified and addressed.
     *
     * @param message The detail message explaining which system property is missing.
     * @param cause   The cause of the exception, providing additional context or the original
     *                error that led to the recognition that a system property is missing.
     */
    public NoSuchSystemPropertyException(String message, Throwable cause) {
        super(message, cause);
    }
}
