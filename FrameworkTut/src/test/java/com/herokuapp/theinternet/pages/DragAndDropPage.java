package com.herokuapp.theinternet.pages;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class DragAndDropPage extends BasePage {

    String url = "http://the-internet.herokuapp.com/drag_and_drop";

    By elementA = By.id("column-a");
    By elementB = By.id("column-b");

    public DragAndDropPage(WebDriver driver, Logger log) {
        super(driver, log);
    }

    public WebElement getElementA(){
        return find(elementA);
    }

    public WebElement getElementB(){
        return find(elementB);
    }

    public DragAndDropPage openPage() {
        log.info("Opening page "+url);
        openUrl(url);
        return this;
    }

    /**
     * May not work on html pages
     */
    public DragAndDropPage dragAndDropUsingActions() {
        log.info("drag And Drop Using actions.dragAndDrop(find(elementA), find(elementB))");
        Actions actions = new Actions(driver);
        actions.dragAndDrop(find(elementA), find(elementB)).build().perform();
        return this;
    }

    public DragAndDropPage clickAndHoldAAndMoveToB(){
        log.info("drag And Drop Using actions.");
        Actions actions = new Actions(driver);
        actions.clickAndHold(find(elementA)).moveToElement(find(elementB)).release().build().perform();
        return this;
    }

    /**
     * Works on different pages
     */
    public DragAndDropPage dragAndDropUsingJavascript(WebElement from, WebElement to) {
        log.info("drag And Drop Using JavascriptExecutor");
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("function createEvent(typeOfEvent) {\n" + "var event =document.createEvent(\"CustomEvent\");\n"
                + "event.initCustomEvent(typeOfEvent,true, true, null);\n" + "event.dataTransfer = {\n" + "data: {},\n"
                + "setData: function (key, value) {\n" + "this.data[key] = value;\n" + "},\n"
                + "getData: function (key) {\n" + "return this.data[key];\n" + "}\n" + "};\n" + "return event;\n"
                + "}\n" + "\n" + "function dispatchEvent(element, event,transferData) {\n"
                + "if (transferData !== undefined) {\n" + "event.dataTransfer = transferData;\n" + "}\n"
                + "if (element.dispatchEvent) {\n" + "element.dispatchEvent(event);\n"
                + "} else if (element.fireEvent) {\n" + "element.fireEvent(\"on\" + event.type, event);\n" + "}\n"
                + "}\n" + "\n" + "function simulateHTML5DragAndDrop(element, destination) {\n"
                + "var dragStartEvent =createEvent('dragstart');\n" + "dispatchEvent(element, dragStartEvent);\n"
                + "var dropEvent = createEvent('drop');\n"
                + "dispatchEvent(destination, dropEvent,dragStartEvent.dataTransfer);\n"
                + "var dragEndEvent = createEvent('dragend');\n"
                + "dispatchEvent(element, dragEndEvent,dropEvent.dataTransfer);\n" + "}\n" + "\n"
                + "var source = arguments[0];\n" + "var destination = arguments[1];\n"
                + "simulateHTML5DragAndDrop(source,destination);", from, to);
        return this;
    }

    public String getTextFromLeftElement(){
        return find(elementA).getText();
    }

    public String getTextFromRightElement(){
        return find(elementB).getText();
    }
}