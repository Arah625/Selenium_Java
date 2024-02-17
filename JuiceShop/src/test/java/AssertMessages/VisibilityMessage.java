package AssertMessages;

import lh.juicecompany.Ansi.Colors.Color;

public abstract class VisibilityMessage {

    public static String pageIsNotVisible(String page) {
        return Color.red("Page " + Color.redBold(page) + Color.red(" is not visible!"));
    }

    public static String pageIsVisible(String page) {
        return Color.red("Page " + Color.redBold(page) + Color.red(" is visible!"));
    }

    public static String headerIsNotVisible(String header) {
        return Color.red("Header " + Color.redBold(header) + Color.red(" is not visible!"));
    }

    public static String headerIsVisible(String header) {
        return Color.red("Header " + Color.redBold(header) + Color.red(" is visible!"));
    }

    public static String modalHeaderIsNotVisible(String modalHeader) {
        return Color.red("Modal header " + Color.redBold(modalHeader) + Color.red(" is not visible!"));
    }

    public static String modalHeaderIsVisible(String modalHeader) {
        return Color.red("Modal header " + Color.redBold(modalHeader) + Color.red(" is visible!"));
    }

    public static String alertIsNotVisible(String alert) {
        return Color.red("Alert " + Color.redBold(alert) + Color.red(" is not visible!"));
    }

    public static String alertIsVisible(String alert) {
        return Color.red("Alert " + Color.redBold(alert) + Color.red(" is visible!"));
    }

    public static String fieldValidationAlertIsNotVisible(String fieldValidationAlert) {
        return Color.red("Field validation alert " + Color.redBold(fieldValidationAlert) + Color.red(" is not visible!"));
    }

    public static String fieldValidationAlertIsVisible(String fieldValidationAlert) {
        return Color.red("Field validation alert " + Color.redBold(fieldValidationAlert) + Color.red(" is visible!"));
    }

    public static String ghostMessageIsNotVisible(String ghostMessage) {
        return Color.red("Ghost message " + Color.redBold(ghostMessage) + Color.red(" is not visible!"));
    }

    public static String ghostMessageIsVisible(String ghostMessage) {
        return Color.red("Ghost message " + Color.redBold(ghostMessage) + Color.red(" is visible!"));
    }

    public static String buttonIsNotVisible(String button) {
        return Color.red("Alert " + Color.redBold(button) + Color.red(" is not visible!"));
    }

    public static String buttonIsVisible(String button) {
        return Color.red("Alert " + Color.redBold(button) + Color.red(" is visible!"));
    }

    public static String countryCodeIsNotVisible(String button) {
        return Color.red("Country code " + Color.redBold(button) + Color.red(" is not visible!"));
    }

    public static String countryCodeIsVisible(String button) {
        return Color.red("Country code " + Color.redBold(button) + Color.red(" is visible!"));
    }

    public static String listOfElementsIsEmpty(String listName) {
        return Color.redBold(listName) + Color.red(" list is empty!");
    }

    public static String listOfElementsIsNotEmpty(String listName) {
        return Color.redBold(listName) + Color.red(" list is not empty!");
    }
}
