package turboEngine;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestContext;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.apache.logging.log4j.Logger;
import java.net.MalformedURLException;
import java.net.URL;

public class TestEngine {

    //ThreadLocal will keep local copy of driver
    public static ThreadLocal<RemoteWebDriver> dr = new ThreadLocal<>();
    protected Logger log;

    @BeforeMethod
    //Parameter will get browser from testng.xml on which browser test to run
    @Parameters("browser")
    public void beforeClass(String browser, ITestContext ctx) throws MalformedURLException {
        String testName=ctx.getCurrentXmlTest().getName();
        log= LogManager.getLogger(testName);
        RemoteWebDriver driver = null;
        if (browser.equals("chrome")) {
            DesiredCapabilities capability = DesiredCapabilities.chrome();
            capability.setBrowserName("chrome");
            capability.setPlatform(Platform.WINDOWS);
            driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), capability);
        } else if (browser.equals("firefox")) {
            DesiredCapabilities capability = DesiredCapabilities.firefox();
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

    @AfterMethod
    public void afterClass() {
        getDriver().quit();
        dr.set(null);
    }
}