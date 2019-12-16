import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.Scanner;

public class PasswordPage {
    private WebDriver driver;
    private final int WAIT_SEC=25;
    private static String PASSWORD = "qareceivemail111111";

    @FindBy(className= "passp-sign-in-button")
    WebElement signInButton;
    @FindBy(id = "passp-field-passwd")
    WebElement passwordField;

    public PasswordPage(WebDriver driver) {
        this.driver = driver;
        new WebDriverWait(driver,WAIT_SEC)
                .until(ExpectedConditions.elementToBeClickable(By.id("passp-field-passwd")));
        PageFactory.initElements(driver, this);
    }

    public PasswordPage enterPassword() {
        passwordField.sendKeys(PASSWORD);
        return this;
    }

    public AccountMailPage clickPasswordNextButton() {
        signInButton.click();
        return new AccountMailPage(driver);
    }
}