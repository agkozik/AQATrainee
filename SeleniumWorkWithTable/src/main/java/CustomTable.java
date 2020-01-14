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

//получение строк таблицы без заголовков
    List<WebElement> getRows(){
        List<WebElement> rows = table.findElements(By.xpath(".//tr"));
        rows.remove(0);
        return rows;
    }

//получение строки заголовков
    List<WebElement> getHeaders(){
        WebElement headersRow = table.findElement(By.xpath(".//tr[1]"));
        List<WebElement> headingColumns = headersRow.findElements(By.xpath(".//th"));
        return  headingColumns;
    }

    //возвращает лист листов: лист строк в каждом индексе которого находится лист колонок
    List<List<WebElement>> getRowsWithColumns(){
      List<WebElement> rows = getRows();
      List<List<WebElement>> rowsWithColumns = new ArrayList<List<WebElement>>();
      for (WebElement row :rows){
          List<WebElement> rowWithColumns= row.findElements(By.xpath(".//td"));
          rowsWithColumns.add(rowWithColumns);
        }
      return rowsWithColumns;
    }

    //получение значения ячейки по номеру строки и столбца
    String getValueFromCell (int rowNumber, int columnNumber){
        List<List<WebElement>> rowsWithColumns = getRowsWithColumns();
        WebElement cell = rowsWithColumns.get(rowNumber-1).get(columnNumber-1);
        return cell.getText();
    }

    //лист это список всех строк, каждая строка это Map, в которой String это заголовок, а WebElement это конкретная ячейка
    List<Map<String,WebElement>> getRowsWithColumnsByHeaders(){
        List<List<WebElement>> rowsWithColumns = getRowsWithColumns();
        List<Map<String, WebElement>> rowsWithColumnsByHeaders = new ArrayList<Map<String, WebElement>>();
        Map<String, WebElement> rowByHeaders;
        List<WebElement> headersColumns =getHeaders();
        //перебор всех строк таблицы
        for (List<WebElement> row :rowsWithColumns){
            rowByHeaders = new HashMap<String, WebElement>();
            //перебор всех колонок в строке заголовков, чтобы получить заголовок в String и ячейку WebElement, которую добавляем в Map
            for(int i=0; i<headersColumns.size(); i++){
                String header = headersColumns.get(i).getText();
                WebElement cell =row.get(i);
                rowByHeaders.put(header,cell);
            }
            //в лист добавляем сформированную на итерации Map
            rowsWithColumnsByHeaders.add(rowByHeaders);
        }
        return rowsWithColumnsByHeaders;
    }

    String getValueFromCellByColumnName (int rowNumber, String columnName){
        List <Map<String,WebElement>> rowsWithColumnsByHeaders = getRowsWithColumnsByHeaders();
        return rowsWithColumnsByHeaders.get(rowNumber-1).get(columnName).getText();
    }
}