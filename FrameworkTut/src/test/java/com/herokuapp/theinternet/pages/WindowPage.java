package com.herokuapp.theinternet.pages;

import io.qameta.allure.Step;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class WindowPage extends BasePage {

    private By clickHereLink = By.linkText("Click Here");

    public WindowPage(WebDriver driver, Logger log) {
        super(driver, log);
    }

    @Step
    public NewWindowPage clickOpenNewWindow() {
        click(clickHereLink);
        return new NewWindowPage(driver, log);
    }

    @Step
    public WindowPage switchToNewWindowAndBack() {
        //remember first window
        String firstWindow = driver.getWindowHandle();
        click(clickHereLink);
        //switch to the new window
        for (String i : driver.getWindowHandles()) {
            driver.switchTo().window(i);
        }
        //return to the first window
        driver.switchTo().window(firstWindow);
        return this;
    }
}