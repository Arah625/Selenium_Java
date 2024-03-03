package lh.juicecompany.PageUtilities;

import java.util.HashMap;
import java.util.Map;

/**
 * A utility class for string manipulation, offering a collection of static methods
 * to perform various transformations and operations on strings. Designed to simplify
 * common tasks involving string processing, normalization, and modification.
 */
public class StringUtilities {

    /**
     * A map of Polish characters to their ASCII replacements. Used to normalize strings by removing diacritics.
     */
    private static final Map<Character, String> REPLACEMENTS = new HashMap<>();

    static {
        REPLACEMENTS.put('ą', "a");
        REPLACEMENTS.put('Ą', "A");
        REPLACEMENTS.put('ć', "c");
        REPLACEMENTS.put('Ć', "C");
        REPLACEMENTS.put('ę', "e");
        REPLACEMENTS.put('Ę', "E");
        REPLACEMENTS.put('ł', "l");
        REPLACEMENTS.put('Ł', "L");
        REPLACEMENTS.put('ń', "n");
        REPLACEMENTS.put('Ń', "N");
        REPLACEMENTS.put('ó', "o");
        REPLACEMENTS.put('Ó', "O");
        REPLACEMENTS.put('ś', "s");
        REPLACEMENTS.put('Ś', "S");
        REPLACEMENTS.put('ź', "z");
        REPLACEMENTS.put('Ź', "Z");
        REPLACEMENTS.put('ż', "z");
        REPLACEMENTS.put('Ż', "Z");
    }

    private StringUtilities() {
        // Private constructor to prevent instantiation.
    }

    /**
     * Retrieves the last N characters from the provided string.
     *
     * @param string               The string to trim.
     * @param numberOfCharsToLeave The number of characters to retain at the end of the string.
     * @return A substring containing the last N characters of the input string.
     */
    public static String getLastNumberOfCharacters(String string, int numberOfCharsToLeave) {
        return string.substring(string.length() - numberOfCharsToLeave);
    }

    /**
     * Replaces Polish letters in the given string with their ASCII equivalents.
     *
     * @param text The string containing Polish letters to replace.
     * @return A new string with Polish letters replaced by their specified ASCII equivalents.
     */
    public static String replacePolishLetters(String text) {
        StringBuilder stringWithReplacement = new StringBuilder();
        for (char character : text.toCharArray()) {
            stringWithReplacement.append(REPLACEMENTS.getOrDefault(character, String.valueOf(character)));
        }
        return stringWithReplacement.toString();
    }

    /**
     * Removes all non-digit characters from the provided string, leaving only digits.
     *
     * @param string The string from which to remove non-digit characters.
     * @return A string consisting only of the digit characters from the input string.
     */
    public static String leaveOnlyDigits(String string) {
        return string.replaceAll("\\D", "");
    }
}