import org.testng.annotations.DataProvider;

public class DataProviderClass {
    @DataProvider(name = "days")
    public static Object[][] dataProviderMethodDays() {
        return new Object[][]{
                new Object[] {"day", "1"},
                new Object[] {"day", "2"},
        };
    }

    @DataProvider(name = "month")
    public static Object[][] dataProviderMethodMonth() {
        return new Object[][]{
                new Object[] {"month", "янв"},
                new Object[] {"month", "фев"},
        };
    }

    @DataProvider(name = "years")
    public static Object[][] dataProviderMethodYears() {
        return new Object[][]{
                new Object[] {"year", "2020"},
                new Object[] {"year", "2019"},
        };
    }
}