package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AccountMailPage {
    private WebDriver driver;
    private final int WAIT_SEC=25;

    @FindBy(className = "js-main-action-compose")
    WebElement createMailButton;

    @FindBy(xpath = "//a[@data-params='fid=1']")
    WebElement inboxMailButton;

    @FindBy(xpath = "//a[@data-params='fid=4']")
    WebElement outgoingMail;

    public AccountMailPage(WebDriver driver) {
        this.driver = driver;
        new WebDriverWait(driver,WAIT_SEC)
                .until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@data-params='fid=4']")));
        PageFactory.initElements(driver, this);
    }

    public CreateMailPage clickCreateMail() {
        createMailButton.click();
        return new CreateMailPage(driver);
    }

    public AccountMailPage clickOnInboxButton(){
        inboxMailButton.click();
        return this;
    }

    public OutgoingMailsPage clickOnOutgoingButton(){
        outgoingMail.click();
        return new OutgoingMailsPage(driver);
    }

}