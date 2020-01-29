package com.herokuapp.theinternet.pages;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import ru.yandex.qatools.allure.annotations.Step;

public class HorizontalSliderPage extends BasePage {
    String url = "http://the-internet.herokuapp.com/horizontal_slider";

    By horizontalSlider = By.xpath("//input[@type='range']");
    By sliderCurrentValue = By.id("range");

    public HorizontalSliderPage(WebDriver driver, Logger log) {
        super(driver, log);
    }

    @Step
    public HorizontalSliderPage openPage() {
        openUrl(url);
        return this;
    }

    public String getCurrentValue() {
        return find(sliderCurrentValue).getText();
    }

    @Step
    public HorizontalSliderPage moveSliderToCurrentValue(double i) {
        int width = find(horizontalSlider).getSize().width;
        log.info("SliderWidth=" + width);
        double maxValueInSlider = Double.parseDouble(find(horizontalSlider).getAttribute("max"));
        log.info("maxValueInSlider=" + maxValueInSlider);
        double percent = i / maxValueInSlider;
        int valueToMove = (int) (width * percent);
        log.info("valueToMove=" + valueToMove);
        Actions actions = new Actions(driver);
        //actions.clickAndHold(find(horizontalSlider)).moveByOffset(valueToMove,0).release().build().perform();
        actions.dragAndDropBy(find(horizontalSlider), valueToMove, 0).build().perform();
        return this;
    }

    @Step
    public HorizontalSliderPage moveSliderToCurrentValueUsingSteps(double value) {
        int steps = (int) (value / Double.parseDouble(find(horizontalSlider).getAttribute("step")));
        pressKey(horizontalSlider, Keys.ENTER);
        for (int i = 0; i < steps; i++) {
            pressKey(horizontalSlider, Keys.ARROW_RIGHT);
        }
        return this;
    }
}