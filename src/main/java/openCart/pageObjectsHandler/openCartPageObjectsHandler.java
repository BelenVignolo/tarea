package openCart.pageObjectsHandler;

import automationFramework.handlers.PageObjectsHandler;
import openCart.pages.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class openCartPageObjectsHandler extends PageObjectsHandler {
    private static openCartPageObjectsHandler instance = null;

    private static loginPage login;
    private static inicioPage inicio;
    private static giftCardPage giftCard;
    private static changePasswordPage changePswd;
    private static compraPage compra;

    public static openCartPageObjectsHandler getInstance(WebDriver driver) {
        if (instance == null) {
            instance = new openCartPageObjectsHandler(driver);
        }
        return instance;
    }

    public static void nullInstance(){
        instance=null;
        login=null;
        inicio=null;
        giftCard=null;
        changePswd=null;
        compra=null;
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
    public giftCardPage getGiftCardPage() {
        if (giftCard == null) {
            giftCard = new giftCardPage(driver, instance);
            PageFactory.initElements(driver, giftCard);
        }
        return giftCard;
    }

    public changePasswordPage getChangePasswordPage() {
        if (changePswd == null) {
            changePswd = new changePasswordPage(driver, instance);
            PageFactory.initElements(driver, changePswd);
        }
        return changePswd;
    }

    public compraPage getCompraPage() {
        if (compra == null) {
            compra = new compraPage(driver, instance);
            PageFactory.initElements(driver, compra);
        }
        return compra;
    }
}