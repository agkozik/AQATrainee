package pages;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CheapestProductPage {

    @FindBy (xpath = "//a[@data-modeltit='shop']")
    WebElement offersFromAllSellers;

    public CheapestProductPage(WebDriver driver, int wait) {
        new WebDriverWait(driver,wait)
                .until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[@itemprop='lowPrice']")));
        PageFactory.initElements(driver,this);
    }

    public AllSellersForCurrentGood clickAllSellers(WebDriver driver,int wait){
        offersFromAllSellers.click();
        return new AllSellersForCurrentGood(driver,wait);
    }
}