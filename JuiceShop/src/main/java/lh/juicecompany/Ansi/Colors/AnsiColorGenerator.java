package lh.juicecompany.Ansi.Colors;

import lh.juicecompany.Ansi.AnsiCode;

public class AnsiColorGenerator implements AnsiCode {

    private static final String TEXT_COLOR_PREFIX = "\u001B[38;5;";
    private static final String BACKGROUND_COLOR_PREFIX = "\u001B[48;5;";

    public static String getStandardColor(int colorCode) {
        if (colorCode < 0 || colorCode > 15) {
            throw new IllegalArgumentException("Color code must be between 0 and 15.");
        }
        return TEXT_COLOR_PREFIX + colorCode + "m";
    }

    public static String getBrightColor(int colorCode) {
        if (colorCode < 8 || colorCode > 15) {
            throw new IllegalArgumentException("Bright color code must be between 8 and 15.");
        }
        return TEXT_COLOR_PREFIX + colorCode + "m";
    }

    public static String getColorCode(int r, int g, int b) {
        if (r < 0 || r > 5 || g < 0 || g > 5 || b < 0 || b > 5) {
            throw new IllegalArgumentException("Color components (r, g, b) must be in the range 0-5.");
        }
        return TEXT_COLOR_PREFIX + (16 + (r * 36) + (g * 6) + b) + "m";
    }

    public static String getGrayscaleCode(int shade) {
        shade = Math.min(Math.max(shade, 0), 23);
        return BACKGROUND_COLOR_PREFIX + (232 + shade) + "m";
    }

    public static String getBackgroundColor(int colorCode) {
        colorCode = Math.min(Math.max(colorCode, 0), 255);
        return BACKGROUND_COLOR_PREFIX + colorCode + "m";
    }
}
