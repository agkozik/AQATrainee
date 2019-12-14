package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class MailPage {
    WebDriver driver;

    public MailPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }

    @FindBy(className = "desk-notif-card__login-enter-expanded")
    WebElement signInButton;

    public LoginPage clickSignIn() {
        signInButton.click();
        return new LoginPage(driver);
    }

}