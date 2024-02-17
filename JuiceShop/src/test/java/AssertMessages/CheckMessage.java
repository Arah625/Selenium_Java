package AssertMessages;

import lh.juicecompany.Ansi.Colors.Color;

public abstract class CheckMessage {

    public static String checkboxIsNotChecked(String checkbox) {
        return Color.red("Checkbox " + Color.redBold(checkbox) + Color.red(" is not checked!"));
    }

    public static String checkboxIsChecked(String checkbox) {
        return Color.red("Checkbox " + Color.redBold(checkbox) + Color.red(" is checked!"));
    }
}
