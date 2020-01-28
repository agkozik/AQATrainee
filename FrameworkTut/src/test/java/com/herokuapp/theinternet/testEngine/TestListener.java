package com.herokuapp.theinternet.testEngine;

import org.apache.logging.log4j.LogManager;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class TestListener extends TestEngine implements ITestListener {

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
        this.testName=context.getCurrentXmlTest().getName();
        this.log= LogManager.getLogger(testName);
        log.info("[TEST "+testName+" STARTED]");
    }

    @Override
    public void onFinish(ITestContext context) {
        log.info("[ALL "+testName+" FINISHED]");
    }
}