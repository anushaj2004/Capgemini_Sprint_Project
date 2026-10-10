package GenericUtitlity;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormat;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.testng.annotations.DataProvider;

public class ExcelUtility {
	Workbook workbook;

    // Open Excel file
    public void openExcel() throws IOException {

        FileInputStream f = new FileInputStream("./src/main/resources/Test_data/ecom_SwagLabstest_data.xlsx");

        workbook = WorkbookFactory.create(f);
    }

    // Get data from Excel
    public String getData(String sheetName, int rowNum, int cellNum) {

        Sheet sheet = workbook.getSheet(sheetName);

        Row row = sheet.getRow(rowNum);

        Cell cell = row.getCell(cellNum);
        DataFormatter df=new DataFormatter();
        return df.formatCellValue(cell);
    }
  
    // Get number of rows
    public int getRowCount(String sheetName) {

        Sheet sheet = workbook.getSheet(sheetName);

        return sheet.getLastRowNum();
    }
    
    public Object[][] getMultipleData() throws IOException {

        FileInputStream file = new FileInputStream("./src/main/resources/Test_data/ecom_SwagLabstest_data.xlsx");

        Workbook wb = WorkbookFactory.create(file);
        Sheet sh = wb.getSheet("Sheet1");

        int rowCount = sh.getLastRowNum();
        int colCount = sh.getRow(0).getLastCellNum();

        Object[][] data = new Object[rowCount][colCount];

        DataFormatter df = new DataFormatter();

        for (int i = 1; i <= rowCount; i++) {
            for (int j = 0; j < colCount; j++) {
                data[i - 1][j] =
                    df.formatCellValue(sh.getRow(i).getCell(j));
            }
        }

        wb.close();
        file.close();

        return data;
    }

    // Close Excel
    public void closeExcel() throws IOException {

        workbook.close();
    }
    public void openExcelForLogin() throws IOException {

        FileInputStream f = new FileInputStream("./src/main/resources/Test_data/ecom_SwagLabstest_data.xlsx");

        workbook = WorkbookFactory.create(f);
    }
    
	
	
}
