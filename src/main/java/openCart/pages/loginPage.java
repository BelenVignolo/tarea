package openCart.pages;

import automationFramework.utils.datatypes.Wait;
import openCart.bases.openCartBasePage;
import openCart.pageObjectsHandler.openCartPageObjectsHandler;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class loginPage extends openCartBasePage {
    @FindBy(id = "input-email")
    WebElement email;

    @FindBy(id = "input-password")
    WebElement password;

    @FindBy(xpath = "//input[@value='Login']")
    WebElement submit;

    @FindBy(css = "h2")
    WebElement h2;

    @FindBy(xpath = "//img[@title='Your Store']")
    WebElement homeButton;

    public loginPage(WebDriver driver, openCartPageObjectsHandler instance) {
        super(driver, instance);
    }

    public boolean login(String email, String password){
        highlighElement(this.email);
        sendText(this.email, email);

        highlighElement(this.password);
        sendText(this.password,password);

        highlighElement(submit);
        submit.click();
        addWait(Wait.PAGELOAD_WAIT);
        addWait(Wait.LONG_WAIT);

        highlighElement(h2);
        return h2.getText().contains("My Account");

    }
    public inicioPage goToInicio(){
        highlighElement(homeButton);
        homeButton.click();
        addWait(Wait.LONG_WAIT);
        return pageObjectsHandler.getInicioPage();
    }

}
