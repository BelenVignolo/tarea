import automationFramework.tests.BaseTest;
import org.testng.annotations.Test;

import java.io.IOException;

public class GiftCard  extends BaseTest {

    @Test
    public void ejecutar() throws InterruptedException, IOException {

        inicio=pageObjectsHandler.getInicioPage();
        giftCard=inicio.goToGiftCardPage();

        giftCard.ingresarToName("nombre1");
        giftCard.ingresarToEmail("email1");
        giftCard.ingresarFromName("nombre2");
        giftCard.ingresarFromEmail("email2");
        giftCard.seleccionarCheckBox(1);
        giftCard.enviar();
    }
}
