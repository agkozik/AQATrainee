package com.herokuapp.theinternet.pages;

import lombok.Getter;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.yandex.qatools.allure.annotations.Step;

@Getter
public class DropdownPage extends BasePage {

    By dropDownField = By.xpath("//select[@id='dropdown']");

    public DropdownPage(WebDriver driver, Logger log) {
        super(driver, log);
    }

    @Step
    public DropdownPage clickOnDropDownField() {
        click(dropDownField);
        return this;
    }

    @Step
    public DropdownPage selectDropDownValueByText(String text) {
        String xPath = "//option[contains(text(),'%s')]";
        new WebDriverWait(driver, WAIT_IN_SEC)
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath(String.format(xPath, text))))
                .click();
        return this;
    }

    @Step
    public DropdownPage selectDropDownItemByValue(String itemValue) {
        Select dropDownItem = new Select(find(dropDownField));
        dropDownItem.selectByValue(itemValue);
        return this;
    }

    @Step
    public DropdownPage selectDropDownItemByIndex(int itemIndex) {
        Select dropDownItem = new Select(find(dropDownField));
        dropDownItem.selectByIndex(itemIndex);
        return this;
    }

    @Step
    public String getSelectedItemFromDropdown() {
        Select dropDownItem = new Select(find(dropDownField));
        return dropDownItem.getFirstSelectedOption().getText();
    }
}