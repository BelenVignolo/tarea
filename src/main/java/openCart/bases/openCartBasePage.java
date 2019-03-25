package openCart.bases;

import automationFramework.pageObjects.BasePage;
import openCart.pageObjectsHandler.openCartPageObjectsHandler;
import org.openqa.selenium.WebDriver;

public abstract class openCartBasePage extends BasePage {

    protected static openCartPageObjectsHandler pageObjectsHandler;

    public openCartBasePage(WebDriver driver, openCartPageObjectsHandler handler) {
        super(driver);
        openCartBasePage.pageObjectsHandler = handler;
    }

    public openCartPageObjectsHandler getPageObjectsHandler() {
        return pageObjectsHandler;
    }

    public void setPageObjectsHandler(openCartPageObjectsHandler pageObjectsHandler) {
        this.pageObjectsHandler = pageObjectsHandler;
    }
}
