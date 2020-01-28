package com.herokuapp.theinternet.windowsTests;

import com.herokuapp.theinternet.pages.NewWindowPage;
import com.herokuapp.theinternet.pages.WelcomePage;
import com.herokuapp.theinternet.pages.WindowPage;
import org.testng.Assert;
import org.testng.annotations.Test;
import testEngine.RunTestAgain;
import testEngine.TestEngine;

public class NewWindowPageTests extends TestEngine {

    @Test (retryAnalyzer = RunTestAgain.class)
    void openNewWindowAndCheckTitle(){
        NewWindowPage newWindowPage = new WelcomePage(getDriver(),log)
                .openPage()
                .clickMultipleWindowsLink()
                .clickOpenNewWindow()
                .switchToNewOpenedWindowUsingTitle("New Window");
        Assert.assertEquals(newWindowPage.getCurrentPageTitle(),"Test must be failed to AutoRetry");
    }

    @Test(retryAnalyzer = RunTestAgain.class)
    public void openNewWindowAndGoBackToTheFirstWindow(){
        WindowPage windowPage=new WelcomePage(getDriver(),log)
                .openPage()
                .clickMultipleWindowsLink()
                .switchToNewWindowAndBack();
        Assert.assertEquals(windowPage.getCurrentUrlFromBrowser(),"http://the-internet.herokuapp.com/windows");
    }
}