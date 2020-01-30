package com.herokuapp.theinternet.testEngine;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;
import org.testng.annotations.Listeners;

/**
 * If test falls you can add to annotation @Test(retryAnalyzer = RunTestAgain.class) it will run test again.
 */
@Listeners({com.herokuapp.theinternet.testEngine.TestListener.class})
public class RunTestAgain extends TestEngine implements IRetryAnalyzer {

    private int nowCount = 0;
    private int maxCount = 1;

    @Override
    public boolean retry(ITestResult iTestResult) {
        if (nowCount < maxCount) {
            nowCount++;
            return true;
        }
        System.out.println("FAILED: Test failed twice. Screenshot has been added to directory.");
        nowCount = 0;
        return false;
    }
}