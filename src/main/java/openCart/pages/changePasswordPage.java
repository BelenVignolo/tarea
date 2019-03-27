package openCart.pages;

import openCart.bases.openCartBasePage;
import openCart.pageObjectsHandler.openCartPageObjectsHandler;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class changePasswordPage  extends openCartBasePage {

    public changePasswordPage(WebDriver driver, openCartPageObjectsHandler instance) {
        super(driver, instance);
    }

    @FindBy(css = "[name='password']")
    WebElement password;

    @FindBy(css = "[name='confirm']")
    WebElement confirmPassword;

    @FindBy(css = "[type='submit']")
    WebElement enviarButton;

    public void changePassword(String pswd){
        password.sendKeys(pswd);
        confirmPassword.sendKeys(pswd);
        enviarButton.click();
    }

}
