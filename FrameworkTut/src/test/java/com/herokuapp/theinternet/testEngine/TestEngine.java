package com.herokuapp.theinternet.testEngine;

import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.logging.LogEntries;
import org.openqa.selenium.logging.LogEntry;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestContext;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class TestEngine {
    //ThreadLocal will keep local copy of driver
    public static ThreadLocal<RemoteWebDriver> dr = new ThreadLocal<>();
    public Logger log;

    public String testSuiteName;
    public String testName;
    public String testMethodName;

    @BeforeMethod(alwaysRun = true)
    //Parameter will get browser from testng.xml on which browser test to run
    @Parameters("browser")
    public void initDriver(Method method, @Optional("chrome")String browser, ITestContext ctx) throws MalformedURLException {

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

        this.testSuiteName = ctx.getCurrentXmlTest().getSuite().getName();
        this.testName = ctx.getCurrentXmlTest().getName();
        this.testMethodName=method.getName();
        this.log = LogManager.getLogger(testName);
    }

    public WebDriver getDriver() {
        return dr.get();
    }

    public void setWebDriver(RemoteWebDriver driver) {
        dr.set(driver);
    }

    @AfterMethod(alwaysRun = true)
    public void closeDriver() {
        log.info("Driver has been closed");
        getDriver().quit();
        dr.set(null);
    }

    /**
     * Take screenshot with time and
     */
    public void takeScreenshotToCTmpDirectory() throws IOException {
        File screenshot = ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.FILE);
        Date dataNow = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("hh_mm_ss");
        String dynamicNameForScreenshot = simpleDateFormat.format(dataNow) + ".png";
        FileHandler.copy(screenshot, new File("c:\\tmp\\" + dynamicNameForScreenshot));
    }

    /**
     * Makes screenshot and put it in special folders (sorted it by Date and Class)
     */
    public void takeScreenshotWithName(String fileName){
        File screenshot = ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.FILE);
        String path = System.getProperty("user.dir")
                +File.separator+"test-output"
                +File.separator+"screenshots"
                +File.separator+getTodayDate()
                +File.separator+testSuiteName
                +File.separator+testName
                +File.separator+testMethodName
                +File.separator+getSystemTime()
                +" "+fileName+".png";
        try
        {
            FileUtils.copyFile(screenshot,new File (path));
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }

    /**
     * Makes screenshot and put it in special folders (sorted it by Date and Class)
     */
    public void takeScreenshot(){
        File screenshot = ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.FILE);
        String path = System.getProperty("user.dir")
                +File.separator+"test-output"
                +File.separator+"screenshots"
                +File.separator+getTodayDate()
                +File.separator+testSuiteName
                +File.separator+testName
                +File.separator+testMethodName
                +File.separator+getSystemTime()
                +" "+testName+"_"+testMethodName+".png";
        try
        {
            FileUtils.copyFile(screenshot,new File (path));
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }

    /**
     * Return SimpleDate ddMMyyyy to using in takeScreenshot method
     */
    private String getTodayDate() {
        return(new SimpleDateFormat("ddMMyyyy").format(new Date()));
    }

    /**
     * Return SimpleDate HHmmssSSS to using in takeScreenshot method
     */
    private String getSystemTime(){
        return (new SimpleDateFormat("HHmmssSSS").format(new Date()));
    }

    /**
     * Get logs from browser console
     */
    public List<LogEntry> getBrowserLogs(){
        LogEntries log=getDriver().manage().logs().get("browser");
        return log.getAll();
    }
}