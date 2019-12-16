import jdk.nashorn.internal.ir.annotations.Ignore;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;


import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;

public class Main {
    private static WebDriver driver;
    private static final String URL = "https://yandex.by";

    @BeforeEach
    void hideMousePointer() throws AWTException {
        Robot bot = new Robot();
        bot.mouseMove(0, 0);
    }

    @BeforeAll
    static void getBrowserInstance(){
        if (driver == null){
            driver = new ChromeDriver();
        }
        driver.manage().window().maximize();
        driver.get(URL);
    }

    @Test
    @Ignore
    void sendMail(){
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
                .clickNotifyMeButton()
                .clickSendMail()
                .clickOnOutgoingButton()
                .clickOnLastOutgoingMail();
    }

    @Test
    void checkIfMailWasSended(){
        MailPage mailPage = new MailPage(driver);
                String list = mailPage.clickSignIn()
                .enterLogin()
                .clickLoginNextButton()
                .enterPassword()
                .clickPasswordNextButton()
                .clickOnOutgoingButton()
                .clickOnLastOutgoingMail()
                .getMailBodyContent().getText();
        System.out.println(list);
        assertTrue(list.contains("16.12.2019"));
    }

    @AfterAll
    static void closeBrowser(){
        driver.quit();
        driver=null;
    }
}