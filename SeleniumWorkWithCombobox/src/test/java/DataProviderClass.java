import org.testng.annotations.DataProvider;

public class DataProviderClass {
    @DataProvider(name = "data-provider")
    public static Object[][] dataProviderMethod() {
        return new Object[][]{
                {"day", "21"},
                {"month","фев"}
        };
    }
}