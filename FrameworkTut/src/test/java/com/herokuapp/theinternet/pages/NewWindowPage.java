package com.herokuapp.theinternet.pages;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class NewWindowPage extends BasePage{

    public NewWindowPage(WebDriver driver, Logger log) {
        super(driver, log);
    }

    public NewWindowPage switchToNewOpenedWindowUsingTitle(String newWindowTitle){
        log.info("Switching to the new window...");
        switchToNewWindowByTitle(newWindowTitle);
        return this;
    }

}