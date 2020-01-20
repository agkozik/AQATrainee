package com.herokuapp.theinternet.pages;

import lombok.Data;
import lombok.Getter;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

@Getter
public class WelcomePage extends BasePage {

    private String pageUrl = "http://the-internet.herokuapp.com/";

    private By authLink =By.linkText("Form Authentication");
    private By checkboxesLink =By.linkText("Checkboxes");

    public WelcomePage(WebDriver driver, Logger log) {
        super(driver, log);
    }

    public WelcomePage openPage(){
        log.info("Opening the MainPage "+ pageUrl);
        openUrl(pageUrl);
        log.info("Page "+pageUrl+" has been opened");
        return this;
    }

    /**
     * Open LogIn page by clicking on Form Authentication link in Welcome page
     * @return
     */
    public LoginPage clickFormAuthenticationLink(){
        log.info("Clicking on Form Authentication link from Welcome page");
        click(authLink);
        return new LoginPage(driver,log);
    }

    /**
     * Open checkboxes page by clicking on checkboxes link in Welcome page
     * @return
     */
    public CheckboxesPage clickCheckboxesLink(){
        log.info("Clicking on Checkboxes Link");
        click(checkboxesLink);
        log.info("Page "+driver.getCurrentUrl()+" has been opened");
        return new CheckboxesPage(driver,log);
    }

}