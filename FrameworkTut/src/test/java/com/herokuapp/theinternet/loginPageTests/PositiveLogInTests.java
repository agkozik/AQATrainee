package com.herokuapp.theinternet.loginPageTests;

import com.herokuapp.theinternet.pages.SecureAreaPage;
import com.herokuapp.theinternet.pages.WelcomePage;
import org.testng.Assert;
import org.testng.annotations.Test;
import turboEngine.TestEngine;

public class PositiveLogInTests extends TestEngine {

    @Test
    public void logInTest() {
        log.info("Starting logIn Workflow PositiveTest");
        SecureAreaPage secureAreaPage = new WelcomePage(getDriver(), log)
                .openPage()
                .clickFormAuthenticationLink()
                .enterUserNameAndPassword("tomsmith", "SuperSecretPassword!")
                .clickLoginButton();
        Assert.assertEquals(secureAreaPage.getCurrentUrlFromBrowser(), secureAreaPage.getExpectedUrl());
    }
}