package engine;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.Platform;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;

public class BrowserDriverFactory {
    private ThreadLocal<RemoteWebDriver> driver = new ThreadLocal<>();
    private String browser;
    private Logger log;

    public BrowserDriverFactory(String browser, Logger log) {
        this.browser = browser.toLowerCase();
        this.log=log;
    }

    public RemoteWebDriver createDriver() throws MalformedURLException {
        log.info("Creating driver "+browser+" in factory class.");

        switch(browser){
            case "chrome":
                DesiredCapabilities capability = DesiredCapabilities.chrome();
                capability.setBrowserName("chrome");
                capability.setPlatform(Platform.WINDOWS);
                driver.set(new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), capability));
                break;
            case "firefox":
                capability = DesiredCapabilities.firefox();
                capability.setBrowserName("firefox");
                capability.setPlatform(Platform.WINDOWS);
                driver.set(new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), capability));
                break;
            default:
                System.out.println("Driver?");
                capability = DesiredCapabilities.chrome();
                capability.setBrowserName("chrome");
                capability.setPlatform(Platform.WINDOWS);
                driver.set(new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), capability));
                break;
        }
        return driver.get();
    }
}
