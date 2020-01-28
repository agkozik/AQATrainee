package com.herokuapp.theinternet.windowsTests;

import com.herokuapp.theinternet.pages.NewWindowPage;
import com.herokuapp.theinternet.pages.WelcomePage;
import com.herokuapp.theinternet.pages.WindowPage;
import com.herokuapp.theinternet.testEngine.RunTestAgain;
import com.herokuapp.theinternet.testEngine.TestEngine;
import org.testng.Assert;
import org.testng.annotations.Ignore;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners({ com.herokuapp.theinternet.testEngine.TestListener.class })
public class NewWindowPageTests extends TestEngine {

    @Test(retryAnalyzer = RunTestAgain.class)
    void openNewWindowAndCheckTitle() {
        NewWindowPage newWindowPage = new WelcomePage(getDriver(), log)
                .openPage()
                .clickMultipleWindowsLink()
                .clickOpenNewWindow()
                .switchToNewOpenedWindowUsingTitle("New Window");
        Assert.assertEquals(newWindowPage.getCurrentPageTitle(), "Test must be failed to AutoRetry");
    }

    @Ignore
    @Test(retryAnalyzer = RunTestAgain.class)
    public void openNewWindowAndGoBackToTheFirstWindow() {
        WindowPage windowPage = new WelcomePage(getDriver(), log)
                .openPage()
                .clickMultipleWindowsLink()
                .switchToNewWindowAndBack();
        Assert.assertEquals(windowPage.getCurrentUrlFromBrowser(), "http://the-internet.herokuapp.com/windows");
    }
}