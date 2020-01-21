package turboEngine;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestContext;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TestEngine {

    //ThreadLocal will keep local copy of driver
    public static ThreadLocal<RemoteWebDriver> dr = new ThreadLocal<>();
    protected Logger log;

    @BeforeMethod
    //Parameter will get browser from testng.xml on which browser test to run
    @Parameters("browser")
    public void beforeClass(@Optional("chrome")String browser, ITestContext ctx) throws MalformedURLException {
        String testName = ctx.getCurrentXmlTest().getName();
        log = LogManager.getLogger(testName);
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

    /**
     * Take screenshot with time and
     */
    public void takeScreenshot() throws IOException {
        File screenshot = ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.FILE);
        Date dataNow = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("hh_mm_ss");
        String dynamicNameForScreenshot = simpleDateFormat.format(dataNow) + ".png";
        FileHandler.copy(screenshot, new File("c:\\tmp\\" + dynamicNameForScreenshot));
    }
}