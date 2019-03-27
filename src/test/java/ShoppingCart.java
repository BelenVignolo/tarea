import automationFramework.utils.ExcelUtils;
import automationFramework.tests.BaseTest;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;

import static automationFramework.utils.Utils.applyDefaultIfMissing;

public class ShoppingCart extends BaseTest {

    @Test(dataProvider="Authentication")
    public void ejecutar(String usuario, String password, String producto) throws InterruptedException,  IOException{

        String excelFile = applyDefaultIfMissing(System.getProperty("dataAddToCart"), "src\\main\\resources\\Data\\AddToCartEntrada.xlsx");

        XSSFWorkbook myWorkBook = ExcelUtils.openExcel(excelFile);
        XSSFSheet mySheet = myWorkBook.getSheetAt(0);


        inicio=pageObjectsHandler.getInicioPage();
        login=inicio.goToLogin();
        login.login(usuario,password);
        inicio=login.goToInicio();

        inicio.addToCart(producto);
;

    }

    @DataProvider
    public Object[][] Authentication() throws Exception{
        String excelFile = applyDefaultIfMissing(System.getProperty("DATASOURCE2"), "src\\main\\resources\\Data\\AddToCartEntrada.xlsx");
        Object[][] testObjArray = ExcelUtils.getTableArray(excelFile,"Sheet1",3);

        return (testObjArray);

    }

}
