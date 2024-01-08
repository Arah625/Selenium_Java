package lh.juicecompany.Colors;

public abstract class Color {

    public static final String RESET = "\u001B[0m";
    public static final String BLUE = "\u001B[34m";
    public static final String RED = "\u001B[31m";
    public static final String RED_BOLD = "\033[1;31m";
    public static final String BLUE_BOLD = "\033[1;34m";
    public static final String BLUE_BOLD_UNDERLINED = "\033[1;34m\033[4;34m";
    public static final String GREEN = "\u001B[32m";

    public static final String GREEN_BOLD = "\033[1;32m";
    String bold = "\033[1m{}\033[0m";


    public static String blue(String text) {
        return BLUE + text + RESET;
    }

    public static String blueBold(String text) {
        return BLUE_BOLD + text + RESET;
    }

    public static String blueBoldUnderlined(String text) {
        return BLUE_BOLD_UNDERLINED + text + RESET;
    }

    public static String red(String text) {
        return RED + text + RESET;
    }

    public static String green(String text) {
        return GREEN + text + RESET;
    }

    public static String redBold(String text) {
        return RED_BOLD + text + RESET;
    }

    public static String greenBold(String text) {
        return GREEN_BOLD + text + RESET;
    }
}
