package automationFramework.pageObjects;

import automationFramework.utils.GetProperties;
import automationFramework.utils.WebDriverUtils;
import automationFramework.utils.datatypes.Wait;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

import static automationFramework.utils.Utils.applyDefaultIfMissing;

public abstract class BasePage {

    protected static WebDriver driver;
    private static String environment = applyDefaultIfMissing(System.getProperty("environment"), "QA");
    protected static GetProperties properties = new GetProperties(environment);

    public BasePage(WebDriver driver) {
        BasePage.driver = driver;
        addWait(Wait.SHORT_WAIT);
    }

    public void addWait(Wait wait){ WebDriverUtils.addWait(wait); }

    public void addWait(int wait){ WebDriverUtils.waitTime(wait); }

    public void waitForLoading() { WebDriverUtils.waitForLoading(this.driver); }

    public WebElement findElement(final By locator){
        return WebDriverUtils.findElement(this.driver, locator);
    }

    public WebElement findElement(WebElement element, final By locator){
        return WebDriverUtils.findElement(element, locator);
    }

    public List<WebElement> findElements(final By locator){ return WebDriverUtils.findElements(this.driver, locator); }

    public List<WebElement> findElements(WebElement element, final By locator){ return WebDriverUtils.findElements(element, locator); }

    protected void waitForVisibilityOfElement(final By locator){ WebDriverUtils.waitForVisibility(this.driver, locator); }

    public void scrollToElement(WebElement element){
        WebDriverUtils.scrollToElement(this.driver, element);
    }

    public void selectByText(WebElement select, String text){
        WebDriverUtils.selectByText(select, text);
    }

    public void selectByValue(WebElement select, String text){
        WebDriverUtils.selectByValue(select, text);
    }

    public boolean isChecked(WebElement check) {
        return WebDriverUtils.isChecked(check);
    }

    public void checkElement(WebElement check) {
        WebDriverUtils.checkElement(check);
    }

    public void clear(WebElement element){
        WebDriverUtils.clear(element);
    }

    public void sendText(WebElement element, String text){
        WebDriverUtils.sendText(element, text);
    }

    protected String getText(WebElement _field){ return WebDriverUtils.getText(_field); }

    protected String getAttribute(WebElement e, String attribute){ return WebDriverUtils.getAttribute(e,attribute); }

    public void sendKeys(WebElement element, Keys key){
        WebDriverUtils.sendKeys(element, key);
    }

    public void highlighElement(WebElement element) { WebDriverUtils.highlighElement(this.driver, element);}

    public void switchToFrame(String frameId) { WebDriverUtils.switchToFrame(this.driver, frameId); }

    public void switchToMainFrame() { WebDriverUtils.switchToMainFrame(this.driver); }

    public void switchToDefaultContent() { WebDriverUtils.switchToDefaultContent(this.driver); }

}
