package lh.juicecompany.PageUtilities;

import java.util.HashMap;
import java.util.Map;

public class StringUtilities {
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
    }

    public static String getLastNumberOfCharacters(String string, int numberOfCharsToLeave) {
        return string.substring(string.length() - numberOfCharsToLeave);
    }

    public static String replacePolishLetters(String text) {
        StringBuilder stringWithReplacement = new StringBuilder();
        for (char character : text.toCharArray()) {
            stringWithReplacement.append(REPLACEMENTS.getOrDefault(character, String.valueOf(character)));
        }
        return stringWithReplacement.toString();
    }

    public static String leaveOnlyDigits(String string) {
        return string.replaceAll("\\D", "");
    }
}
