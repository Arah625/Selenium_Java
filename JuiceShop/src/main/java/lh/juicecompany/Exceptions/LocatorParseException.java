package lh.juicecompany.Exceptions;

import java.text.ParseException;

/**
 * Represents errors encountered while parsing locator strings or expressions. This exception
 * extends {@link ParseException} to provide detailed information about parsing failures specific
 * to locators used in identifying web elements or similar use cases.
 * <p>
 * The exception includes functionality to pinpoint the location of the error within the locator
 * string, aiding in the rapid identification and correction of parsing issues.
 */
public class LocatorParseException extends ParseException {

    /**
     * Constructs a LocatorParseException with a detailed error message and the position
     * at which the error was identified in the locator string.
     *
     * @param message     The detail message explaining the parsing error and possibly suggesting
     *                    a resolution or providing context about the nature of the error.
     * @param errorOffset The position within the locator string where the error was detected.
     *                    This index is zero-based; an index of zero indicates the first character.
     */
    public LocatorParseException(String message, int errorOffset) {
        super(message, errorOffset);
    }
}
