package com.herokuapp.theinternet.dropDownTests;

import com.herokuapp.theinternet.pages.DropdownPage;
import com.herokuapp.theinternet.pages.WelcomePage;
import org.testng.Assert;
import org.testng.annotations.Test;
import testEngine.TestEngine;

public class DropDowntests extends TestEngine {

    @Test
    public void clickOnDropDownItem() {
        DropdownPage dropdownPage = new WelcomePage(getDriver(), log)
                .openPage()
                .clickDropdownLink()
                .clickOnDropDownField()
                .selectDropDownValueByText("Option 1");
        Assert.assertEquals(dropdownPage.getSelectedItemFromDropdown(), "Option 1");
    }

    @Test
    public void clickOnDropdownItemByValue() {
        DropdownPage dropdownPage = new WelcomePage(getDriver(), log)
                .openPage()
                .clickDropdownLink()
                .selectDropDownItemByValue("2");
        Assert.assertEquals(dropdownPage.getSelectedItemFromDropdown(), "Option 2");
    }

    @Test
    public void clickOnDropdownItemByArrayIndex() {
        DropdownPage dropdownPage = new WelcomePage(getDriver(), log)
                .openPage()
                .clickDropdownLink()
                .selectDropDownItemByValue("2");
        Assert.assertEquals(dropdownPage.getSelectedItemFromDropdown(), "Option 2");
    }
}