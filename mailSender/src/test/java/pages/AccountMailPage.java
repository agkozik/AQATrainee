package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AccountMailPage {
    WebDriver driver;

    @FindBy(className = "js-main-action-compose")
    WebElement createMailButton;

    public AccountMailPage(WebDriver driver) {
        this.driver = driver;
        new WebDriverWait(driver,5)
                .until(ExpectedConditions.elementToBeClickable(By.className("js-main-action-compose")));
        PageFactory.initElements(driver, this);
    }

    public void clickCreateMail() {
        createMailButton.click();
    }

    public AccountMailPage init(WebDriver driver) {
        PageFactory.initElements(driver, this);
        return this;
    }

    public WebElement getCreateMailButton() {
        return createMailButton;
    }
}