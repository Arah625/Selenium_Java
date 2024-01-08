package Tests.StandardMode;

import AssertMessages.VisibilityMessage;
import Setup.Base;
import Setup.TestGroup;
import Utilities.Language;
import org.testng.Assert;
import org.testng.annotations.Test;

//@ModeAnnotation(ModeType.STANDARD)
public class Languages extends Base {

    @Test(priority = 1, groups = {TestGroup.REGRESSION, TestGroup.HIGH})
    public void changeLanguageToSpanish() {
        testCaseName("Change language - check site elements");
        String language = Language.SPANISH.getLocalName();
        home.selectLanguage(language);
        Assert.assertTrue(home.isLanguageChangedAlertVisible(language), VisibilityMessage.alertIsNotVisible("Language has been changed to " + language));
        Assert.assertTrue(home.isForcePageReloadButtonVisible(), VisibilityMessage.buttonIsNotVisible("Force page reload"));
        String spanishHeader = "Todos los Productos";
        Assert.assertTrue(home.isAllProductsHeaderVisible(spanishHeader), VisibilityMessage.headerIsNotVisible(spanishHeader));
        String isoCode = Language.getIsoAlphaTwoCodeByLocalName(language);
        Assert.assertEquals(home.getLanguageDropdownButtonCode(), isoCode, VisibilityMessage.countryCodeIsNotVisible(isoCode));
    }

    @Test(priority = 2, groups = {TestGroup.REGRESSION, TestGroup.CRITICAL})
    public void changeLanguageToRandomLanguage() {
        testCaseName("Change language - check ghost message");
        String language = Language.getRandomLocalName();
        home.selectLanguage(language);
        Assert.assertTrue(home.isLanguageChangedAlertVisible(language), VisibilityMessage.alertIsNotVisible("Language has been changed to " + language));
        Assert.assertTrue(home.isForcePageReloadButtonVisible(), VisibilityMessage.buttonIsNotVisible("Force page reload"));
    }


}
