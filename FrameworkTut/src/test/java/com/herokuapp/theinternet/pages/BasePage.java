package com.herokuapp.theinternet.pages;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.Iterator;
import java.util.List;
import java.util.Set;

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
    public void openUrl(String url) {
        driver.get(url);
    }

    /**
     * Find element by given locator find=driver.findElement(locator)
     */
    public WebElement find(By locator) {
        log.info("Find element by locator "+locator);
        return new WebDriverWait(driver,WAIT_IN_SEC)
                .until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    /**
     * Find all  elements (List<WebElement>) by given locator findAll=driver.findElementS(locator)
     */
    public List<WebElement> findAll(By locator){
        log.info("Find element by locator "+locator);
        new WebDriverWait(driver,WAIT_IN_SEC)
                .until(ExpectedConditions.visibilityOfElementLocated(locator));
        return driver.findElements(locator);
    }

    /**
     * Click on Element with given locator when its visible
     */
    public void click(By locator) {
        waitForVisibilityOf(locator, WAIT_IN_SEC);
        find(locator).click();
    }

    /**
     * Type given text into element with given locator (analog sendKeys)
     */
    public void type(String text, By locator) {
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
     * Get current Page Title, from current page
     */
    public String getCurrentPageTitle(){
        return driver.getTitle();
    }

    /**
     * Get current PageSource, from current page
     */
    public String getCurrentPageSource(){
        return driver.getPageSource();
    }

    /**
     * Wait for custom ExpectedCondition with ExplicitWait in seconds
     */
    public void waitFor(ExpectedCondition<WebElement> condition, Integer timeOutInSeconds) {
        timeOutInSeconds = timeOutInSeconds != null ? timeOutInSeconds : 30;
        new WebDriverWait(driver, timeOutInSeconds).until(condition);
    }

    /**
     * Wait for given number of seconds (ExplicitWait visibilityOfElementLocated(locator))
     */
    public void waitForVisibilityOf(By locator, Integer... timeOutInSeconds) {
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
     */
    public Alert switchToAlert(){
        WebDriverWait wait = new WebDriverWait(driver,WAIT_IN_SEC);
                wait.until(ExpectedConditions.alertIsPresent());
        return driver.switchTo().alert();
    }

    /**
     * Switch to new window in browser by title
     */
    public void switchToNewWindowByTitle(String expectedTitle){
        String firstWindow = driver.getWindowHandle();
        Set<String> allWindows = driver.getWindowHandles();

        for (String iWindow : allWindows) {
            if (!(iWindow.equals(firstWindow))) {
                driver.switchTo().window(iWindow);
                if (driver.getTitle().equals(expectedTitle)) {
                    break;
                }
            }
        }
    }

    /**
     * Switch To Frame by locator
     */
    public void switchToFrameByLocator(By locator){
        driver.switchTo().frame(find(locator));
    }

}