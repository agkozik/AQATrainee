import com.google.common.collect.Table;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.List;

public class Main {
    static WebDriver driver;

    public static void main(String[] args) {
        //driver = new ChromeDriver();
        System.setProperty("webdriver.gecko.driver","c:\\webdrivers\\geckodriver.exe");
        driver = new FirefoxDriver();
        driver.manage().window().maximize();
        driver.get("https://www.w3schools.com/sql/sql_syntax.asp");

        WebElement tableElement = driver.findElement(By.cssSelector(".w3-table-all"));
        CustomTable table = new CustomTable(tableElement, driver);

        System.out.println("Table headers:");
        List<WebElement> tableHeaders = table.getHeaders();
        for (WebElement i:tableHeaders) {
            System.out.print(i.getText()+"\t");
        }
        System.out.println("\nRows number "+table.getRows().size());
        System.out.println("Row 1 Column 3 value = "+table.getValueFromCell(1,3));
        System.out.println(table.getValueFromCellByColumnName(4,"Address"));

        driver.quit();
    }
}