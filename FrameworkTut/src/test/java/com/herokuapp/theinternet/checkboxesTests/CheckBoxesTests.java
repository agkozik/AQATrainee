package com.herokuapp.theinternet.checkboxesTests;

import com.herokuapp.theinternet.pages.CheckboxesPage;
import com.herokuapp.theinternet.pages.WelcomePage;
import org.testng.Assert;
import org.testng.annotations.Test;
import testEngine.TestEngine;

import java.io.IOException;

public class CheckBoxesTests extends TestEngine {

    @Test
    public void clickOnCheckBox() {
        CheckboxesPage checkboxesPage = new WelcomePage(getDriver(), log)
                .openPage()
                .clickCheckboxesLink()
                .chooseCheckboxByText(" checkbox 1");
    }

    @Test
    public void chooseAllCheckBoxes() {
        boolean checkboxesSelected = new WelcomePage(getDriver(), log)
                .openPage()
                .clickCheckboxesLink()
                .chooseAllCheckboxes()
                .areAllCheckboxesSelected();
        Assert.assertTrue(checkboxesSelected);
    }

    @Test
    public void unchooseAllCheckBoxes() {
        boolean checkboxesSelected = new WelcomePage(getDriver(), log)
                .openPage()
                .clickCheckboxesLink()
                .unselectAllCheckboxes()
                .areAllCheckboxesSelected();
        Assert.assertFalse(checkboxesSelected);
    }

    @Test
    public void chooseFirstCheckboxOnPage() throws IOException {
        int index=1;
        CheckboxesPage checkboxesPage = new WelcomePage(getDriver(), log)
                .openPage()
                .clickCheckboxesLink()
                .chooseCheckboxByIndex(index);
        takeScreenshot();
        Assert.assertTrue(checkboxesPage.returnSelectedCheckboxes().get(index - 1));
    }
}