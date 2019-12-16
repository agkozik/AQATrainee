import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.text.SimpleDateFormat;
import java.util.Date;

public class CreateMailPage {
    WebDriver driver;
    private final int WAIT_SEC = 25;

    private Date dateNow = new Date();
    private SimpleDateFormat formatForDateNow = new SimpleDateFormat("E dd.MM.yyyy ' время' hh:mm:ss a zzz");
    private String sendToAdress = "qareceivemail@yandex.by";
    private String mailSubject = "Проверка работы почтового фреймворка (тема письма)";
    private String tempString = ("Тело письма: 'Нет тела - нет дела.' \n"
            + formatForDateNow.format(dateNow)
            + "\nОтправлено c использованием Selenium WebDriver");
    private String mailBody = tempString;

    @FindBy(xpath = "//div[@class='mail-Compose-Field-Input']//div[@name='to']")
    WebElement sendtoField;
    @FindBy(xpath = "//input[@name='subj-de1095455d1c47938e244f3f312d07946394ad5e']")
    WebElement mailSubjectField;
    @FindBy(xpath = "//*[@id='cke_1_contents']/div")
    WebElement mailBodyField;
    @FindBy(xpath = "//div[@data-key='view=compose-notify-on-send-button']/label")
    WebElement notifyMeButton;
    @FindBy(xpath = "//div[@data-key='view=compose-send-button-complex']")
    WebElement sendMailButton;

    public CreateMailPage(WebDriver driver) {
        this.driver = driver;
        new WebDriverWait(driver, WAIT_SEC)
                .until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@data-key='view=compose-send-button-complex']")));
        PageFactory.initElements(driver, this);
    }

    public CreateMailPage enterAddressToSend() {
        sendtoField.sendKeys(sendToAdress);
        return this;
    }

    public CreateMailPage enterMailSubject() {
        mailSubjectField.sendKeys(mailSubject);
        return this;
    }

    public CreateMailPage enterMailBody() {
        mailBodyField.sendKeys(mailBody);
        return this;
    }

    public CreateMailPage clickNotifyMeButton() {
        notifyMeButton.click();
        return this;
    }

    public AccountMailPage clickSendMail() {
        sendMailButton.click();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if(driver.findElements(By.xpath("//*[@class='nb-popup _nb-modal-popup _init _nb-popup ui-dialog-content ui-widget-content']/div/a")).size()>0){
            driver.findElement(By.xpath("//*[@class='nb-popup _nb-modal-popup _init _nb-popup ui-dialog-content ui-widget-content']/div/a")).click();
        }
        return new AccountMailPage(driver);
    }
}