package com.herokuapp.theinternet.pages;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import ru.yandex.qatools.allure.annotations.Step;

public class WysiwygEditorPage extends BasePage {

    private By iFrame = By.tagName("iframe");
    private By textFieldInFrame = By.xpath("//body[@id='tinymce']");

    public WysiwygEditorPage(WebDriver driver, Logger log) {
        super(driver, log);
    }

    @Step
    public WysiwygEditorPage clickOnTextFieldAndTypeText(String text) {
        switchToFrameByLocator(iFrame);
        find(textFieldInFrame).clear();
        type(text, textFieldInFrame);
        return this;
    }
}