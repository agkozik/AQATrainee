package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ShopByPage {

    @FindBy(xpath = "//input[@class='Header__FindTxtInput']")
    WebElement searchField;

    public ShopByPage(WebDriver driver, int wait) {
        new WebDriverWait(driver, wait)
                .until(ExpectedConditions
                        .presenceOfElementLocated(By.xpath("//input[@class='Header__FindTxtInput']")));
        PageFactory.initElements(driver, this);
    }

    public ShopByPage sendKeyToSearchField(String keyString) {
        searchField.sendKeys(keyString);
        return this;
    }

    public ShopBySearchProductPage clickSubmitSearch() {
        searchField.submit();
        return new ShopBySearchProductPage();
    }
}