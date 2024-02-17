package AssertMessages;

import lh.juicecompany.Ansi.Colors.Color;

public abstract class SelectMessage {

    public static String radioButtonIsNotSelected(String button) {
        return Color.red("Radio button " + Color.redBold(button) + Color.red(" is not selected!"));
    }

    public static String radioButtonIsSelected(String button) {
        return Color.red("Radio button " + Color.redBold(button) + Color.red(" is selected!"));
    }
}
