import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class OutgoingMailsPage {
    private WebDriver driver;
    private final int WAIT_SEC=25;

    @FindBy(xpath = "//div[contains(@class,'ns-view-messages-item-wrap')][1]//a")
    WebElement listOutgoingMails;

    public OutgoingMailsPage(WebDriver driver) {
        this.driver = driver;
        new WebDriverWait(driver,WAIT_SEC)
                .until(ExpectedConditions.titleContains("Отправленные"));
        PageFactory.initElements(driver, this);
    }

    public ViewOutgoingMailPage clickOnLastOutgoingMail(){
        listOutgoingMails.click();
        return new ViewOutgoingMailPage(driver);
    }
}