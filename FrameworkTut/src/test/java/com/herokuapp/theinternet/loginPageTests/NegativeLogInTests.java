package com.herokuapp.theinternet.loginPageTests;

import com.herokuapp.theinternet.pages.LoginPage;
import com.herokuapp.theinternet.pages.WelcomePage;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;
import testEngine.CsvDataProviders;
import testEngine.TestEngine;

import java.util.Map;

import static org.testng.Assert.*;

public class NegativeLogInTests extends TestEngine {

    @Test(priority = 1, dataProvider = "csvReader", dataProviderClass = CsvDataProviders.class)
    public void negativeLogInTest(Map<String, String> testData) {
        // Data
        String number = testData.get("number");
        String username  = testData.get("username");
        String password = testData.get("password");
        String expectedErrorMessage = testData.get("expectedMessage");
        String description = testData.get("description");

        log.info("Starting negativeLogInTest #" + number + " for " + description);

        // open main page
        WelcomePage welcomePage = new WelcomePage(getDriver(), log);
        welcomePage.openPage();

        // Click on Form Authentication link
        LoginPage loginPage = welcomePage.clickFormAuthenticationLink();

        // execute negative login
        loginPage.enterUserNameAndPassword(username,password);
        loginPage.clickLoginButton();

        // wait for error message
        loginPage.waitForErrorMessage();
        String message = loginPage.getTextErrorMessageFromPage();

        // Verification
        Assert.assertTrue(message.contains(expectedErrorMessage), "Message doesn't contain expected text.");
    }

    @Test
    public void openWelcomePage() {
        WelcomePage welcomePage=new WelcomePage(getDriver(), log)
                .openPage();
        assertEquals(getDriver().getCurrentUrl(),welcomePage.getPageUrl());
    }

    @Test
    public void openAuthPage() {
        LoginPage loginPage=new WelcomePage(getDriver(), log)
                .openPage()
                .clickFormAuthenticationLink();
        assertTrue(getDriver().findElement(By.xpath("//button[@class='radius']")).isDisplayed());
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