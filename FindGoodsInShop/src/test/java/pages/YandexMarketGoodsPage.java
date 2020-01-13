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

@Data
@Getter
@Setter
public class YandexMarketGoodsPage {
    private String key;
    private String xPathToRadioButtonDeliveryTerm =
            "//legend[contains(text(),'Срок доставки курьером')]/..//span[contains(text(),'%s')]/ancestor::label";
    private Double value;

    @FindBy(xpath = "//input[@id='header-search']")
    WebElement searchField;

    @FindBy(xpath = "//div[@id='product-628885067']//div[@class='price']")
    WebElement firstGood;

    @FindBy(xpath = "//fieldset[@data-autotest-id='7893318']")
    WebElement manufacturerCheckboxesBlock;
    @FindBy(xpath = "//fieldset[@data-autotest-id='7893318']//span[text()='Apple']")
    WebElement appleManufacturer;

    @FindBy(xpath = "//span[contains(text(),'16\"-16.9\"')]/../../input")
    WebElement screen16inchesBool;

    @FindBy(xpath = "//fieldset[@data-autotest-id='15083339']//span[text()='16\"-16.9\"']")
    WebElement screen16inchesCheckBox;

    @FindBy(xpath = "//span[contains(text(),'До 5 дней')]/ancestor::label")
    WebElement radioButton5Days;

    YandexMarketGoodsPage searchTextGood(String searchableGood) {
        searchField.sendKeys(searchableGood);
        searchField.submit();
        return this;
    }

    public YandexMarketGoodsPage clickOnManufacturerCheckBox() {
        appleManufacturer.click();
        return this;
    }

    public YandexMarketGoodsPage clickOnScreensSizeCheckBox() {
        if (!screen16inchesBool.isSelected()) {
            screen16inchesCheckBox.click();
        }
        return this;
    }

    public YandexMarketGoodsPage getTheCheapestProduct() {
        setKey(firstGood.findElement(By.xpath("..")).getAttribute("href") + " ");
        setValue(Double.valueOf(firstGood.getText().trim().replaceAll("[^0-9]", "")));
        return this;
    }

    public YandexMarketGoodsPage initPage(WebDriver driver, int WAITSEC) {
        new WebDriverWait(driver, WAITSEC)
                .until(ExpectedConditions
                        .elementToBeClickable(By.xpath("//div[@id='product-628885067']//div[@class='price']")));
        PageFactory.initElements(driver, this);
        return this;
    }

    public YandexMarketGoodsPage chooseDeliveryTerm(String radioButtonName){
        if (!radioButton5Days.isSelected()) {
            radioButton5Days.click();
        }
        return this;
    }

    public YandexMarketGoodsPage chooseDeliveryTermByName(String radioButtonName, WebDriver driver){
        if (!driver.findElement(By.xpath(String.format(xPathToRadioButtonDeliveryTerm, radioButtonName))).isSelected()) {
            driver.findElement(By.xpath(String.format(xPathToRadioButtonDeliveryTerm, radioButtonName))).click();
        }
        return this;
    }
}