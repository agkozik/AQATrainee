import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertTrue;
/*
Задача:
1) Залогаться в почтовом ящике
2) НАписать письмо, указав кому, тему и тело(любой шаблон, который содержит дату и время, подпись отправителя)
3) Проверить что письмо отправлено
 */
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class StartTestsWithJUnit5 {
    private WebDriver driver;
    private static final String URL = "https://yandex.by";
    private Date dateNow = new Date();
    private SimpleDateFormat formatForDateNow = new SimpleDateFormat("E dd.MM.yyyy");
    private String stringToCompare = ("Тело письма: 'Нет тела - нет дела.'\n" + formatForDateNow.format(dateNow));

    @BeforeEach
    void getBrowserInstance() {
        if (driver == null) {
            System.setProperty("webdriver.chrome.driver","src\\test\\resources\\chromedriver.exe");
            driver = new ChromeDriver();
        }
        driver.manage().window().maximize();
        driver.get(URL);
    }

    @BeforeEach
    void hideMousePointer() throws AWTException {
        Robot bot = new Robot();
        bot.mouseMove(0, 0);
    }
    //@Disabled("Отключен, т.к яндекс банит за спам, разрешено около 5 емайлов за день \nЕсли необходимо запустить - закомментировать строку @Disable")
    @Test
    @Order(1)
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

    @Test
    @Order(2)
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

    @AfterEach
    void closeBrowser() {
        driver.quit();
        driver = null;
    }
}