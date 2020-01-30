package com.herokuapp.theinternet.pages;

import io.qameta.allure.Step;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;

public class JavaScriptErrorPage extends BasePage {

    private String url="http://the-internet.herokuapp.com/javascript_error";

    public JavaScriptErrorPage(WebDriver driver, Logger log) {
        super(driver, log);
    }

    @Step
    public JavaScriptErrorPage openPage(){
        openUrl(url);
        return this;
    }
}