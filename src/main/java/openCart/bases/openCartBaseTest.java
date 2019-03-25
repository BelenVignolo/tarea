package openCart.bases;

import automationFramework.tests.BaseTest;
import openCart.pageObjectsHandler.openCartPageObjectsHandler;
import openCart.pages.inicioPage;
import openCart.pages.loginPage;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class openCartBaseTest extends BaseTest {
    protected openCartPageObjectsHandler pageObjectsHandler;
    protected loginPage login;
    protected inicioPage inicio;

    @BeforeMethod
    public void initialize() {
        pageObjectsHandler = openCartPageObjectsHandler.getInstance(driver);
    }

    @AfterMethod
    public void dispose() {
        openCartPageObjectsHandler.getInstance(null);
    }
}