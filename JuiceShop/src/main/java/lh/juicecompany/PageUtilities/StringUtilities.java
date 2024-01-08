package lh.juicecompany.PageUtilities;

public class StringUtilities {


    public static String getLastNumberOfCharacters(String string, int numberOfCharsToLeave) {
        return string.substring(string.length() - numberOfCharsToLeave);
    }


}
