import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.io.FileHandler;
import org.testng.reporters.Files;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ExampleWorkWithWindows {
    static WebDriver driver;
    private static Files FileUtils;

    public static void main(String[] args) throws IOException {
        /* запуск браузера в режиме без UI
        ChromeOptions options = new ChromeOptions();
        options.setHeadless(true);
        driver = new ChromeDriver(options);
        */
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

            takeScreenshot();
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

    public static void takeScreenshot() throws IOException {
        File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        Date dataNow = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("hh_mm_ss");
        String methodName = new Object() {
        }.getClass().getEnclosingMethod().getName();
        String dynamicNameForScreenshot = simpleDateFormat.format(dataNow) + methodName + ".png";
        FileHandler.copy(screenshot, new File("c:\\tmp\\" + dynamicNameForScreenshot));
    }
}