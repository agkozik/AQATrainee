package testEngine;

import org.testng.annotations.DataProvider;

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