package com.herokuapp.theinternet.loginPageTests;

import com.herokuapp.theinternet.pages.CheckboxesPage;
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

    @Test
    public void clickOnCheckBox (){
        CheckboxesPage checkboxesPage=new WelcomePage(driver,log)
                .openPage()
                .clickCheckboxesLink()
                .chooseCheckboxByText(" checkbox 1");
    }

    @Test
    public void chooseAllCheckBoxes (){
        Boolean checkboxesSelected =new WelcomePage(driver,log)
                .openPage()
                .clickCheckboxesLink()
                .chooseAllCheckboxes()
                .areAllCheckboxesSelected();
        Assert.assertTrue(checkboxesSelected);
    }

    @Test
    public void unchooseAllCheckBoxes (){
        Boolean checkboxesSelected =new WelcomePage(driver,log)
                .openPage()
                .clickCheckboxesLink()
                .unselectAllCheckboxes()
                .areAllCheckboxesSelected();
        Assert.assertFalse(checkboxesSelected);
    }

    @Test
    public void chooseFirstCheckboxOnPage(){
        CheckboxesPage checkboxesPage=new WelcomePage(driver,log)
                .openPage()
                .clickCheckboxesLink()
                .chooseCheckboxByIndex(1);
        Assert.assertTrue(checkboxesPage.returnSelectedCheckboxes().get(1-1));
    }
}