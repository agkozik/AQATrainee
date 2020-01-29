package com.herokuapp.theinternet.testEngine;

import io.qameta.allure.Attachment;
import org.apache.logging.log4j.LogManager;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class TestListener extends TestEngine implements ITestListener {

    // Text attachments for Allure
    @Attachment(value = "Page screenshot", type = "image/png")
    public byte[] saveScreenshotPNG(WebDriver driver) {
        return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
    }

    // Text attachments for Allure
    @Attachment(value = "{0}", type = "text/plain")
    public static String saveTextLog(String message) {
        return message;
    }

    // HTML attachments for Allure
    @Attachment(value = "{0}", type = "text/html")
    public static String attachHtml(String html) {
        return html;
    }

    @Override
    public void onTestStart(ITestResult result) {
        this.testMethodName=result.getMethod().getMethodName();
        log.info("[Starting "+testMethodName+"]");
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        log.info("[TEST "+testMethodName+" passed]");
    }

    @Override
    public void onTestFailure(ITestResult result) {
        log.info("[TEST "+testMethodName+" FAILED]");
        log.info("[Screenshot has been put to the test-output directory]");
        takeScreenshot();
        saveScreenshotPNG(getDriver());
    }

    @Override
    public void onTestSkipped(ITestResult result) {

    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {

    }

    @Override
    public void onTestFailedWithTimeout(ITestResult result) {

    }

    @Override
    public void onStart(ITestContext context) {
        this.testSuiteName = context.getCurrentXmlTest().getSuite().getName();
        this.testName=context.getCurrentXmlTest().getName();
        this.log= LogManager.getLogger(testName);
        log.info("[TEST "+testName+" STARTED]");
    }

    @Override
    public void onFinish(ITestContext context) {
        log.info("[ALL "+testName+" FINISHED]");
    }
}