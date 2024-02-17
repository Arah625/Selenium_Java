package Tests;

import AssertMessages.VisibilityMessage;
import Setup.BaseTest;
import Setup.TestGroup;
import Setup.TestLogger;
import Utilities.Language;
import org.testng.Assert;
import org.testng.annotations.Test;

//@ModeAnnotation(ModeType.STANDARD)
public class Languages extends BaseTest {

    @Test(priority = 1, groups = {TestGroup.REGRESSION, TestGroup.HIGH}, description = "Change language - check site elements")
    public void changeLanguageToSpanish() throws Exception {
        String language = Language.SPANISH.getLocalName();
        home.selectLanguage(language);
        Assert.assertTrue(home.isLanguageChangedAlertVisible(language), VisibilityMessage.alertIsNotVisible("Language has been changed to " + language));
        Assert.assertTrue(home.isForcePageReloadButtonVisible(), VisibilityMessage.buttonIsNotVisible("Force page reload"));
        String spanishHeader = "Todos los Productos";
        Assert.assertTrue(home.isAllProductsHeaderVisible(spanishHeader), VisibilityMessage.headerIsNotVisible(spanishHeader));
        String isoCode = Language.getIsoAlphaTwoCodeByLocalName(language);
        Assert.assertEquals(home.getLanguageDropdownButtonCode(), isoCode, VisibilityMessage.countryCodeIsNotVisible(isoCode));
        TestLogger.logTestVariables("LANGUAGE", language, "ISO CODE", isoCode);
    }

    @Test(priority = 2, groups = {TestGroup.REGRESSION, TestGroup.CRITICAL}, description = "Change language - check ghost message")
    public void changeLanguageToRandomLanguage() throws Exception {
        String language = Language.getRandomLocalName();
        home.selectLanguage(language);
        Assert.assertTrue(home.isLanguageChangedAlertVisible(language), VisibilityMessage.alertIsNotVisible("Language has been changed to " + language));
        Assert.assertTrue(home.isForcePageReloadButtonVisible(), VisibilityMessage.buttonIsNotVisible("Force page reload"));
        String isoCode = Language.getIsoAlphaTwoCodeByLocalName(language);
        Assert.assertEquals(home.getLanguageDropdownButtonCode(), isoCode, VisibilityMessage.countryCodeIsNotVisible(isoCode));
        TestLogger.logTestVariables("LANGUAGE", language, "ISO CODE", isoCode);
    }


}
