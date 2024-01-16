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
    public static final String YELLOW = "\u001B[33m";
    public static final String YELLOW_BOLD = "\033[1;33m";
    public static final String PURPLE = "\u001B[35m";
    public static final String PURPLE_BOLD = "\033[1;35m";


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

    public static String yellow(String text) {
        return YELLOW + text + RESET;
    }

    public static String purple(String text) {
        return PURPLE + text + RESET;
    }

    public static String redBold(String text) {
        return RED_BOLD + text + RESET;
    }

    public static String greenBold(String text) {
        return GREEN_BOLD + text + RESET;
    }

    public static String yellowBold(String text) {
        return YELLOW_BOLD + text + RESET;
    }

    public static String purpleBold(String text) {
        return PURPLE_BOLD + text + RESET;
    }
}
