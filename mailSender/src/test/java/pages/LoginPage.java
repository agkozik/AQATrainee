package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage {
    static String LOGIN = "testsendingmail";
    WebDriver driver;

    @FindBy(id= "passp-field-login")
    WebElement emailField;
    @FindBy(xpath = "//div[@class='passp-button passp-sign-in-button']")
    WebElement signInButton;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        new WebDriverWait(driver,5)
                .until(ExpectedConditions.elementToBeClickable(By.id("passp-field-login")));
        PageFactory.initElements(driver, this);
    }

    public LoginPage enterLogin() {
        emailField.sendKeys(LOGIN);
        return this;
    }

    public PasswordPage clickLoginNextButton() {
        signInButton.click();
        return new PasswordPage(driver);
    }
}