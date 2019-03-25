package automationFramework.utils;

import automationFramework.utils.datatypes.Wait;
import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.concurrent.TimeUnit;

import static automationFramework.utils.Constants.*;

public class WebDriverUtils {

    private final static int WAIT_TIME = 30;

    public static boolean isElementPresent(WebDriver driver, final By Locator) {
        boolean oAux = true;
        try {
            driver.findElement(Locator);
        } catch (NoSuchElementException e) {
            oAux = false;
        }
        return oAux;
    }
	
	public static void waitTime(int time){
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void addWait(Wait wait){
        switch (wait){
            case PAGELOAD_WAIT:
                waitTime(PAGELOAD_TIMEOUT);
                break;
            case LONG_WAIT:
                waitTime(LONG_TIMEOUT);
                break;
            case MEDIUM_WAIT:
                waitTime(TIMEOUT);
                break;
            case SHORT_WAIT:
                waitTime(SHORT_TIMEOUT);
                break;
            default:
                waitTime(SHORT_TIMEOUT);
                break;
        }
    }

    public static void waitForLoading(WebDriver driver) {
        try {
            turnOffImplicitWaits(driver);
            WebDriverWait wait = new WebDriverWait(driver, 300);
            wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("nc-loading")));
            waitTime(1000);
            wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("nc-loading")));
        } catch (UnhandledAlertException uae) {
            throw uae;
        } catch (Exception e) {
            System.err.print(e.getMessage());
        } finally {
            turnOnImplicitWaits(driver);
        }
    }

    private static void turnOffImplicitWaits(WebDriver driver) {
        driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
    }

    private static void turnOnImplicitWaits(WebDriver driver) {
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    }

    public static WebElement findElement(WebDriver driver, final By locator){
        return findElement(driver, locator, true);
    }

    public static WebElement findElement(WebDriver driver, final By locator, boolean printError){
        WebElement element = null;
        try{
            element = driver.findElement(locator);
        }
        catch (NoSuchElementException e) {
            if (printError) {
                System.err.print(e.getMessage());
            }
        }
        return element;
    }

    public static WebElement findElement(WebElement _element, final By locator){
        WebElement element = null;
        try{
            element = _element.findElement(locator);
        }
        catch (NoSuchElementException e){
            System.err.print(e.getMessage());
        }
        return element;
    }

    public static List<WebElement> findElements(WebDriver driver, final By locator){
        List<WebElement> elements = null;
        try{
            elements = driver.findElements(locator);
        }
        catch (NoSuchElementException e){
            System.err.print(e.getMessage());
        }
        return elements;
    }

    public static List<WebElement> findElements(WebElement element, final By locator){
        List<WebElement> elements = null;
        try{
            elements = element.findElements(locator);
        }
        catch (NoSuchElementException e){
            System.err.print(e.getMessage());
        }
        return elements;
    }

    public static void waitForElement(WebDriver driver, By locator) {
        WebDriverWait wait = new WebDriverWait(driver, WAIT_TIME);
        wait.until(ExpectedConditions.elementToBeClickable(locator));
    }

    public static void waitForVisibility(WebDriver _driver, By locator){
        WebDriverWait wait = new WebDriverWait(_driver, WAIT_TIME);
        wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    public static void scrollToElement(WebDriver driver, WebElement element) {
        JavascriptExecutor jse = (JavascriptExecutor) driver;
        jse.executeScript("arguments[0].scrollIntoView(true);", element);
    }

    public static void selectByText(WebElement comboBox, String text){
        if (StringUtils.isNotEmpty(text)) {
            Select select = new Select(comboBox);
            select.selectByVisibleText(text);
        }
    }

    public static void selectByValue(WebElement comboBox, String value){
        if (StringUtils.isNotEmpty(value)) {
            Select select = new Select(comboBox);
            select.selectByValue(value);
        }
    }

    public static boolean isChecked(WebElement check) {
        return check.getAttribute("checked") != null;
    }

    public static void checkElement(WebElement check) {
        boolean isChecked = isChecked(check);
        if (isChecked) return;
        check.click();
    }

    public static void clear(WebElement element){
        element.clear();
    }

    public static void sendText(WebElement element, String text){
        clear(element);
        element.sendKeys(text);
    }

    public static String getText(WebElement element){ return element.getText(); }

    public static String getAttribute(WebElement element, String attribute){
        return element.getAttribute(attribute);
    }

    public static void sendKeys(WebElement element, Keys key){
        element.sendKeys(key);
    }

    public static void highlighElement(WebDriver driver, WebElement element) {
        JavascriptExecutor jse = (JavascriptExecutor) driver;
        jse.executeScript("arguments[0].style.backgroundColor = '#FDFF47';", element);
    }

    public static void switchToFrame(WebDriver driver, String frameId) {
        driver.switchTo().frame(driver.findElement(By.id(frameId)));
    }

    public static void switchToMainFrame(WebDriver driver) {
        driver.switchTo().defaultContent();
        driver.switchTo().frame(driver.findElement(By.id("mainFrame")));
    }

    public static void switchToDefaultContent(WebDriver driver) {
        driver.switchTo().defaultContent();
    }
}
