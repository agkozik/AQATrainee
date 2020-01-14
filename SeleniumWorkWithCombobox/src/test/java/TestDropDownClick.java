import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.awt.*;

public class TestDropDownClick {
    private static volatile WebDriver driver;
    private final String URL="https://www.facebook.com/";
    private final int WAIT_TIME=20;

    @BeforeMethod
    public static WebDriver getInstance() {
        WebDriver localDriver = driver;
        if (localDriver == null) {
            synchronized (TestDropDownClick.class) {
                localDriver = driver;
                if (localDriver == null) {
                    driver = localDriver = new ChromeDriver() {
                    };
                }   }  }
        driver.manage().window().maximize();
        return driver;
    }

    @BeforeMethod
    void hideMousePointer() throws AWTException {
        Robot bot = new Robot();
        bot.mouseMove(0, 0);
    }

    @Test(dataProvider = "data-provider", dataProviderClass = DataProviderClass.class)
    void chooseDayOfBirthdayDropDown(String field, String value){
        driver.get(URL);
        new MainPage(driver,WAIT_TIME).clickByFieldDay().chooseDayOfBirth(field, value);
        //Assert.assertEquals(value.getText(),day);
    }

    @AfterMethod
    void closeBrowser() {
        driver.quit();
        driver = null;
    }
}