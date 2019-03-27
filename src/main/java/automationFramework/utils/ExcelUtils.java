package automationFramework.utils;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ExcelUtils {
    private static XSSFSheet ExcelWSheet;

    private static XSSFWorkbook ExcelWBook;

    private static XSSFCell Cell;

    private static XSSFRow Row;

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


   /* public static Object[][] getTableArray(String FilePath, String SheetName, int iTestCaseRow)    throws Exception

    {

        String[][] tabArray = null;

        try{

            FileInputStream ExcelFile = new FileInputStream(FilePath);

            // Access the required test data sheet

            ExcelWBook = new XSSFWorkbook(ExcelFile);

            ExcelWSheet = ExcelWBook.getSheet(SheetName);

            int startCol = 1;

            int ci=0,cj=0;

            int totalRows = 1;

            int totalCols = 2;

            tabArray=new String[totalRows][totalCols];

            for (int j=startCol;j<=totalCols;j++, cj++)

            {

                tabArray[ci][cj]=getCellData(iTestCaseRow,j);

                System.out.println(tabArray[ci][cj]);

            }

        }

        catch (FileNotFoundException e)

        {

            System.out.println("Could not read the Excel sheet");

            e.printStackTrace();

        }

        catch (IOException e)

        {

            System.out.println("Could not read the Excel sheet");

            e.printStackTrace();

        }

        return(tabArray);

    }*/

    public static Object[][] getTableArray(String FilePath, String SheetName, int totalCols) throws Exception {

        String[][] tabArray = null;

        try {

            FileInputStream ExcelFile = new FileInputStream(FilePath);

            // Access the required test data sheet

            ExcelWBook = new XSSFWorkbook(ExcelFile);

            ExcelWSheet = ExcelWBook.getSheet(SheetName);

            int startRow = 1;

            int startCol = 0;

            int ci,cj;

            int totalRows = ExcelWSheet.getLastRowNum();

            // you can write a function as well to get Column count

            tabArray=new String[totalRows][totalCols];

            ci=0;

            for (int i=startRow;i<=totalRows;i++, ci++) {

                cj=0;

                for (int j=startCol;j<totalCols;j++, cj++){

                    tabArray[ci][cj]=getCellData(i,j);

                    System.out.println(tabArray[ci][cj]);

                }

            }

        }

        catch (FileNotFoundException e){

            System.out.println("Could not read the Excel sheet");

            e.printStackTrace();

        }

        catch (IOException e){

            System.out.println("Could not read the Excel sheet");

            e.printStackTrace();

        }

        return(tabArray);

    }


    public static String getCellData(int RowNum, int ColNum) throws Exception{

        try{

            Cell = ExcelWSheet.getRow(RowNum).getCell(ColNum);

            String CellData = Cell.getStringCellValue();

            return CellData;

        }catch (Exception e){

            return"";

        }

    }
}
