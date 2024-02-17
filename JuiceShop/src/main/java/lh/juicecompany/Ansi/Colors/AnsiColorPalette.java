package lh.juicecompany.Ansi.Colors;

import lh.juicecompany.Ansi.AnsiCode;
import lh.juicecompany.Ansi.Style.AnsiStyle;

public enum AnsiColorPalette implements AnsiCode {
    BLACK(AnsiColorGenerator.getStandardColor(0)),
    RED(AnsiColorGenerator.getStandardColor(1)),
    GREEN(AnsiColorGenerator.getStandardColor(2)),
    YELLOW(AnsiColorGenerator.getStandardColor(3)),
    BLUE(AnsiColorGenerator.getStandardColor(4)),
    MAGENTA(AnsiColorGenerator.getStandardColor(5)),
    CYAN(AnsiColorGenerator.getStandardColor(6)),
    WHITE(AnsiColorGenerator.getStandardColor(7)),

    BLACK_BOLD(AnsiStyle.BOLD + AnsiColorGenerator.getStandardColor(0)),
    RED_BOLD(AnsiStyle.BOLD + AnsiColorGenerator.getStandardColor(1)),
    GREEN_BOLD(AnsiStyle.BOLD + AnsiColorGenerator.getStandardColor(2)),
    YELLOW_BOLD(AnsiStyle.BOLD + AnsiColorGenerator.getStandardColor(3)),
    BLUE_BOLD(AnsiStyle.BOLD + AnsiColorGenerator.getStandardColor(4)),
    MAGENTA_BOLD(AnsiStyle.BOLD + AnsiColorGenerator.getStandardColor(5)),
    CYAN_BOLD(AnsiStyle.BOLD + AnsiColorGenerator.getStandardColor(6)),
    WHITE_BOLD(AnsiStyle.BOLD + AnsiColorGenerator.getStandardColor(7)),

    BLACK_UNDERLINED(AnsiStyle.BOLD + AnsiStyle.UNDERLINE + AnsiColorGenerator.getStandardColor(0)),
    RED_UNDERLINED(AnsiStyle.BOLD + AnsiStyle.UNDERLINE + AnsiColorGenerator.getStandardColor(1)),
    GREEN_UNDERLINED(AnsiStyle.BOLD + AnsiStyle.UNDERLINE + AnsiColorGenerator.getStandardColor(2)),
    YELLOW_UNDERLINED(AnsiStyle.BOLD + AnsiStyle.UNDERLINE + AnsiColorGenerator.getStandardColor(3)),
    BLUE_UNDERLINED(AnsiStyle.BOLD + AnsiStyle.UNDERLINE + AnsiColorGenerator.getStandardColor(4)),
    MAGENTA_UNDERLINED(AnsiStyle.BOLD + AnsiStyle.UNDERLINE + AnsiColorGenerator.getStandardColor(5)),
    CYAN_UNDERLINED(AnsiStyle.BOLD + AnsiStyle.UNDERLINE + AnsiColorGenerator.getStandardColor(6)),
    WHITE_UNDERLINED(AnsiStyle.BOLD + AnsiStyle.UNDERLINE + AnsiColorGenerator.getStandardColor(7)),

    BLACK_BOLD_UNDERLINED(AnsiStyle.UNDERLINE + AnsiColorGenerator.getStandardColor(0)),
    RED_BOLD_UNDERLINED(AnsiStyle.UNDERLINE + AnsiColorGenerator.getStandardColor(1)),
    GREEN_BOLD_UNDERLINED(AnsiStyle.UNDERLINE + AnsiColorGenerator.getStandardColor(2)),
    YELLOW_BOLD_UNDERLINED(AnsiStyle.UNDERLINE + AnsiColorGenerator.getStandardColor(3)),
    BLUE_BOLD_UNDERLINED(AnsiStyle.UNDERLINE + AnsiColorGenerator.getStandardColor(4)),
    MAGENTA_BOLD_UNDERLINED(AnsiStyle.UNDERLINE + AnsiColorGenerator.getStandardColor(5)),
    CYAN_BOLD_UNDERLINED(AnsiStyle.UNDERLINE + AnsiColorGenerator.getStandardColor(6)),
    WHITE_BOLD_UNDERLINED(AnsiStyle.UNDERLINE + AnsiColorGenerator.getStandardColor(7)),

