import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.*;

import java.awt.*;
import java.util.*;

public class MacbookPro16PriceParser {

    private WebDriver driver;
    private final String URL = "https://market.yandex.by/";
    private final String URL2 = "https://shop.by/";
    private final int WAITSEC = 10;
    private String searchableGood = "MacBook Pro 16";
    private String listItem = "С дешевых";
    Map<String, Double> dataLinkPrice = new TreeMap<>();

    @BeforeMethod
    void getBrowserInstance() {
        if (driver == null) {
            //System.setProperty("webdriver.chrome.driver", "src\\test\\resources\\chromedriver.exe");
            driver = new ChromeDriver();
        }
        driver.manage().window().maximize();
    }

    @BeforeMethod
    void hideMousePointer() throws AWTException {
        Robot bot = new Robot();
        bot.mouseMove(0, 0);
    }

    @Test
    void findTheMostCheapestGoodFromYandexAndAddItToMap() {
        driver.get(URL);
        YandexMarketGoodsPage yandexMarketGoodsPage = new YaMarketPage(driver, WAITSEC)
                .searchTextGood(searchableGood)
                .initPage(driver, WAITSEC)
                .clickOnManufacturerCheckBox()
                .clickOnScreensSizeCheckBox()
                .getTheCheapestProduct();
        dataLinkPrice.put(yandexMarketGoodsPage.getKey(), yandexMarketGoodsPage.getValue());
    }

    @Test(dataProvider = "data-provider", dataProviderClass = DataProviderClass.class)
    void findTheMostCheapestGoodFromShopByAndAddItToMap(String data){
        driver.get(URL2);
        AllSellersForCurrentGood allSellersForCurrentGood=new ShopByPage(driver,WAITSEC)
                .sendKeyToSearchField(searchableGood)
                .clickSubmitSearch()
                .initShopByProductPage(driver,WAITSEC)
                .clickOnSortByButtonAndClickSortListItemByName(data)
                .detectTheCheapestGoodAndClickOnIt(searchableGood,driver,WAITSEC)
                .clickAllSellers(driver,WAITSEC)
                .getBestPriceAndURL();
        dataLinkPrice.put(allSellersForCurrentGood.getKey(),allSellersForCurrentGood.getValue());
    }

    @AfterClass
    void printTheCheapestGoodPriceAndLink(){
        String keyMinValue="";
        System.out.println("Самая низкая цена:");
        //Получение минимального value в Map
        Double minPrice =Collections
                .min(dataLinkPrice.entrySet(), Comparator.comparingDouble(Map.Entry::getValue)).getValue();
        //Поиск ключа по значению в Map
        for (Map.Entry<String, Double> i:dataLinkPrice.entrySet()) {
            if (minPrice.equals(i.getValue())) {
                keyMinValue= i.getKey();// нашли наше значение и возвращаем  ключ
                break;
            }
        }
        System.out.println("\nЦена: " + minPrice+"\nСсылка на магазин: "+keyMinValue+"\n");
        System.out.println("----------------------------Все предложения----------------------------------------------");

        for (Map.Entry<String, Double> i:dataLinkPrice.entrySet()) {
            System.out.println("\nЦена: " + i.getValue()+"\nСсылка на магазин: "+i.getKey()+"\n");
        }
    }

    @AfterMethod
    void closeBrowser() {
        driver.quit();
        driver = null;
    }
}