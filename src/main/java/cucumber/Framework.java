package cucumber;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.json.JSONObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.apache.poi.ss.usermodel.DateUtil;

import java.io.*;
import java.security.PublicKey;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;


public class Framework {
    public static WebDriver driver;
    public String getCucumberProperty(String propertyName) throws IOException {
        String filePath ="src/main/resources/cucumber.properties";
        Properties prop =new Properties();
        prop.load(new FileInputStream(filePath));
        return prop.getProperty(propertyName);

    }
    public Map<String,String> readTestData(String dataSetName, String path,String sheetName) throws IOException {
        Map<String,String> testDataMap = new HashMap<String, String>();
        FileInputStream fs = new FileInputStream(path);
        XSSFWorkbook workBook = new XSSFWorkbook(fs);
        XSSFSheet sheet = workBook.getSheet(sheetName);
        XSSFRow headerRow = sheet.getRow(0);
        int lastRowIndex =sheet.getLastRowNum();
        int dataSetRow =0 ;
        int lastcol =headerRow.getLastCellNum();
         for(int i=1; i<=lastRowIndex;i++)
         {
             XSSFRow row =sheet.getRow(i);
             Cell cell = row.getCell(0);
             String value = getCellValueAsString(cell);
             if(value.equalsIgnoreCase(dataSetName))
             {
                 dataSetRow=i;
                 break;
             }

         }
         for(int i=1;i<lastcol;i++)
         {
             Cell keyCell = headerRow.getCell(i);
             XSSFRow valueRow =sheet.getRow(dataSetRow);
             Cell valueCell =valueRow.getCell(i);

             String key = getCellValueAsString(keyCell);
             String val = getCellValueAsString(valueCell);


             testDataMap.put(key,val);
         }
return testDataMap;
    }
    //Helper method to safely convert any cell type to String
    private String getCellValueAsString(Cell cell) {
        if (cell == null) return "";

        switch (cell.getCellType()) {
            case STRING:
                return cell.getStringCellValue();
            case NUMERIC:
                if (DateUtil.isCellDateFormatted(cell)) {
                    return cell.getDateCellValue().toString(); // Format if needed
                } else {
                    double num = cell.getNumericCellValue();
                    if (num == (long) num)
                        return String.valueOf((long) num); // Avoid .0 for integers
                    else
                        return String.valueOf(num);
                }
            case BOOLEAN:
                return String.valueOf(cell.getBooleanCellValue());
            case FORMULA:
                try {
                    return cell.getStringCellValue(); // Try string formula result
                } catch (IllegalStateException e) {
                    return String.valueOf(cell.getNumericCellValue()); // fallback
                }
            case BLANK:
            case _NONE:
            case ERROR:
            default:
                return "";
        }
    }

    public By getObjectProperty(String objectName,String path) throws IOException {

        By objectProperty = null;

        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode = objectMapper.readTree(new File(path));
        JsonNode objectPropertyNode = jsonNode.get(objectName);
    //String xpath =objectPropertyMap.get("xpath").asText();
        Map<String, String> objectPropertyMap = objectMapper.convertValue(objectPropertyNode, new TypeReference<Map<String, String>>(){});
      // String objectPropertyMap1 = objectMapper.convertValue(objectPropertyNode, new TypeReference<String>(){});

        for(String property:objectPropertyMap.keySet()) {
         switch (property) {

             case "id":
                 if (!objectPropertyMap.get("id").isEmpty()) {
                     objectProperty = By.id(objectPropertyMap.get("id"));

                 }
                 break;
             case "class":
                 if (!objectPropertyMap.get("class").isEmpty()) {
                     objectProperty = By.id(objectPropertyMap.get("class"));
                 }
                 break;
             case "xpath":
                 if (!objectPropertyMap.get("xpath").isEmpty()) {
                     objectProperty = By.xpath(objectPropertyMap.get("xpath"));
                                      }
                 break;
             case "name":
                 if (!objectPropertyMap.get("name").isEmpty()) {
                     objectProperty = By.name(objectPropertyMap.get("name"));
                                      }
                 break;
             case "css":
                 if (!objectPropertyMap.get("css").isEmpty()) {
                     objectProperty = By.cssSelector(objectPropertyMap.get("css"));
                 }
                 break;
             case "link_text":
                 if (!objectPropertyMap.get("link_text").isEmpty()) {
                     objectProperty = By.linkText(objectPropertyMap.get("link_text"));
                                      }
                 break;
         }
        }
        return objectProperty;
    }

}
