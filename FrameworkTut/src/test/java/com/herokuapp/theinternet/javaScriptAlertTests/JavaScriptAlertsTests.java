package com.herokuapp.theinternet.javaScriptAlertTests;

import com.herokuapp.theinternet.pages.JavaScriptAlertsPage;
import com.herokuapp.theinternet.pages.WelcomePage;
import com.herokuapp.theinternet.testEngine.TestEngine;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
@Listeners({com.herokuapp.theinternet.testEngine.TestListener.class})
public class JavaScriptAlertsTests extends TestEngine {

    @Test
    public void jsAlertTextIsEqualsExpected() {
        String alertText = new WelcomePage(getDriver(), log)
                .openPage()
                .clickJavaScriptAlertLink()
                .clickJSAlertButton()
                .getAlertText();
                Assert.assertEquals(alertText,"I am a JS Alert");
    }

    @Test
    public void acceptJsAlert(){
        new WelcomePage(getDriver(), log)
                .openPage()
                .clickJavaScriptAlertLink()
                .clickJSAlertButton()
                .acceptAlert();
        WebDriverWait wait = new WebDriverWait(getDriver(), 2);
        Assert.assertTrue(wait.until(ExpectedConditions.not(ExpectedConditions.alertIsPresent())));
    }

    @Test
    public void dismissJsAlert(){
        new WelcomePage(getDriver(), log)
                .openPage()
                .clickJavaScriptAlertLink()
                .clickJSAlertButton()
                .dismissAlert();
        WebDriverWait wait = new WebDriverWait(getDriver(), 2);
        Assert.assertTrue(wait.until(ExpectedConditions.not(ExpectedConditions.alertIsPresent())));
    }

    @Test
    public void acceptJsConfirm(){
        new WelcomePage(getDriver(), log)
                .openPage()
                .clickJavaScriptAlertLink()
                .clickJSConfirmButton()
                .acceptAlert();
        WebDriverWait wait = new WebDriverWait(getDriver(), 2);
        Assert.assertTrue(wait.until(ExpectedConditions.not(ExpectedConditions.alertIsPresent())));
    }

    @Test
    public void dismissJsConfirm(){
        new WelcomePage(getDriver(), log)
                .openPage()
                .clickJavaScriptAlertLink()
                .clickJSConfirmButton()
                .dismissAlert();
        WebDriverWait wait = new WebDriverWait(getDriver(), 2);
        Assert.assertTrue(wait.until(ExpectedConditions.not(ExpectedConditions.alertIsPresent())));
    }

    @Test
    public void jsConfirmTextIsEqualsExpected() {
        String alertText = new WelcomePage(getDriver(), log)
                .openPage()
                .clickJavaScriptAlertLink()
                .clickJSConfirmButton()
                .getAlertText();
        Assert.assertEquals(alertText,"I am a JS Confirm");
    }

    @Test
    public void acceptJsPrompt(){
        new WelcomePage(getDriver(), log)
                .openPage()
                .clickJavaScriptAlertLink()
                .clickJSPromptButton()
                .acceptAlert();
        WebDriverWait wait = new WebDriverWait(getDriver(), 2);
        Assert.assertTrue(wait.until(ExpectedConditions.not(ExpectedConditions.alertIsPresent())));
    }

    @Test
    public void dismissJsPrompt(){
        new WelcomePage(getDriver(), log)
                .openPage()
                .clickJavaScriptAlertLink()
                .clickJSPromptButton()
                .dismissAlert();
        WebDriverWait wait = new WebDriverWait(getDriver(), 2);
        Assert.assertTrue(wait.until(ExpectedConditions.not(ExpectedConditions.alertIsPresent())));
    }

    @Test
    public void jsPromptIsEqualsExpected() {
        String alertText = new WelcomePage(getDriver(), log)
                .openPage()
                .clickJavaScriptAlertLink()
                .clickJSPromptButton()
                .getAlertText();
        Assert.assertEquals(alertText,"I am a JS prompt");
    }

    @Test
    public void sendKeyIntoJsPromptAlert(){
        JavaScriptAlertsPage javaScriptAlertsPage=new WelcomePage(getDriver(),log)
                .openPage()
                .clickJavaScriptAlertLink()
                .clickJSPromptButton()
                .sendKeysToAlert("It means peace among worlds .|.. ..|.")
                .acceptAlert();
        Assert.assertTrue(javaScriptAlertsPage.getMessageFromResultField().contains(".|.."));
    }
}