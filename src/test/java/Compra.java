import automationFramework.tests.BaseTest;
import org.testng.annotations.Test;

import java.io.IOException;

public class Compra extends BaseTest {

    @Test
    public void ejecutar() throws InterruptedException, IOException {
        new ShoppingCart
        ShoppingCart.ejecutar("a@aa.a","aaaa","MacBook");
        inicio=pageObjectsHandler.getInicioPage();
        login=inicio.goToLogin();
        login.login("a@a.aa","aaaa");
        compra=inicio.goToCompraPage();
        compra.comprar();
    }
}
