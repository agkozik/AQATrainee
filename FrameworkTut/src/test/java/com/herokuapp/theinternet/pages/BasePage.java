package com.herokuapp.theinternet.pages;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

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
     * Type text using actions
     */
    public void pressKeysWithActions(Keys key){
        log.info("Press keys " + key+" using Actions");
        Actions actions = new Actions(driver);
        actions.sendKeys(key).build().perform();
    }


    public void pressKey(By locator,Keys key){
        find(locator).sendKeys(key);
    }

    /**
     * Select all Ctrl+a
     */
    public BasePage pressCtrlA(WebElement webElement){
        String action =Keys.chord(Keys.CONTROL,"a");
        webElement.sendKeys(action);
        return this;
    }

    /**
     * Select all Ctrl+x
     */
    public BasePage pressCtrlX(WebElement webElement){
        String action =Keys.chord(Keys.CONTROL,"x");
        webElement.sendKeys(action);
        return this;
    }

    /**
     * Paste Ctrl+v
     */
    public BasePage pressCtrlV(WebElement webElement){
        String action =Keys.chord(Keys.CONTROL,"v");
        webElement.sendKeys(action);
        return this;
    }

    /**
     * Copy Ctrl+c
     */
    public BasePage pressCtrlC(WebElement webElement){
        String action =Keys.chord(Keys.CONTROL,"c");
        webElement.sendKeys(action);
        return this;
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
        new WebDriverWait(driver,WAIT_IN_SEC)
                .until(ExpectedConditions.urlContains("the-internet.herokuapp.com"));
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

    /**
     * Scroll page to the bottom using JavaScript Executor
     */
    public void scrollToBottom(){
        log.info("Scrolling to the bottom...");
        JavascriptExecutor javascriptExecutor = (JavascriptExecutor) driver;
        javascriptExecutor.executeScript("window.scrollTo(0, document.body.scrollHeight)");
    }

}