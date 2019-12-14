import org.openqa.selenium.WebDriver;
        import org.openqa.selenium.chrome.ChromeDriver;

public class WDriverManager {
    WebDriver driver;
    private static WDriverManager wDriverManager;

    private WDriverManager(){}

    public static WDriverManager getInstance(){
        if (wDriverManager==null){
            wDriverManager=new WDriverManager();
        }
        return wDriverManager;
    }

    public WebDriver getDriver() {
        if (driver==null){
            driver=new ChromeDriver();
            driver.manage().window().maximize();
        }
        return driver;
    }
}