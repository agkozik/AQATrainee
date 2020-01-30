package com.herokuapp.theinternet.dropDownTests;

import com.herokuapp.theinternet.pages.DropdownPage;
import com.herokuapp.theinternet.pages.WelcomePage;

import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import com.herokuapp.theinternet.testEngine.TestEngine;

@Listeners({com.herokuapp.theinternet.testEngine.TestListener.class})
public class DropDowntests extends TestEngine {

    @Test
    @Story("DropdownPage Test")
    @Description("click On DropDown Item")
    @Severity(SeverityLevel.MINOR)
    public void clickOnDropDownItem() {
        DropdownPage dropdownPage = new WelcomePage(getDriver(), log)
                .openPage()
                .clickDropdownLink()
                .clickOnDropDownField()
                .selectDropDownValueByText("Option 1");
        Assert.assertEquals(dropdownPage.getSelectedItemFromDropdown(), "Option 1");
    }

    @Test
    @Story("DropdownPage Test")
    @Description("click On Dropdown Item By using Value")
    @Severity(SeverityLevel.MINOR)
    public void clickOnDropdownItemByValue() {
        DropdownPage dropdownPage = new WelcomePage(getDriver(), log)
                .openPage()
                .clickDropdownLink()
                .selectDropDownItemByValue("2");
        Assert.assertEquals(dropdownPage.getSelectedItemFromDropdown(), "Option 2");
    }

    @Test
    @Story("DropdownPage Test")
    @Description("click On Dropdown Item By Array Index")
    @Severity(SeverityLevel.MINOR)
    public void clickOnDropdownItemByArrayIndex() {
        DropdownPage dropdownPage = new WelcomePage(getDriver(), log)
                .openPage()
                .clickDropdownLink()
                .selectDropDownItemByValue("2");
        Assert.assertEquals(dropdownPage.getSelectedItemFromDropdown(), "Option 2");
    }
}