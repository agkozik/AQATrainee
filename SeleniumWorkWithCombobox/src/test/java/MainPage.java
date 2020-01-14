import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.json.JsonOutput;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MainPage {

    @FindBy (xpath = "//div[@id='birthday_wrapper']")
    WebElement dateOfBirthBlock;

    @FindBy(xpath = "//div[@id='birthday_wrapper']//select[@name='birthday_day']")
    WebElement dateOfBirthDay;

    @FindBy(xpath = "//div[@id='birthday_wrapper']//select[@name='birthday_month']")
    WebElement dateOfBirthMonth;

    @FindBy(xpath = "//div[@id='birthday_wrapper']//select[@name='birthday_year']")
    WebElement dateOfBirthYear;

    public MainPage(WebDriver driver, int waitTime) {
        new WebDriverWait(driver, waitTime)
                .until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//div[@id='birthday_wrapper']")));
        PageFactory.initElements(driver,this);
    }

    public MainPage clickByFieldDay(){
        dateOfBirthDay.click();
        return this;
    }

    public MainPage chooseDayOfBirth(String field, String value){
       String xPathToElementInDropDown="//select[@id='%s']//option[contains(text(),'%s')]";
       System.out.println(dateOfBirthBlock
               .findElement(By.xpath(String.format(xPathToElementInDropDown, field, value))).getText());
       dateOfBirthBlock.findElement(By.xpath(String.format(xPathToElementInDropDown, field, value))).click();
       return this;
   }
}