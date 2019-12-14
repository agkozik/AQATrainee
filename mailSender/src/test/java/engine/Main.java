package engine;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import pages.MailPage;

public class Main {
    static WebDriver driver;
    private static final String URL = "https://yandex.by";

    public static void main(String[] args) {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get(URL);
        MailPage mailPage = new MailPage(driver);

        mailPage.clickSignIn()
                .enterLogin()
                .clickLoginNextButton()
                .enterPassword()
                .clickPasswordNextButton()
                .clickCreateMail();
    }
}