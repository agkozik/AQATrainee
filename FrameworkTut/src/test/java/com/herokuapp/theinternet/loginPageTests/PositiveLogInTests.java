package com.herokuapp.theinternet.loginPageTests;

import com.herokuapp.theinternet.pages.SecureAreaPage;
import com.herokuapp.theinternet.pages.WelcomePage;
import engine.TestUtilities;
import org.testng.Assert;
import org.testng.annotations.Test;

public class PositiveLogInTests extends TestUtilities {

    @Test
    public void logInTest(){
        log.info("Starting logIn Workflow PositiveTest");
        SecureAreaPage secureAreaPage = new WelcomePage(driver, log)
                .openPage()
                .clickFormAuthenticationLink()
                .enterUserNameAndPassword("tomsmith","SuperSecretPassword!")
                .clickLoginButton();
        Assert.assertEquals(secureAreaPage.getCurrentUrlFromBrowser(), secureAreaPage.getExpectedUrl());
    }
}