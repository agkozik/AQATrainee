package com.herokuapp.theinternet.windowsTests;

import com.herokuapp.theinternet.pages.NewWindowPage;
import com.herokuapp.theinternet.pages.WelcomePage;
import com.herokuapp.theinternet.pages.WindowPage;
import org.testng.Assert;
import org.testng.annotations.Test;
import turboEngine.RunTestAgain;
import turboEngine.TestEngine;

public class NewWindowPageTests extends TestEngine {

    @Test
    void openNewWindowAndCheckTitle(){
        NewWindowPage newWindowPage = new WelcomePage(getDriver(),log)
                .openPage()
                .clickMultipleWindowsLink()
                .clickOpenNewWindow()
                .switchToNewOpenedWindowUsingTitle("New Window");
        Assert.assertEquals(newWindowPage.getCurrentPageTitle(),"New Window");
    }

    @Test(retryAnalyzer = RunTestAgain.class)
    public void openNewWindowAndGoBackToTheFirstWindow(){
        WindowPage windowPage=new WelcomePage(getDriver(),log)
                .openPage()
                .clickMultipleWindowsLink()
                .switchToNewWindowAndBack();
        Assert.assertEquals(windowPage.getCurrentUrlFromBrowser(),"http://the-internet.herokuapp.com/windowsX");
    }
}