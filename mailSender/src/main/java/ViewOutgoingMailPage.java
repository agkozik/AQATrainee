import lombok.Data;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.List;

@Data
public class ViewOutgoingMailPage {
    WebDriver driver;
    private final int WAIT_SEC=25;
    List<String> listBodyText=new ArrayList<>();

    public ViewOutgoingMailPage(WebDriver driver) {
        this.driver = driver;
        new WebDriverWait(driver, WAIT_SEC).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@dir='ltr']")));
        PageFactory.initElements(driver,this);
    }

    @FindBy(xpath = "//div[@dir='ltr']")
    WebElement mailBodyContent;

    public String getListFromMailBody(){
        String bodyText = mailBodyContent.getText();
        return bodyText;
    }
}