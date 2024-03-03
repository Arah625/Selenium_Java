package lh.juicecompany.Exceptions;

/**
 * Indicates that an illegal or unsupported type of locator was used. This exception is thrown to
 * signal errors in specifying locators, typically in the context of Selenium WebDriver operations
 * or any other scenario where element locators are employed.
 * <p>
 * Extending {@link IllegalArgumentException}, this class provides a way to clearly communicate
 * issues related to the misuse of locators, facilitating debugging and ensuring that locator-related
 * errors are handled gracefully.
 */
public class IllegalLocatorTypeException extends IllegalArgumentException {

    /**
     * Constructs a new exception with the specified detail message. The message explains the reason
     * for the error and can be used to convey information about the illegal or unsupported locator type.
     *
     * @param message The detail message. The detail message is saved for later retrieval by the {@link #getMessage()} method.
     */
    public IllegalLocatorTypeException(String message) {
        super(message);
    }

    /**
     * Constructs a new exception with the specified detail message and cause. This constructor is useful
     * for exceptions that are the result of another underlying issue.
     *
     * @param message The detail message. The detail message is saved for later retrieval by the {@link #getMessage()} method.
     * @param cause   The cause (which is saved for later retrieval by the {@link #getCause()} method). A {@code null} value is
     *                permitted, and indicates that the cause is nonexistent or unknown.
     */
    public IllegalLocatorTypeException(String message, Throwable cause) {
        super(message, cause);
    }
}
