package com.herokuapp.theinternet.pages;

import io.qameta.allure.Step;
import lombok.Getter;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

@Getter
public class LoginPage extends BasePage {

    public String pageURL = "http://the-internet.herokuapp.com/login";

    private By fieldUserName = By.xpath("//input[@id='username']");
    private By fieldUserPassword = By.xpath("//input[@id='password']");
    private By loginButton = By.xpath("//button[@class='radius']");
    private By errorMessageLocator = By.xpath("//div[@id='flash']");

    private String errorMessage = "Your username is invalid!";

    public LoginPage(WebDriver driver, Logger log) {
        super(driver, log);
    }

    @Step
    public LoginPage openPage(){
        openUrl(pageURL);
        return this;
    }

    @Step
    public LoginPage enterUserNameAndPassword(String userName, String password) {
        log.info("Typing user=" + userName + " , password=" + password);
        type(userName, fieldUserName);
        type(password, fieldUserPassword);
        return this;
    }

    @Step
    public SecureAreaPage clickLoginButton() {
        log.info("Click on LogIN Button");
        click(loginButton);
        log.info("Go to new page " + driver.getCurrentUrl());
        return new SecureAreaPage(driver, log);
    }

    /**
     * Wait for error message to be visible on the page
     */
    @Step
    public void waitForErrorMessage() {
        waitForVisibilityOf(errorMessageLocator, 5);
    }

    @Step
    public String getTextErrorMessageFromPage() {
        return find(errorMessageLocator).getText();
    }

    public String getColor(){
        String buttonColor = driver.findElement(By.xpath("//button[@class='radius']"))
                .getCssValue("color");
        return buttonColor;

    }
}