package lh.juicecompany.Exceptions;

import java.text.ParseException;

public class LocatorParseException extends ParseException {

    public LocatorParseException(String message, int errorOffset) {
        super(message, errorOffset);
    }
}
