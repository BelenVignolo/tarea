import automationFramework.utils.ExcelUtils;
import openCart.bases.openCartBaseTest;
import openCart.pages.inicioPage;
import openCart.pages.loginPage;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.Test;

import java.io.IOException;

import static automationFramework.utils.Utils.applyDefaultIfMissing;

public class Login extends openCartBaseTest {


    @Test
    public void ejecutar() throws InterruptedException, IOException {

        String excelFile = applyDefaultIfMissing(System.getProperty("excel"), "C:\\Users\\AbstractaAdmin\\Documents\\pruebaaa\\excelEntrada.xlsx");

        XSSFWorkbook myWorkBook = ExcelUtils.openExcel(excelFile);
        XSSFSheet mySheet = myWorkBook.getSheetAt(0);

        String user;
        String pswd;

        for(int rowIndex = 1; rowIndex<=3; rowIndex++){
            Row row=mySheet.getRow(rowIndex);
            user= ExcelUtils.getStringCellValue(row,0);
            pswd= ExcelUtils.getStringCellValue(row,1);

            inicio=pageObjectsHandler.getInicioPage();

            inicio.goToLogin();
            if (login.login(user,pswd)){
                inicio.cerrarSesion();
            }
        }
    }
}
