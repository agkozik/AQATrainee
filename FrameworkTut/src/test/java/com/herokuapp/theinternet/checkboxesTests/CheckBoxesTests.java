package com.herokuapp.theinternet.checkboxesTests;

import com.herokuapp.theinternet.pages.CheckboxesPage;
import com.herokuapp.theinternet.pages.WelcomePage;
import org.testng.Assert;
import org.testng.annotations.Ignore;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import com.herokuapp.theinternet.testEngine.TestEngine;

import java.io.IOException;

@Listeners({com.herokuapp.theinternet.testEngine.TestListener.class})
public class CheckBoxesTests extends TestEngine {

    @Test
    public void clickOnCheckBox() {
        CheckboxesPage checkboxesPage = new WelcomePage(getDriver(), log)
                .openPage()
                .clickCheckboxesLink()
                .chooseCheckboxByText(" checkbox 2");
        Assert.assertTrue(checkboxesPage.returnSelectedCheckboxes().get(0));
    }

    @Ignore
    @Test
    public void chooseAllCheckBoxes() {
        boolean checkboxesSelected = new WelcomePage(getDriver(), log)
                .openPage()
                .clickCheckboxesLink()
                .chooseAllCheckboxes()
                .areAllCheckboxesSelected();
        Assert.assertTrue(checkboxesSelected);
    }

    @Ignore
    @Test
    public void unchooseAllCheckBoxes() {
        boolean checkboxesSelected = new WelcomePage(getDriver(), log)
                .openPage()
                .clickCheckboxesLink()
                .unselectAllCheckboxes()
                .areAllCheckboxesSelected();
        Assert.assertFalse(checkboxesSelected);
    }

    @Ignore
    @Test
    public void chooseFirstCheckboxOnPage() throws IOException {
        int index = 2;
        CheckboxesPage checkboxesPage = new WelcomePage(getDriver(), log)
                .openPage()
                .clickCheckboxesLink()
                .chooseCheckboxByIndex(index);
        Assert.assertTrue(checkboxesPage.returnSelectedCheckboxes().get(index - 1));
    }
}