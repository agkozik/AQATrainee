package com.herokuapp.theinternet.testEngine;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;

@Listeners({com.herokuapp.theinternet.testEngine.TestListener.class})
public class DataToTest extends TestEngine {

    @DataProvider(name="files")
    protected static Object[][] files() {
        return new Object[][]{
                {"firstTest", "1.txt"},
                {"secondTest", "2.jpg"},
                {"thirdTest", "3.doc"}
        };
    }
}