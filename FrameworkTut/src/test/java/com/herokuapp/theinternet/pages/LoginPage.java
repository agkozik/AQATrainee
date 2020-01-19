package com.herokuapp.theinternet.pages;

import lombok.Getter;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

@Getter
public class LoginPage extends BasePage {

    private By fieldUserName = By.xpath("//input[@id='username']");
    private By fieldUserPassword = By.xpath("//input[@id='password']");
    private By loginButton = By.xpath("//button[@class='radius']");
    private By divMessage = By.xpath("//div[@id='flash']");

    private String errorMessage = "Your username is invalid!";

    public LoginPage(WebDriver driver, Logger log) {
        super(driver, log);
    }

    public LoginPage enterUserNameAndPassword(String userName, String password) {
        log.info("Typing user="+userName+" , password="+password);
        type(userName, fieldUserName);
        type(password, fieldUserPassword);
        return this;
    }

    public SecureAreaPage clickLoginButton() {
        log.info("Click on LogIN Button");
        click(loginButton);
        log.info("Go to new page "+driver.getCurrentUrl());
        return new SecureAreaPage(driver, log);
    }

    public String getTextErrorMessageFromPage(){
        return find(divMessage).getText();
    }
}