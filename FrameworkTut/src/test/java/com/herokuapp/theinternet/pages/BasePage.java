package com.herokuapp.theinternet.pages;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BasePage {
    protected WebDriver driver;
    protected Logger log;

    public BasePage(WebDriver driver, Logger log) {
        this.driver = driver;
        this.log = log;
    }

    /**
     * Open page by URL
     */
    protected void openUrl(String url) {
        driver.get(url);
    }

    /**
     * Find element by given locator
     */
    protected WebElement find(By locator) {
        return driver.findElement(locator);
    }

    /**
     * Click on Element with given locator when its visible
     */
    protected void click(By locator) {
        int WAIT_IN_SEC = 5;
        waitForVisibilityOf(locator, WAIT_IN_SEC);
        find(locator).click();
    }

    /**
     * Type given text into element with given locator (analog sendKeys)
     */
    protected void type(String text, By locator) {
        waitForVisibilityOf(locator);
        find(locator).sendKeys(text);
    }

    /**
     * Get Current URL from browser
     */
    public String getCurrentUrlFromBrowser() {
        return driver.getCurrentUrl();
    }

    /**
     * Wait for custom ExpectedCondition with ExplicitWait in seconds
     */
    protected void waitFor(ExpectedCondition<WebElement> condition, Integer timeOutInSeconds) {
        timeOutInSeconds = timeOutInSeconds != null ? timeOutInSeconds : 30;
        new WebDriverWait(driver, timeOutInSeconds).until(condition);
    }

    /**
     * Wait for given number of seconds (ExplicitWait visibilityOfElementLocated(locator))
     */
    protected void waitForVisibilityOf(By locator, Integer... timeOutInSeconds) {
        int attempts = 0;
        while (attempts < 2) {
            try {
                waitFor(ExpectedConditions.visibilityOfElementLocated(locator),
                        (timeOutInSeconds.length > 0 ? timeOutInSeconds[0] : null));
                break;
            } catch (StaleElementReferenceException e) {
                log.info(e.getLocalizedMessage());
            }
            attempts++;
        }
    }
}