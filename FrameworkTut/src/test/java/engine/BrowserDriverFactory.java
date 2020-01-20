package engine;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;

public class BrowserDriverFactory {
    protected ThreadLocal<RemoteWebDriver> driver = new ThreadLocal<>();
    private String browser;
    private Logger log;

    public BrowserDriverFactory(String browser, Logger log) {
        this.browser = browser.toLowerCase();
        this.log=log;
    }

    public WebDriver getDriver() {
        return driver.get();
    }

    public void setWebDriver(RemoteWebDriver dr) {
        driver.set(dr);
    }

    public WebDriver createDriver() throws MalformedURLException {
        log.info("BrowserDriverFactory Creating driver "+browser);
        RemoteWebDriver dr = null;
        switch(browser){
            case "chrome":
                DesiredCapabilities capability = DesiredCapabilities.chrome();
                capability.setBrowserName("chrome");
                capability.setPlatform(Platform.WINDOWS);
                dr=(new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), capability));
                break;
            case "firefox":
                DesiredCapabilities capability2 = DesiredCapabilities.firefox();
                capability2.setBrowserName("firefox");
                capability2.setPlatform(Platform.WINDOWS);
                dr=(new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), capability2));
                break;
            default:
                System.out.println("Driver?");
                capability = DesiredCapabilities.chrome();
                capability.setBrowserName("chrome");
                capability.setPlatform(Platform.WINDOWS);
                dr=(new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), capability));
                break;
        }
        setWebDriver(dr);
        return getDriver();
    }
}