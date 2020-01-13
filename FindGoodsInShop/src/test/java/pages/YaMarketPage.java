package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class YaMarketPage {
    @FindBy(xpath = "//input[@id='header-search']")
    WebElement searchField;

    public YaMarketPage(WebDriver driver, int WAITSEC) {
        new WebDriverWait(driver,WAITSEC)
                    .until(ExpectedConditions
                            .elementToBeClickable(By.xpath("//input[@id='header-search']")));
            PageFactory.initElements(driver,this);
        }

    public YandexMarketGoodsPage searchTextGood(String searchableGood){
        searchField.sendKeys(searchableGood);
        searchField.submit();
        return new YandexMarketGoodsPage();
    }
 }