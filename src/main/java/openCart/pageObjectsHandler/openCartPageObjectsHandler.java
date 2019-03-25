package openCart.pageObjectsHandler;

import automationFramework.handlers.PageObjectsHandler;
import openCart.pages.inicioPage;
import openCart.pages.loginPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class openCartPageObjectsHandler extends PageObjectsHandler {
    private static openCartPageObjectsHandler instance = null;

    private loginPage login;
    private inicioPage inicio;

    public static openCartPageObjectsHandler getInstance(WebDriver driver) {
        if (instance == null) {
            instance = new openCartPageObjectsHandler(driver);
        }
        return instance;
    }

    protected openCartPageObjectsHandler(WebDriver driver) {
        super(driver);
    }

    public loginPage getLoginPage() {
        if (login == null) {
            login = new loginPage(driver, instance);
            PageFactory.initElements(driver, login);
        }
        return login;
    }

    public inicioPage getInicioPage() {
        if (inicio == null) {
            inicio = new inicioPage(driver, instance);
            PageFactory.initElements(driver, inicio);
        }
        return inicio;
    }
}