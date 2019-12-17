import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.*;

import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.testng.Assert.assertTrue;
/*
Задача:
1) Залогаться в почтовом ящике
2) НАписать письмо, указав кому, тему и тело(любой шаблон, который содержит дату и время, подпись отправителя)
3) Проверить что письмо отправлено
*/
public class StartTestWithTestNG {
    private WebDriver driver;
    private final String URL = "https://yandex.by";
    private Date dateNow = new Date();
    private SimpleDateFormat formatForDateNow = new SimpleDateFormat("E dd.MM.yyyy");
    private String stringToCompare = ("Тело письма: 'Нет тела - нет дела.'\n" + formatForDateNow.format(dateNow));

    @BeforeMethod
    void getBrowserInstance() {
        if (driver == null) {
            System.setProperty("webdriver.chrome.driver", "src\\test\\resources\\chromedriver.exe");
            driver = new ChromeDriver();
        }
        driver.manage().window().maximize();
        driver.get(URL);
    }

    @BeforeMethod
    void hideMousePointer() throws AWTException {
        Robot bot = new Robot();
        bot.mouseMove(0, 0);
    }

    @Test(priority = 1)
    void sendMail() {
        MailPage mailPage = new MailPage(driver);
        mailPage.clickSignIn()
                .enterLogin()
                .clickLoginNextButton()
                .enterPassword()
                .clickPasswordNextButton()
                .clickCreateMail()
                .enterAddressToSend()
                .enterMailSubject()
                .enterMailBody()
                .clickSendMail();
    }

    @Test(priority = 2)
    void checkIfMailWasSent() {
        MailPage mailPage = new MailPage(driver);
        String list = mailPage.clickSignIn()
                .enterLogin()
                .clickLoginNextButton()
                .enterPassword()
                .clickPasswordNextButton()
                .clickOnOutgoingButton()
                .clickOnLastOutgoingMail()
                .getMailBodyContent().getText();
        assertTrue(list.contains(stringToCompare), "\nExpected: \n" + stringToCompare + "\nActual: \n" + list);
    }

    @AfterMethod
    void closeBrowser() {
        driver.quit();
        driver = null;
    }
}