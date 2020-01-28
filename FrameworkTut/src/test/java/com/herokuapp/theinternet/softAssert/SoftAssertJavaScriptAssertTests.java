package com.herokuapp.theinternet.softAssert;

import com.herokuapp.theinternet.pages.WelcomePage;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import testEngine.TestEngine;

public class SoftAssertJavaScriptAssertTests extends TestEngine {
    @Test
    public void jsAlertTextIsEqualsExpected() {
        SoftAssert softAssert = new SoftAssert();
        String alertText = new WelcomePage(getDriver(), log)
                .openPage()
                .clickJavaScriptAlertLink()
                .clickJSAlertButton()
                .getAlertText();
        softAssert.assertEquals(alertText, "First Check");
        softAssert.assertEquals(alertText, "Second Check");
        softAssert.assertEquals(alertText, "I am a JS Alert");
        softAssert.assertAll("Soft Assert message");
    }
}