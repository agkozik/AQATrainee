import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CustomTable {
    private WebElement table;
    private WebDriver driver;

    public CustomTable(WebElement table, WebDriver driver) {
        this.table = table;
        this.driver = driver;
    }

    List<WebElement> getRows(){
        List<WebElement> rows = table.findElements(By.xpath(".//tr"));
        rows.remove(0);
        return rows;
    }

    List<WebElement> getHeaders(){
        WebElement headersRow = table.findElement(By.xpath(".//tr[1]"));
        List<WebElement> headingColumns = headersRow.findElements(By.xpath(".//th"));
        return  headingColumns;
    }

    List<List<WebElement>> getRowsWithColumns(){
      List<WebElement> rows = getRows();
      List<List<WebElement>> rowsWithColumns = new ArrayList<List<WebElement>>();
      for (WebElement row :rows){
          List<WebElement> rowWithColumns= row.findElements(By.xpath(".//td"));
          rowsWithColumns.add(rowWithColumns);
        }
      return rowsWithColumns;
    }

    String getValueFromCell (int rowNumber, int columnNumber){
        List<List<WebElement>> rowsWithColumns = getRowsWithColumns();
        WebElement cell = rowsWithColumns.get(rowNumber-1).get(columnNumber-1);
        return cell.getText();
    }

    List<Map<String,WebElement>> getRowsWithColumnsByHeaders(){
        List<List<WebElement>> rowsWithColumns = getRowsWithColumns();
        List<Map<String, WebElement>> rowsWithColumnsByHeaders = new ArrayList<Map<String, WebElement>>();
        Map<String, WebElement> rowByHeaders;
        List<WebElement> headersColumns =getHeaders();
        for (List<WebElement> row :rowsWithColumns){
            rowByHeaders = new HashMap<String, WebElement>();
            for(int i=0; i<headersColumns.size(); i++){
                String header = headersColumns.get(i).getText();
                WebElement cell =row.get(i);
                rowByHeaders.put(header,cell);
            }
            rowsWithColumnsByHeaders.add(rowByHeaders);
        }
        return rowsWithColumnsByHeaders;
    }

    String getValueFromCellByColumnName (int rowNumber, String columnName){
        List <Map<String,WebElement>> rowsWithColumnsByHeaders = getRowsWithColumnsByHeaders();
        return rowsWithColumnsByHeaders.get(rowNumber-1).get(columnName).getText();
    }
}