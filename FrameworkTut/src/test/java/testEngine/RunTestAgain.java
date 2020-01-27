package testEngine;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

import java.io.IOException;

/**
 * If test falls you can add to annotation @Test(retryAnalyzer = RunTestAgain.class) then it will be run again.
 */
public class RunTestAgain extends TestEngine implements IRetryAnalyzer {

    private int nowCount = 0;
    private int maxCount = 1;

    @Override
    public boolean retry(ITestResult iTestResult) {
        if (nowCount < maxCount) {
            nowCount++;
            return true; //пока истина перезапускаем
        }
        System.out.println("FAILED: Test failed twice. Screenshot has been added to directory.");
        try {
            takeScreenshot();
        } catch (IOException e) {
            e.printStackTrace();
        }
        // пишем в лог или делаем скриншот
        nowCount = 0;
        return false;
    }
}