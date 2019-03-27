import automationFramework.tests.BaseTest;
import org.testng.annotations.Test;

import java.io.IOException;

public class WishList extends BaseTest {

    @Test
    public void ejecutar() throws InterruptedException, IOException {
        inicio=pageObjectsHandler.getInicioPage();
        login=inicio.goToLogin();
        login.login("a@a.aa","aaaa");
        inicio=login.goToInicio();

        inicio.addToWishList("MacBook");
        inicio.addToWishList("iPhone");
        inicio.addToWishList("Apple Cinema 30\"");

    }
}