    BLACK_BRIGHT(AnsiColorGenerator.getBrightColor(8)),
    RED_BRIGHT(AnsiColorGenerator.getBrightColor(9)),
    GREEN_BRIGHT(AnsiColorGenerator.getBrightColor(10)),
    YELLOW_BRIGHT(AnsiColorGenerator.getBrightColor(11)),
    BLUE_BRIGHT(AnsiColorGenerator.getBrightColor(12)),
    MAGENTA_BRIGHT(AnsiColorGenerator.getBrightColor(13)),
    CYAN_BRIGHT(AnsiColorGenerator.getBrightColor(14)),
    WHITE_BRIGHT(AnsiColorGenerator.getBrightColor(15)),

    BLACK_BOLD_BRIGHT(AnsiStyle.BOLD + AnsiColorGenerator.getBrightColor(8)),
    RED_BOLD_BRIGHT(AnsiStyle.BOLD + AnsiColorGenerator.getBrightColor(9)),
    GREEN_BOLD_BRIGHT(AnsiStyle.BOLD + AnsiColorGenerator.getBrightColor(10)),
    YELLOW_BOLD_BRIGHT(AnsiStyle.BOLD + AnsiColorGenerator.getBrightColor(11)),
    BLUE_BOLD_BRIGHT(AnsiStyle.BOLD + AnsiColorGenerator.getBrightColor(12)),
    MAGENTA_BOLD_BRIGHT(AnsiStyle.BOLD + AnsiColorGenerator.getBrightColor(13)),
    CYAN_BOLD_BRIGHT(AnsiStyle.BOLD + AnsiColorGenerator.getBrightColor(14)),
    WHITE_BOLD_BRIGHT(AnsiStyle.BOLD + AnsiColorGenerator.getBrightColor(15)),

    BLACK_BACKGROUND(AnsiColorGenerator.getBackgroundColor(0)),
    RED_BACKGROUND(AnsiColorGenerator.getBackgroundColor(1)),
    GREEN_BACKGROUND(AnsiColorGenerator.getBackgroundColor(2)),
    YELLOW_BACKGROUND(AnsiColorGenerator.getBackgroundColor(3)),
    BLUE_BACKGROUND(AnsiColorGenerator.getBackgroundColor(4)),
    MAGENTA_BACKGROUND(AnsiColorGenerator.getBackgroundColor(5)),
    CYAN_BACKGROUND(AnsiColorGenerator.getBackgroundColor(6)),
    WHITE_BACKGROUND(AnsiColorGenerator.getBackgroundColor(7)),

    BLACK_BACKGROUND_BRIGHT(AnsiColorGenerator.getBackgroundColor(8)),
    RED_BACKGROUND_BRIGHT(AnsiColorGenerator.getBackgroundColor(9)),
    GREEN_BACKGROUND_BRIGHT(AnsiColorGenerator.getBackgroundColor(10)),
    YELLOW_BACKGROUND_BRIGHT(AnsiColorGenerator.getBackgroundColor(11)),
    BLUE_BACKGROUND_BRIGHT(AnsiColorGenerator.getBackgroundColor(12)),
    MAGENTA_BACKGROUND_BRIGHT(AnsiColorGenerator.getBackgroundColor(13)),
    CYAN_BACKGROUND_BRIGHT(AnsiColorGenerator.getBackgroundColor(14)),
    WHITE_BACKGROUND_BRIGHT(AnsiColorGenerator.getBackgroundColor(15));

    private String colorCode;


    AnsiColorPalette(String colorCode) {
        this.colorCode = colorCode;
    }

    public static String applyColor(String text, AnsiColorPalette textColor, AnsiColorPalette backgroundColor) {
        return textColor.apply(backgroundColor.apply(text));
    }

    public static String applyColor(String text, AnsiColorPalette textColor) {
        return textColor.apply(text);
    }

    public String getColor() {
        return colorCode;
    }

    public String apply(String text) {
        return colorCode + text + AnsiCode.RESET;
    }

}
