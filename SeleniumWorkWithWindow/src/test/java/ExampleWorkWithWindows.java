import com.sun.deploy.cache.Cache;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.io.FileHandler;
import org.testng.reporters.Files;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

public class ExampleWorkWithWindows {
    static WebDriver driver;
    private static Files FileUtils;

    public static void main(String[] args) throws IOException {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://www.w3schools.com/sql/");

        //запомнить имя первого окна
        String firstWindow = driver.getWindowHandle();
        WebElement button1 = driver.findElement(By.xpath("//a[contains(text(),'Try it Yourself »')]"));
        WebElement searchButton = driver.findElement(By.xpath("//a[@title='Search W3Schools']"));

        searchButton.click();
        if (driver.findElement(By.xpath("//input[@title='search']")).isDisplayed()) {
            WebElement searchField = driver.findElement(By.xpath("//input[@title='search']"));
            searchField.sendKeys(Keys.chord(Keys.SHIFT, "Only English text?"));
            String selectAll = Keys.chord(Keys.CONTROL, "a");
            searchField.sendKeys(selectAll);

            File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            FileHandler.copy(screenshot, new File("c:\\English\\screenshot.png"));
        }
        button1.click();

        //переключение на открывшееся окно
        for (String i : driver.getWindowHandles()) {
            driver.switchTo().window(i);
        }

        WebElement button2 = driver.findElement(By.xpath("//button[contains(text(),'Run SQL »')]"));
        if (button2.isEnabled()) {
            button2.click();
        }

        //возврат на первое окно
        driver.switchTo().window(firstWindow);

        //проверка наличия элемента
        if (driver.findElements(By.xpath("//a[contains(text(),'Try it Yourself »')]")).size() > 0) {
            button1.click();
        }
        driver.quit();
    }
}