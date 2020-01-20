package com.herokuapp.theinternet.pages;

import lombok.Getter;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.List;

@Getter
public class CheckboxesPage extends BasePage {

    By checkbox = By.xpath("//input[@type='checkbox']");

    public CheckboxesPage(WebDriver driver, Logger log) {
        super(driver, log);
    }

    public CheckboxesPage chooseCheckboxByText(String text) {
        List<WebElement> checkboxes = findAll(checkbox);
        for (WebElement i : checkboxes) {
            if (i.getText().equalsIgnoreCase(text) && (!i.isSelected()))
                i.click();
        }
        return this;
    }

    public CheckboxesPage chooseAllCheckboxes() {
        List<WebElement> checkboxes = findAll(checkbox);
        for (WebElement i : checkboxes) {
            if (!i.isSelected())
                i.click();
        }
        return this;
    }

    public CheckboxesPage unselectAllCheckboxes() {
        List<WebElement> checkboxes = findAll(checkbox);
        for (WebElement i : checkboxes) {
            if (i.isSelected())
                i.click();
        }
        return this;
    }

    public boolean areAllCheckboxesSelected() {
        List<WebElement> checkboxes = findAll(checkbox);
        for (WebElement i : checkboxes) {
            if (!i.isSelected()) {
                return false;
            }
        }
        return true;
    }

    public CheckboxesPage chooseCheckboxByIndex(int index) {
        String xpath = "//input[@type='checkbox'][%d]";
        WebElement iCheckbox = new WebDriverWait(driver, WAIT_IN_SEC)
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath(String.format(xpath, index))));
        if (!(iCheckbox).isSelected()) {
            iCheckbox.click();
        }
        return this;
    }

    public List<Boolean> returnSelectedCheckboxes(){
        List<WebElement> checkboxes = findAll(checkbox);
        List<Boolean> checkboxesStatus = new ArrayList<>();
        for (WebElement i : checkboxes) {
            if (i.isSelected()) {
                checkboxesStatus.add(true);
            }
            else{
                checkboxesStatus.add(false);
            }
        }
        return checkboxesStatus;
    }
}