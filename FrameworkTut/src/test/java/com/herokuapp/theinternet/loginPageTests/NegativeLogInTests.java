package com.herokuapp.theinternet.loginPageTests;

import com.herokuapp.theinternet.pages.LoginPage;
import com.herokuapp.theinternet.pages.WelcomePage;
import engine.BaseTest;
import org.openqa.selenium.By;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class NegativeLogInTests extends BaseTest {

    @Test
    public void openWelcomePage() {
        WelcomePage welcomePage=new WelcomePage(driver, log)
                .openPage();
        assertEquals(driver.getCurrentUrl(),welcomePage.getPageUrl());
    }

    @Test
    public void openAuthPage() {
        LoginPage loginPage=new WelcomePage(driver, log)
                .openPage()
                .clickFormAuthenticationLink();
        assertTrue(driver.findElement(By.xpath("//button[@class='radius']")).isDisplayed());
    }

    @Test
    void logInOnLoginPageWithWrongData() {
        LoginPage loginPage= new WelcomePage(driver, log)
                .openPage()
                .clickFormAuthenticationLink();
        loginPage
                .enterUserNameAndPassword("user","password")
                .clickLoginButton();
        assertTrue(loginPage.getTextErrorMessageFromPage().contains("Your username is invalid!"));
    }
}