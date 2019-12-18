package pages;

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
    private WebDriver driver;
    private final int WAIT_SEC=25;
    private ArrayList<String> listBodyText=new ArrayList<>();

    public ViewOutgoingMailPage(WebDriver driver) {
        this.driver = driver;
        new WebDriverWait(driver, WAIT_SEC).until(ExpectedConditions
                .presenceOfElementLocated(By.xpath("//div[contains(@class,'ns-view-message-body')]")));
        PageFactory.initElements(driver,this);
    }

    @FindBy(xpath = "//div[contains(@class,'ns-view-message-body')]")
    WebElement mailBodyContent;

    public ArrayList<String> getListFromMailBody(){
        String bodyText = mailBodyContent.getText();
        listBodyText.add(bodyText);
        return listBodyText;
    }

    public String getListFromMailBodyStream(){
        String bodyText = mailBodyContent.getText();
        return bodyText;
    }
}