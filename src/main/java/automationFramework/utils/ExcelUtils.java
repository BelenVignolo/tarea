package automationFramework.utils;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ExcelUtils {

    public static XSSFWorkbook openExcel(String fileName) throws IOException {

        //Leer Excel
        File myFile = new File(fileName);
        FileInputStream fis = new FileInputStream(myFile);

        // Finds the workbook instance for XLSX file
        XSSFWorkbook myWorkBook = new XSSFWorkbook(fis);

        return myWorkBook;
    }

    public static int cantidadDeFilasExcelCambioTLO(XSSFSheet mySheet) {
        return cantidadDeFilasExcel(mySheet, 0);
    }
    public static int cantidadDeFilasExcelCreacionTicket(XSSFSheet mySheet) {
        return cantidadDeFilasExcel(mySheet, 2);
    }

    public static int cantidadDeFilasExcelDebitos(XSSFSheet mySheet) {
        return cantidadDeFilasExcel(mySheet, 9);
    }

    public static int cantidadDeFilasExcelIngresoPagos(XSSFSheet mySheet) {
        return cantidadDeFilasExcel(mySheet, 2);
    }

    public static int cantidadDeFilasExcel(XSSFSheet mySheet, int columna) {
        int rowIndex;
        for (rowIndex = 1; rowIndex <= mySheet.getLastRowNum(); rowIndex++) {
            Row row = mySheet.getRow(rowIndex);
            if (row != null) {
                String val = getStringCellValue(row, columna);
                if (val.equals("")) {
                    break;
                }
            } else {
                break;
            }
        }
        return rowIndex - 1;
    }

    public static String getStringCellValue(Row row, int i) {
        String res;
        try {
            res = row.getCell(i) != null ? row.getCell(i).getStringCellValue() : "";
        } catch (Exception e) {
            double resDouble = row.getCell(i) != null ? row.getCell(i).getNumericCellValue() : -1;
            int resInt = row.getCell(i) != null ? (int) row.getCell(i).getNumericCellValue() : -1;
            String stringDouble = resDouble != -1 ? Double.toString(resDouble) : "";
            String stringInt = resInt != -1 ? Integer.toString(resInt) : "";
            if (stringDouble.contains("E") || stringDouble.endsWith(".0")) {
                res = stringInt;
            } else {
                res = stringDouble.replace(".",",");
            }
        }
        return res;
    }

    public static String getStringDateCellValue(Row row, int i) {
        String res;
        try {
            Date resDate = row.getCell(i) != null ? row.getCell(i).getDateCellValue() : new Date();
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
            res = dateFormat.format(resDate);
        } catch (Exception e) {
            res = row.getCell(i) != null ? row.getCell(i).getStringCellValue() : "";
        }
        return res;
    }

    public static String getExcelOutPath(String originalPath, String script)
    {
        File file = new File(originalPath);
        String filePath = file.getPath().substring(0, file.getPath().indexOf(file.getName()));
        String fileName = file.getName().replace(".xlsx", "");
        return filePath + "Resultados\\" + fileName + "_" + script + "_Resultado_" + Utils.now() + ".xlsx";
    }
}
