package openCart.pages;

import openCart.bases.openCartBasePage;
import openCart.pageObjectsHandler.openCartPageObjectsHandler;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class giftCardPage extends openCartBasePage {

    public giftCardPage(WebDriver driver, openCartPageObjectsHandler instance) {
        super(driver, instance);
    }

    @FindBy(id = "input-to-name")
    WebElement toName;

    @FindBy(id = "input-to-email")
    WebElement toEmail;

    @FindBy(id = "input-from-name")
    WebElement fromName;

    @FindBy(id = "input-from-email")
    WebElement fromEmail;

    @FindBy(css = "[type='checkbox']")
    WebElement check;

    @FindBy(css = "[value='Continue']")
    WebElement continueButton;

    public void ingresarToName(String nombre){
        highlighElement(toName);
        toName.sendKeys(nombre);
    }

    public void ingresarToEmail(String email){
        highlighElement(toEmail);
        toEmail.sendKeys(email);
    }

    public void ingresarFromName(String nombre){
        highlighElement(fromName);
        fromName.sendKeys(nombre);
    }

    public void ingresarFromEmail(String email){
        highlighElement(fromEmail);
        fromEmail.sendKeys(email);
    }

    public void seleccionarCheckBox(Integer num) {
        WebElement checkBox=driver.findElement(By.cssSelector(".col-sm-10 div:nth-child(" + num +") input"));
        highlighElement(checkBox);
        checkBox.click();
    }

    public void enviar(){
        check.click();
        continueButton.click();
    }
}