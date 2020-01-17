import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;
import org.testng.annotations.*;

import java.net.MalformedURLException;
import java.net.URL;

public class TestDropDownClick {
    private final String URL = "https://www.facebook.com/";
    private final int WAIT_TIME = 20;

    //ThreadLocal will keep local copy of driver
    public static ThreadLocal<RemoteWebDriver> dr = new ThreadLocal<>();

    @BeforeMethod
    //Parameter will get browser from testng.xml on which browser test to run
    @Parameters("myBrowser")
    public void beforeClass(String myBrowser) throws MalformedURLException {
        RemoteWebDriver driver = null;
        if (myBrowser.equals("chrome")) {
            DesiredCapabilities capability = DesiredCapabilities.chrome();
            capability.setBrowserName("chrome");
            capability.setPlatform(Platform.WINDOWS);
            driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), capability);
        } else if (myBrowser.equals("firefox")) {
            DesiredCapabilities capability = DesiredCapabilities.chrome();
            capability.setBrowserName("firefox");
            capability.setPlatform(Platform.WINDOWS);
            driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), capability);
        }
        setWebDriver(driver);
        getDriver().manage().window().maximize();
    }

    public WebDriver getDriver() {
        return dr.get();
    }

    public void setWebDriver(RemoteWebDriver driver) {
        dr.set(driver);
    }

    @Test(dataProvider = "days", dataProviderClass = DataProviderClass.class)
    public void chooseDayOfBirthdayDropDown(String field, String value) {
        getDriver().get(URL);
        String actualValueOfField = new MainPage(dr.get(), WAIT_TIME)
                .clickByFieldDay().chooseValueFromDropdown(field, value);
        Assert.assertEquals(actualValueOfField, value);
    }

    @Test(dataProvider = "month", dataProviderClass = DataProviderClass.class)
    public void chooseMonthOfBirthdayDropDown(String field, String value) {
        getDriver().get(URL);
        String actualValueOfField = new MainPage(dr.get(), WAIT_TIME)
                .clickByFieldMonth().chooseValueFromDropdown(field, value);
        Assert.assertEquals(actualValueOfField, value);
    }

    @Test(dataProvider = "years", dataProviderClass = DataProviderClass.class)
    public void chooseYearOfBirthdayDropDown(String field, String value) {
        getDriver().get(URL);
        String actualValueOfField = new MainPage(dr.get(), WAIT_TIME)
                .clickByFieldYear().chooseValueFromDropdown(field, value);
        Assert.assertEquals(actualValueOfField, value);
    }

    @Test()
    public void clickByDate() {
        getDriver().get(URL);
        new MainPage(dr.get(), WAIT_TIME)
                .clickByFieldDay();
    }

    @Test()
    public void clickByMonth() {
        getDriver().get(URL);
        new MainPage(dr.get(), WAIT_TIME)
                .clickByFieldMonth();
    }

    @Test()
    void clickByYear() {
        getDriver().get(URL);
        new MainPage(dr.get(), WAIT_TIME)
                .clickByFieldYear();
    }

    @AfterMethod
    public void afterClass() {
        getDriver().quit();
        dr.set(null);
    }
}