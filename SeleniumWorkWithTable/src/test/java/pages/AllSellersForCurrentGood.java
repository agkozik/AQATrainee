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

import java.util.List;

@Getter
@Setter
public class AllSellersForCurrentGood {

    private String key;
    private Double value;

    @FindBy(xpath = "//div[@class='ModelList__ModelContentBlock ShopItemList__BlockItemInfo']")
    List<WebElement> linksToBuyAGood;


    public AllSellersForCurrentGood(WebDriver driver,int wait) {
        new WebDriverWait(driver,wait)
                .until(ExpectedConditions
                        .presenceOfElementLocated(By
                                .xpath("//div[@class='ShopItemList__DescBlockInfo ModelList__InfoModelBlock']")));
        PageFactory.initElements(driver,this);
    }

    public AllSellersForCurrentGood getBestPriceAndURL(){
        setKey(linksToBuyAGood
                .get(1)
                .findElement(By
                        .xpath("//a[@class='ShopItemList__ItemName Page__GALinkBlock']")).getAttribute("href"));
        setValue(Double.valueOf(linksToBuyAGood
                .get(1)
                .findElement(By.xpath("//span[@class='PriceBlock__PriceFirst']/span"))
                .getText()
                .trim()
                .replaceAll("[^0-9,]","")
                .replaceAll(",",".")));
        return this;
    }
}