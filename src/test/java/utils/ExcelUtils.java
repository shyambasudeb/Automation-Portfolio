package utils;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class ExcelUtils {

    private static Workbook workbook;
    private static Sheet sheet;

    public static void loadExcel(String filePath, String sheetName) throws IOException {
        FileInputStream fis = new FileInputStream(new File(filePath));
        workbook = new XSSFWorkbook(fis);
        sheet = workbook.getSheet(sheetName);
    }

    public static String getCellData(int row, int col) {
        return sheet.getRow(row).getCell(col).getStringCellValue();
    }

    public static int getRowCount() {
        return sheet.getPhysicalNumberOfRows();
    }

    public static void closeExcel() throws IOException {
        workbook.close();
    }
}
