import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Tuturial {
    public static void main(String[] args) {
        //System.setProperty("webdriver.gecko.driver","c:\\webdrivers\\geckodriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://market.yandex.by/");
        WebElement searchField = driver.findElement(By.xpath("//input[@id='header-search']"));
        searchField.sendKeys("Macbook Pro 16");
        searchField.submit();
        System.out.println(driver.getTitle());
        new WebDriverWait(driver, 10)
                .until(ExpectedConditions
                        .presenceOfElementLocated(By
                                .xpath("//fieldset[@data-autotest-id='7893318']//legend[text()='Производитель']")));
        new WebDriverWait(driver, 10)
                .until(ExpectedConditions
                        .presenceOfElementLocated(By
                                .xpath("//fieldset[@data-autotest-id='15083339']//legend[text()='Размер экрана']")));
        WebElement manufacturer = driver
                .findElement(By
                        .xpath("//fieldset[@data-autotest-id='7893318']//span[text()='Apple']"));
        manufacturer.click();
        WebElement checkBoxSize = driver
                .findElement(By
                        .xpath("//fieldset[@data-autotest-id='15083339']//span[text()='16\"-16.9\"']"));
        checkBoxSize.click();
        WebElement searchParam = driver.findElement(By.xpath("//div[@class='n-filter-block_pos_left i-bem']//a[text()='по цене']"));
searchParam.click();

        try {
            Thread.sleep(5000);
        } catch (
                InterruptedException e) {
            e.printStackTrace();
        }
        driver.quit();
    }
}