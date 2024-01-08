package AssertMessages;

import lh.juicecompany.Colors.Color;

public abstract class EnableMessage {


    public static String buttonIsNotEnabled(String button) {
        return Color.red("Alert " + Color.redBold(button) + Color.red(" is not enabled!"));
    }

    public static String buttonIsEnabled(String button) {
        return Color.red("Alert " + Color.redBold(button) + Color.red(" is enabled!"));
    }

}
