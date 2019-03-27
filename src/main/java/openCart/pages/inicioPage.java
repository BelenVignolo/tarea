package openCart.pages;

import automationFramework.utils.datatypes.Wait;
import openCart.bases.openCartBasePage;
import openCart.pageObjectsHandler.openCartPageObjectsHandler;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class inicioPage extends openCartBasePage {

    public inicioPage(WebDriver driver, openCartPageObjectsHandler instance) {
        super(driver, instance);
    }


    @FindBy(className = "caret")
    WebElement cosaButton;

    @FindBy(linkText = "Login")
    WebElement loginButton;

    @FindBy(linkText = "Logout")
    WebElement logoutButton;

    @FindBy(linkText = "Gift Certificates")
    WebElement giftCertificateButton;

    @FindBy(css = "[title=\"Shopping Cart\"]")
    WebElement carritoButton;


    /*
    @FindBy(css =".product-layout:nth-child(1) .fa-shopping-cart")
    WebElement AddMcBookToCartButton;

    @FindBy(css = ".product-layout:nth-child(2) button:nth-child(1)")
    WebElement AddiPhoneToCartButton;

    @FindBy(css = ".product-layout:nth-child(1) .fa-heart")
    WebElement AddMcBookToWishListButton;

    @FindBy(css = ".product-layout:nth-child(2) button:nth-child(2)")
    WebElement AddiPhoneToWishListButton;

    @FindBy(css = ".product-layout:nth-child(3) button:nth-child(2)")
    WebElement AddAppleCinemaToWishListButton;

    @FindBy(css = ".product-layout:nth-child(4) button:nth-child(2)")
    WebElement AddCanonToWishListButton;

*/

    public loginPage goToLogin(){
        this.cosaButton.click();
        addWait(Wait.MEDIUM_WAIT);
        this.loginButton.click();
        return pageObjectsHandler.getLoginPage();
    }


    public void cerrarSesion(){
        this.cosaButton.click();
        addWait(Wait.MEDIUM_WAIT);
        this.logoutButton.click();
    }

    public void addToCart(String elemento){
        WebElement a=driver.findElement(By.xpath("//a[text()='" + elemento + "']/../../..//button[contains(@onclick, 'cart.add')]"));
        highlighElement(a);
        a.click();
        addWait(Wait.LONG_WAIT);
    }

    public void addToWishList(String elemento){
        WebElement a=driver.findElement(By.xpath("//a[text()='" + elemento + "']/../../..//button[contains(@onclick, 'wishlist.add')]"));
        highlighElement(a);
        a.click();
        addWait(Wait.LONG_WAIT);
    }

    public giftCardPage goToGiftCardPage(){
        highlighElement(giftCertificateButton);
        giftCertificateButton.click();
        return pageObjectsHandler.getGiftCardPage();
    }

    public compraPage goToCompraPage(){
        carritoButton.click();
        return pageObjectsHandler.getCompraPage();
    }

}


