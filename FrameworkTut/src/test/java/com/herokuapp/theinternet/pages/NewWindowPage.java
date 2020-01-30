package com.herokuapp.theinternet.pages;

import io.qameta.allure.Step;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;

public class NewWindowPage extends BasePage {

    public NewWindowPage(WebDriver driver, Logger log) {
        super(driver, log);
    }

    @Step
    public NewWindowPage switchToNewOpenedWindowUsingTitle(String newWindowTitle) {
        log.info("Switching to the new window...");
        switchToNewWindowByTitle(newWindowTitle);
        return this;
    }

}