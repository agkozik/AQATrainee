import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MailPage {
    private WebDriver driver;
    private final int WAIT_SEC=25;
    @FindBy(className = "desk-notif-card__login-enter-expanded")
    WebElement signInButton;

    public MailPage(WebDriver driver) {
        this.driver = driver;
        new WebDriverWait(driver,WAIT_SEC).until(ExpectedConditions.elementToBeClickable(By.className("desk-notif-card__login-enter-expanded")));
        PageFactory.initElements(driver,this);
    }

    public LoginPage clickSignIn() {
        signInButton.click();
        return new LoginPage(driver);
    }
}