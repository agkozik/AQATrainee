import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.awt.*;
import java.util.List;

/*
Задача:
1) Залогаться в почтовом ящике
2) НАписать письмо, указав кому, тему и тело(любой шаблон, который содержит дату и время, подпись отправителя)
3) Проверить что письмо отправлено
Было бы не плохо завести 2 тестовые почты для будущего задания
так же надо будет покопать СI а именно дженкинс, что бы тесты ранились на нем
по технологиям - java8 selenium(last version), maven
*/
public class MailSender {
    private static final String URL = "https://www.google.com/intl/ru/gmail/about/";
    private static final int WAIT_TIMEOUT_SEC = 30;
    static String LOGIN = "sendmailtosendmailto@gmail.com";
    static String Password = "Selenium111111";
    static WDriverManager driverManager = WDriverManager.getInstance();

    @BeforeAll
    public static void moveCursorOut() throws AWTException {
        Robot bot = new Robot();
        bot.mouseMove(0, 0);
    }

    @Test
    void createTemp() {
        initPage();
    }

    @FindBy(className = "h-c-header__cta-list")
    private List<WebElement> rightHeaderMenuItems;

    public List<WebElement> getRightHeaderMenuItems() {
        return rightHeaderMenuItems;
    }

    public MailSender initPage() {
        driverManager.getDriver().get(URL);
        new WebDriverWait(driverManager.getDriver(), WAIT_TIMEOUT_SEC)
                .until(ExpectedConditions.elementToBeClickable(By.className("h-c-header__cta-list")));
        PageFactory.initElements(driverManager.getDriver(),this);
        return this;
    }

    @AfterAll
    static void browserClose() {
        driverManager.getDriver().quit();
        driverManager = null;
    }
}