import automationFramework.tests.BaseTest;
import org.testng.annotations.Test;

import java.io.IOException;

public class ChangePassword extends BaseTest {

    @Test
    public void ejecutar() throws InterruptedException, IOException {
        inicio=pageObjectsHandler.getInicioPage();
        login=inicio.goToLogin();
        login.login("a@a.aa","aaaa");
        changePswd=login.goToChangePswd();
        changePswd.changePassword("aaaa");

    }
}
