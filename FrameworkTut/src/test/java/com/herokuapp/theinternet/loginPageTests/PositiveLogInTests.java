package com.herokuapp.theinternet.loginPageTests;

import com.herokuapp.theinternet.pages.SecureAreaPage;
import com.herokuapp.theinternet.pages.WelcomePage;
import com.herokuapp.theinternet.testEngine.TestEngine;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners({com.herokuapp.theinternet.testEngine.TestListener.class})
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