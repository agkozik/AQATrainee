package com.herokuapp.theinternet.pages;

import lombok.Getter;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;

@Getter
public class SecureAreaPage extends BasePage{
    String expectedUrl = "http://the-internet.herokuapp.com/secure";

    public SecureAreaPage(WebDriver driver, Logger log) {
        super(driver, log);
    }
}