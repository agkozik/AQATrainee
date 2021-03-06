package com.herokuapp.theinternet.javascriptErrorTests;

import com.herokuapp.theinternet.pages.JavaScriptErrorPage;
import com.herokuapp.theinternet.testEngine.TestEngine;
import org.openqa.selenium.logging.LogEntry;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.util.List;
@Listeners({com.herokuapp.theinternet.testEngine.TestListener.class})
public class jsErrorTest extends TestEngine {

    @Test
    public void checkPageOnJsErrors(){
        log.info("Starting checkPageOnJsErrors");
        SoftAssert softAssert= new SoftAssert();
        new JavaScriptErrorPage(getDriver(),log)
                .openPage();
        List<LogEntry> logs = getBrowserLogs();
        for(LogEntry i:logs){
            if (i.getLevel().toString().equals("SEVERE")){
                softAssert.fail("\nSevere error:\n"+i.getMessage());
            }
        }
        log.info("Complete checkPageOnJsErrors");
        softAssert.assertAll();
    }
}