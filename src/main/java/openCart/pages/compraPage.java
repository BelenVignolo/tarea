package openCart.pages;

import openCart.bases.openCartBasePage;
import openCart.pageObjectsHandler.openCartPageObjectsHandler;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class compraPage extends openCartBasePage {

    public compraPage(WebDriver driver, openCartPageObjectsHandler instance) {
        super(driver, instance);
    }

    @FindBy(linkText = "Checkout")
    WebElement checkoutButton;

    @FindBy(id = "button-payment-address")
    WebElement continueBillingDetails;

    @FindBy(id = "button-shipping-address")
    WebElement continueDeliveryDetails;

    @FindBy(id = "button-shipping-method")
    WebElement continueDeliveryMethod;

    @FindBy(name = "agree")
    WebElement check;

    @FindBy(id = "button-payment-method")
    WebElement continuePaymentMethod;

    @FindBy(id = "button-confirm")
    WebElement confirmOrder;

    public void comprar(){
        checkoutButton.click();
        continueBillingDetails.click();
        continueDeliveryDetails.click();
        continueDeliveryMethod.click();
        check.click();
        continuePaymentMethod.click();
        confirmOrder.click();
    }
}
