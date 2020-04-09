package engine;

import com.codeborne.selenide.Configuration;
import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.logging.LogEntries;
import org.openqa.selenium.logging.LogEntry;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.*;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URI;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Set;

public class TestEngine {
    public static ThreadLocal<RemoteWebDriver> dr = new ThreadLocal<>();
    public String testSuiteName;
    public String testName;
    public String testMethodName;
    public Logger log;

    /**
     * Initialized driver using .xml, or Chrome by default
     */
    @BeforeMethod(alwaysRun = true)
    @Parameters("browser")
    public void initDriver(Method method, @Optional("internet explorer") String browser, ITestContext ctx) throws MalformedURLException {
        this.testSuiteName = ctx.getCurrentXmlTest().getSuite().getName();
        this.testName = ctx.getCurrentXmlTest().getName();
        this.testMethodName = ctx.getName();
        this.log = LogManager.getLogger(testName);

        RemoteWebDriver driver = null;

        if (browser.equals("chrome")) {
            Configuration.remote = "http://localhost:4444/wd/hub";
            Configuration.browser = "chrome";
            Configuration.browserSize = "1920x1080";
            DesiredCapabilities capabilities = new DesiredCapabilities();
            capabilities.setBrowserName("chrome");
            capabilities.setCapability("name", testName+"-"+testMethodName);
            //capabilities.setVersion("80.0");
            //capabilities.setCapability("enableVNC", true);
            //capabilities.setCapability("enableVideo", false);
            Configuration.browserCapabilities = capabilities;
            driver = new RemoteWebDriver(
                    URI.create("http://localhost:4444/wd/hub").toURL(), capabilities);
            log.info("Chrome driver has been initialized");
        } else if (browser.equals("firefox")) {
            Configuration.remote = "http://localhost:4444/wd/hub";
            Configuration.browser = "firefox";
            Configuration.browserSize = "1920x1080";
            DesiredCapabilities capabilities = new DesiredCapabilities();
            capabilities.setBrowserName("firefox");
            capabilities.setCapability("name", testName+"-"+testMethodName);
            capabilities.setVersion("74");
            capabilities.setCapability("enableVNC", true);
            capabilities.setCapability("enableVideo", false);
            Configuration.browserCapabilities = capabilities;
            driver = new RemoteWebDriver(
                    URI.create("http://localhost:4444/wd/hub").toURL(), capabilities);
            log.info("Firefox driver has been initialized");
        }
        else if (browser.equals("internet explorer")) {
            Configuration.remote = "http://localhost:4444/wd/hub";
            Configuration.browser = "internet explorer";
            Configuration.browserSize = "1920x1080";
            DesiredCapabilities capabilities = new DesiredCapabilities();
            capabilities.setBrowserName("internet explorer");
            capabilities.setCapability("name", testName+"-"+testMethodName);
            capabilities.setVersion("74");
            capabilities.setCapability("enableVNC", true);
            capabilities.setCapability("enableVideo", false);
            Configuration.browserCapabilities = capabilities;
            driver = new RemoteWebDriver(
                    URI.create("http://localhost:4444/wd/hub").toURL(), capabilities);
            log.info("Internet explorer driver has been initialized");
        }
        dr.set(driver);
        log.info("Starting browser");
        dr.get().manage().window().maximize();
    }

    /**
     * Get date in format ddMMyyyy as String
     */
    private String getTodayDate() {
        return (new SimpleDateFormat("ddMMyyyy").format(new Date()));
    }

    /**
     * Get time in format HHmmssSSS as String
     */
    private String getSystemTime() {
        return (new SimpleDateFormat("HHmmssSSS").format(new Date()));
    }

    /**
     * Makes screenshot and put it in special folders (structured and sorted it by Date and Class)
     */
    public void takeScreenshotToProjectDirectory() {
        File screenshot = ((TakesScreenshot) dr.get()).getScreenshotAs(OutputType.FILE);
        String path = System.getProperty("user.dir")
                + File.separator + "test-output"
                + File.separator + "screenshots"
                + File.separator + getTodayDate()
                + File.separator + testSuiteName
                + File.separator + testName
                + File.separator + testMethodName
                + File.separator + getSystemTime()
                + " " + testName + "_" + testMethodName + ".png";
        try {
            FileUtils.copyFile(screenshot, new File(path));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Get logs from browser console
     */
    public List<LogEntry> getBrowserLogs() {
        LogEntries log = dr.get().manage().logs().get("browser");
        return log.getAll();
    }

    /**
     * Write Cookies to the File for using in next tests
     */
    public void writeCookiesToFile() {
        Set<Cookie> cookies = dr.get().manage().getCookies();
        try (FileWriter fileWriter = new FileWriter(new File("./target/cookie.data"));
             BufferedWriter bufferedWriter = new BufferedWriter(fileWriter)) {
            for (Cookie cookie : cookies) {
                String strCookie = cookie.getName()
                        + ";" + cookie.getValue()
                        + ";" + cookie.getDomain()
                        + ";" + cookie.getPath()
                        + ";" + cookie.getExpiry()
                        + ";" + cookie.isHttpOnly()
                        + ";" + cookie.isSecure();
                bufferedWriter.write(strCookie);
                bufferedWriter.newLine();
            }
            bufferedWriter.flush();
            log.info("Cookies were parsed into /target/cookie.data");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * If test status Failed - makes screenshot to project directory, then driver.quit
     */
    @AfterMethod(alwaysRun = true)
    public void quitDriver(ITestResult result) {
        if (result.FAILURE == result.getStatus()) {
            takeScreenshotToProjectDirectory();
        }
        dr.get().quit();
        log.info("Driver has been closed");
    }
}