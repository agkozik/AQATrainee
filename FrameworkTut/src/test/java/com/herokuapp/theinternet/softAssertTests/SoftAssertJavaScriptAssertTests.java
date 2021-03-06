package com.herokuapp.theinternet.softAssertTests;

import com.herokuapp.theinternet.pages.WelcomePage;
import com.herokuapp.theinternet.testEngine.TestEngine;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
@Listeners({com.herokuapp.theinternet.testEngine.TestListener.class})
public class SoftAssertJavaScriptAssertTests extends TestEngine {
    @Test
    public void jsAlertTextIsEqualsExpected() {
        SoftAssert softAssert = new SoftAssert();
        String alertText = new WelcomePage(getDriver(), log)
                .openPage()
                .clickJavaScriptAlertLink()
                .clickJSAlertButton()
                .getAlertText();
        softAssert.assertTrue(alertText.contains("JS Alert"));
        softAssert.assertEquals(alertText, "I am a JS Alert");
        softAssert.assertAll("Soft Assert message");
    }
}