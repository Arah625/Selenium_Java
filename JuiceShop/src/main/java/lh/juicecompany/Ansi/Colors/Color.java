package lh.juicecompany.Ansi.Colors;

import lh.juicecompany.Ansi.Style.AnsiStyle;

public abstract class Color {

    public static final String RESET = AnsiStyle.RESET;
    public static final String BLUE = AnsiColorGenerator.getStandardColor(4);
    public static final String RED = AnsiColorGenerator.getStandardColor(1);
    public static final String RED_BOLD = AnsiStyle.BOLD + AnsiColorGenerator.getStandardColor(1);
    public static final String BLUE_BOLD = AnsiStyle.BOLD + AnsiColorGenerator.getStandardColor(4);
    public static final String BLUE_BOLD_UNDERLINED = AnsiStyle.BOLD + AnsiStyle.UNDERLINE + AnsiColorGenerator.getStandardColor(4);
    public static final String GREEN = AnsiColorGenerator.getStandardColor(2);
    public static final String GREEN_BOLD = AnsiStyle.BOLD + AnsiColorGenerator.getStandardColor(2);
    public static final String YELLOW = AnsiColorGenerator.getStandardColor(3);
    public static final String YELLOW_BOLD = AnsiStyle.BOLD + AnsiColorGenerator.getStandardColor(3);
    public static final String MAGENTA = AnsiColorGenerator.getStandardColor(5);
    public static final String MAGENTA_BOLD = AnsiStyle.BOLD + AnsiColorGenerator.getStandardColor(5);

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

    public static String magenta(String text) {
        return MAGENTA + text + RESET;
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

    public static String magentaBold(String text) {
        return MAGENTA_BOLD + text + RESET;
    }
}
