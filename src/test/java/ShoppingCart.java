import openCart.bases.openCartBaseTest;
import org.testng.annotations.Test;

import java.io.IOException;

import static automationFramework.utils.Utils.applyDefaultIfMissing;

public class ShoppingCart extends openCartBaseTest {
    @Test
    public void ejecutar() throws InterruptedException,  IOException{
        inicio=pageObjectsHandler.getInicioPage();
        login=inicio.goToLogin();
        login.login("a@a.aa","aaaa");

        inicio.addToCart(1);
        inicio.addToCart(2);
        inicio.addToCart(3);
    }
}
