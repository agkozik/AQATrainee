package com.herokuapp.theinternet.pages;

import lombok.Data;
import lombok.Getter;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

@Getter
public class WelcomePage extends BasePage {

    private String pageUrl = "http://the-internet.herokuapp.com/";

    private By formAuthLink =By.linkText("Form Authentication");

    public WelcomePage(WebDriver driver, Logger log) {
        super(driver, log);
    }

    public WelcomePage openPage(){
        log.info("Opening the MainPage "+ pageUrl);
        openUrl(pageUrl);
        log.info("Page "+pageUrl+" has been opened");
        return this;
    }

    public LoginPage clickFormAuthenticationLink(){
        log.info("Clicking on Form Authentication link from Welcome page");
        click(formAuthLink);
        return new LoginPage(driver,log);
    }
}