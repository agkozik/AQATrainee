package engine;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestContext;
import org.testng.annotations.*;

import java.net.MalformedURLException;

public class BaseTest {
    protected WebDriver driver;
    protected Logger log;

    @BeforeMethod(alwaysRun = true)
    @Parameters({"browser"})
    public void setUp (@Optional("chrome") String browser, ITestContext ctx) throws MalformedURLException {
        String testName=ctx.getCurrentXmlTest().getName();
        log= LogManager.getLogger(testName);
        BrowserDriverFactory factory = new BrowserDriverFactory(browser, log);
        driver=factory.createDriver();
        log.info("Create Driver "+browser+" Base.class");
        driver.manage().window().maximize();
    }

    @AfterMethod(alwaysRun = true)
    public void closeBrowser(){
        log.info("Close driver");
        driver.quit();
    }
}