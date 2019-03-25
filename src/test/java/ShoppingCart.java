import automationFramework.utils.ExcelUtils;
import openCart.bases.openCartBaseTest;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.Test;

import java.io.IOException;

import static automationFramework.utils.Utils.applyDefaultIfMissing;

public class ShoppingCart extends openCartBaseTest {

    @Test
    public void ejecutar() throws InterruptedException,  IOException{

        String excelFile = applyDefaultIfMissing(System.getProperty("dataAddToCart"), "src\\main\\resources\\Data\\AddToCartEntrada.xlsx");

        XSSFWorkbook myWorkBook = ExcelUtils.openExcel(excelFile);
        XSSFSheet mySheet = myWorkBook.getSheetAt(0);


        inicio=pageObjectsHandler.getInicioPage();
        login=inicio.goToLogin();
        login.login("a@a.aa","aaaa");
        inicio=login.goToInicio();

        inicio.addToCart("MacBook");
        inicio.addToCart("iPhone");
        inicio.addToCart("Apple Cinema 30\"");

    }



}
