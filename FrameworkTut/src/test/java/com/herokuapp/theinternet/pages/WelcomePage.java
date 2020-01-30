package com.herokuapp.theinternet.pages;

import io.qameta.allure.Step;
import lombok.Getter;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

@Getter
public class WelcomePage extends BasePage {

    private String pageUrl = "http://the-internet.herokuapp.com/";

    private By authLink = By.linkText("Form Authentication");
    private By checkboxesLink = By.linkText("Checkboxes");
    private By dropDownLink = By.linkText("Dropdown");
    private By javaScriptAlertsLink = By.linkText("JavaScript Alerts");
    private By multipleWindowsLink = By.linkText("Multiple Windows");
    private By wysiwygEditorLink = By.linkText("WYSIWYG Editor");

    public WelcomePage(WebDriver driver, Logger log) {
        super(driver, log);
    }

    @Step("Open page")
    public WelcomePage openPage() {
        log.info("Opening the MainPage " + pageUrl);
        openUrl(pageUrl);
        log.info("Page " + pageUrl + " has been opened");
        return this;
    }

    /**
     * Open LogIn page by clicking on Form Authentication link in Welcome page
     */
    @Step
    public LoginPage clickFormAuthenticationLink() {
        log.info("Clicking on Form Authentication link from Welcome page");
        click(authLink);
        return new LoginPage(driver, log);
    }

    /**
     * Open checkboxes page by clicking on checkboxes link in Welcome page
     */
    @Step("Open checkboxes page by clicking on checkboxes link in Welcome page")
    public CheckboxesPage clickCheckboxesLink() {
        log.info("Clicking on Checkboxes Link");
        click(checkboxesLink);
        log.info("Page " + driver.getCurrentUrl() + " has been opened");
        return new CheckboxesPage(driver, log);
    }

    /**
     * Open Dropdown page by clicking on Dropdown link in Welcome page
     */
    @Step
    public DropdownPage clickDropdownLink() {
        log.info("Clicking Dropdown link");
        click(dropDownLink);
        log.info("Page " + driver.getCurrentUrl() + " has been opened");
        return new DropdownPage(driver, log);
    }

    /**
     * Open Dropdown page by clicking on JavaScriptAlert link in Welcome page
     */
    @Step
    public JavaScriptAlertsPage clickJavaScriptAlertLink() {
        log.info("Clicking JavaScriptAlert link");
        click(javaScriptAlertsLink);
        log.info("Page " + driver.getCurrentUrl() + " has been opened");
        return new JavaScriptAlertsPage(driver, log);
    }

    /**
     * Open WindowPage by clicking multipleWindows link
     */
    @Step
    public WindowPage clickMultipleWindowsLink() {
        log.info("Clicking on link");
        click(multipleWindowsLink);
        log.info("Page " + driver.getCurrentUrl() + " has been opened");
        return new WindowPage(driver, log);
    }

    /**
     * Open wysiwyg Editor (work with iFrame)
     */
    @Step
    public WysiwygEditorPage clickOnWysiwygEditorLink() {
        scrollToBottom();
        click(wysiwygEditorLink);
        return new WysiwygEditorPage(driver, log);
    }
}