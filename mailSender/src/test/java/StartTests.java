import org.junit.Ignore;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertTrue;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class StartTests {
    private WebDriver driver;
    private static final String URL = "https://yandex.by";
    private Date dateNow = new Date();
    private SimpleDateFormat formatForDateNow = new SimpleDateFormat("E dd.MM.yyyy");
    private String stringToCompare = ("Тело письма: 'Нет тела - нет дела.'\n" + formatForDateNow.format(dateNow));

    @BeforeEach
    void getBrowserInstance() {
        if (driver == null) {
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

    @Test
    @Order(1)
    @Ignore
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
        assertTrue(list.contains(stringToCompare), "Expected: \n" + stringToCompare + "\nActual: \n" + list);
    }

    @AfterEach
    void closeBrowser() {
        driver.quit();
        driver = null;
    }
}