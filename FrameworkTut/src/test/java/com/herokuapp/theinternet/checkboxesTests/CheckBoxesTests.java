package com.herokuapp.theinternet.checkboxesTests;

import com.herokuapp.theinternet.pages.CheckboxesPage;
import com.herokuapp.theinternet.pages.WelcomePage;
import com.herokuapp.theinternet.testEngine.TestEngine;
import io.qameta.allure.Story;
import org.testng.Assert;
import org.testng.annotations.Ignore;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import ru.yandex.qatools.allure.annotations.Description;
import ru.yandex.qatools.allure.annotations.Severity;
import ru.yandex.qatools.allure.model.SeverityLevel;

@Listeners({com.herokuapp.theinternet.testEngine.TestListener.class})
public class CheckBoxesTests extends TestEngine {

    @Test
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
    public void chooseFirstCheckboxOnPage() {
        int index = 2;
        CheckboxesPage checkboxesPage = new WelcomePage(getDriver(), log)
                .openPage()
                .clickCheckboxesLink()
                .chooseCheckboxByIndex(index);
        Assert.assertTrue(checkboxesPage.returnSelectedCheckboxes().get(index - 1));
    }
}