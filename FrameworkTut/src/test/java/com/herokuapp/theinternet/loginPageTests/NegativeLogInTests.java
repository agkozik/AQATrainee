package com.herokuapp.theinternet.loginPageTests;

import com.herokuapp.theinternet.pages.LoginPage;
import com.herokuapp.theinternet.pages.WelcomePage;
import org.openqa.selenium.By;
import org.testng.annotations.Test;
import testEngine.TestEngine;

import static org.testng.Assert.*;

public class NegativeLogInTests extends TestEngine {

    @Test
    public void openWelcomePage() {
        WelcomePage welcomePage=new WelcomePage(getDriver(), log)
                .openPage();
        assertEquals(dr.get().getCurrentUrl(),welcomePage.getPageUrl());
    }

    @Test
    public void openAuthPage() {
        LoginPage loginPage=new WelcomePage(getDriver(), log)
                .openPage()
                .clickFormAuthenticationLink();
        assertTrue(dr.get().findElement(By.xpath("//button[@class='radius']")).isDisplayed());
    }

    @Test
    void logInOnLoginPageWithWrongData() {
        LoginPage loginPage= new WelcomePage(getDriver(), log)
                .openPage()
                .clickFormAuthenticationLink();
        loginPage
                .enterUserNameAndPassword("user","password")
                .clickLoginButton();
        assertTrue(loginPage.getTextErrorMessageFromPage().contains("Your username is invalid!"));
    }
}