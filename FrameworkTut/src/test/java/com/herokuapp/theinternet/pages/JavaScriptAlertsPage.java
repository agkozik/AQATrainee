package com.herokuapp.theinternet.pages;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class JavaScriptAlertsPage extends BasePage {

    By jsAlertButton =By.xpath("//button[contains(text(),'Click for JS Alert')]");
    By jsConfirmButton =By.xpath("//button[contains(text(),'Click for JS Confirm')]");
    By jsPromptButton =By.xpath("//button[contains(text(),'Click for JS Prompt')]");
    By messageResult=By.xpath("//p[@id='result']");

    public JavaScriptAlertsPage(WebDriver driver, Logger log) {
        super(driver, log);
    }

    public JavaScriptAlertsPage clickJSAlertButton(){
        click(jsAlertButton);
        return this;
    }

    public JavaScriptAlertsPage clickJSConfirmButton(){
        click(jsConfirmButton);
        return this;
    }

    public JavaScriptAlertsPage clickJSPromptButton(){
        click(jsPromptButton);
        return this;
    }

    /**
     * switch to Alert and get AlertMessage
     */
    public String getAlertText(){
        Alert alert = switchToAlert();
        String alertText=alert.getText();
        log.info("Alert text: "+alertText);
        return alertText;
    }

    /**
     * switch to Alert and get accept it
     */
    public JavaScriptAlertsPage acceptAlert(){
        Alert alert = switchToAlert();
        alert.accept();
        return this;
    }

    /**
     * switch to Alert and get dismiss it
     */
    public JavaScriptAlertsPage dismissAlert(){
        Alert alert = switchToAlert();
        alert.dismiss();
        return this;
    }

    /**
     * switch to Alert, send keys and accept it
     */
    public JavaScriptAlertsPage sendKeysToAlert (String keys){
        Alert alert = switchToAlert();
        alert.sendKeys(keys);
        return this;
    }

    public String getMessageFromResultField(){
       String text =find(messageResult).getText();
       log.info(text);
       return text;
    }
}