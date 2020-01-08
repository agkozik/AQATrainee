package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class ShopBySearchProductPage {
    @FindBy(xpath = "//span[@class='chzn-txt-sel']")
    WebElement sortByButton;

    @FindBy(xpath = "//span[@class='chzn-search']")
    WebElement sortByList;

    @FindBy(xpath = "//a[@class='ModelList__LinkModel']//span")
    List<WebElement> namesInTheListOfGoods;

    public ShopBySearchProductPage initShopByProductPage(WebDriver driver, int wait){
        new WebDriverWait(driver,wait)
                .until(ExpectedConditions
                        .presenceOfAllElementsLocatedBy(By.xpath("//a[@class='ModelList__LinkModel']//span")));
        PageFactory.initElements(driver,this);
        return this;
    }

    public ShopBySearchProductPage clickOnSortByButtonAndClickSortListItemByName(String name){
        sortByButton.click();
        WebElement tmp =sortByList
                .findElement(By.xpath("//ul[@class='chzn-results']//li[contains(text(),'"+name+"')]"));
        tmp.click();
        return this;
    }

    public CheapestProductPage detectTheCheapestGoodAndClickOnIt(String good, WebDriver driver, int wait) {
        for (WebElement i : namesInTheListOfGoods) {
            if (i.getText().contains(good)) {
                i.click();
                break;
            }
        }
        return new CheapestProductPage(driver,wait);
    }
}