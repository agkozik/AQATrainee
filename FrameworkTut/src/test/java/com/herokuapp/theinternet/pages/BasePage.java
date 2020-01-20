package com.herokuapp.theinternet.pages;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class BasePage {
    protected WebDriver driver;
    protected Logger log;
    protected int WAIT_IN_SEC = 5;

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
     * Find element by given locator find=driver.findElement(locator)
     */
    protected WebElement find(By locator) {
        log.info("Find element by locator "+locator);
        return new WebDriverWait(driver,WAIT_IN_SEC)
                .until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    /**
     * Find all  elements (List<WebElement>) by given locator findAll=driver.findElementS(locator)
     */
    protected List<WebElement> findAll(By locator){
        log.info("Find element by locator "+locator);
        new WebDriverWait(driver,WAIT_IN_SEC)
                .until(ExpectedConditions.visibilityOfElementLocated(locator));
        return driver.findElements(locator);
    }

    /**
     * Click on Element with given locator when its visible
     */
    protected void click(By locator) {
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

    /**
     * Wait for Alert and swith to it
     * @return
     */
    public Alert switchToAlert(){
        WebDriverWait wait = new WebDriverWait(driver,WAIT_IN_SEC);
                wait.until(ExpectedConditions.alertIsPresent());
        return driver.switchTo().alert();
    }
}