package com.herokuapp.theinternet.checkboxesTests;

import com.herokuapp.theinternet.pages.CheckboxesPage;
import com.herokuapp.theinternet.pages.WelcomePage;
import com.herokuapp.theinternet.testEngine.TestEngine;
import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import org.testng.Assert;
import org.testng.annotations.Ignore;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners({com.herokuapp.theinternet.testEngine.TestListener.class})
public class CheckBoxesTests extends TestEngine {

    @Test(groups = { "GroupOne" })
    @Story("Checking CheckBox data")
    @Description("Select second checkbox and check if the first checkbox is selected")
    @Severity(SeverityLevel.MINOR)
    public void clickOnCheckBox() {
        CheckboxesPage checkboxesPage = new WelcomePage(getDriver(), log)
                .openPage()
                .clickCheckboxesLink()
                .chooseCheckboxByText(" checkbox 2");
        Assert.assertTrue(checkboxesPage.returnSelectedCheckboxes().get(0));
    }

    @Test(groups = { "GroupOne" })
    @Story("Checking CheckBox data")
    @Description("Select all checkboxes and check all checkboxes is selected")
    @Severity(SeverityLevel.MINOR)
    public void chooseAllCheckBoxes() {
        boolean checkboxesSelected = new WelcomePage(getDriver(), log)
                .openPage()
                .clickCheckboxesLink()
                .chooseAllCheckboxes()
                .areAllCheckboxesSelected();
        Assert.assertTrue(checkboxesSelected);
    }

    @Test(groups = { "GroupTwo" })
    public void unchooseAllCheckBoxes() {
        boolean checkboxesSelected = new WelcomePage(getDriver(), log)
                .openPage()
                .clickCheckboxesLink()
                .unselectAllCheckboxes()
                .areAllCheckboxesSelected();
        Assert.assertFalse(checkboxesSelected);
    }

    @Test(groups = { "GroupTwo" })
    public void chooseFirstCheckboxOnPage() {
        int index = 2;
        CheckboxesPage checkboxesPage = new WelcomePage(getDriver(), log)
                .openPage()
                .clickCheckboxesLink()
                .chooseCheckboxByIndex(index);
        Assert.assertTrue(checkboxesPage.returnSelectedCheckboxes().get(index - 1));
    }
}