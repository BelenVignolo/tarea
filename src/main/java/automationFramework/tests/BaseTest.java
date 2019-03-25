package automationFramework.tests;

import automationFramework.utils.GetProperties;
import automationFramework.utils.datatypes.BrowserType;
import org.openqa.selenium.UnexpectedAlertBehaviour;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import static automationFramework.utils.Utils.applyDefaultIfMissing;

public class BaseTest {

    protected static WebDriver driver;


    protected static String environment = applyDefaultIfMissing(System.getProperty("environment"), "QA");
    private static GetProperties properties = new GetProperties(environment);
    private static String browser = properties.getString("BROWSER").toUpperCase();
    private static String chromedriver = properties.getString("CHROMEDRIVER_DIR");
    private static String iedriver = properties.getString("IEDRIVER_DIR");

    @BeforeMethod
    public void setUp(Method method) throws Exception {
        BrowserType browserType = BrowserType.valueOf(browser.toUpperCase());
        DesiredCapabilities capabilities;

        switch (browserType) {
            case FIREFOX:
                capabilities = DesiredCapabilities.firefox();
                driver = new FirefoxDriver(capabilities);
                break;
            case CHROME:
                System.setProperty("webdriver.chrome.driver", chromedriver);
                capabilities = DesiredCapabilities.chrome();
                ChromeOptions options = new ChromeOptions();
                options.addArguments("--disable-extensions");
                options.addArguments("--start-maximized");
                options.addArguments("disable-infobars");
                Map<String, Object> prefs = new HashMap<String, Object>();
                prefs.put("credentials_enable_service", false);
                prefs.put("profile.password_manager_enabled", false);
                options.setExperimentalOption("prefs", prefs);
                capabilities.setCapability(ChromeOptions.CAPABILITY, options);
                capabilities.setCapability(CapabilityType.UNEXPECTED_ALERT_BEHAVIOUR, UnexpectedAlertBehaviour.ACCEPT);
                int i = 1;
                while(true) {
                    try {
                        System.out.println("Abriendo Chrome intento " + i);
                        driver = new ChromeDriver(capabilities);
                        break;
                    } catch (Exception e) { i++; }
                }
                break;
            case IE:
                System.setProperty("webdriver.ie.driver", iedriver);
                capabilities = DesiredCapabilities.internetExplorer();
                capabilities.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
                capabilities.setJavascriptEnabled(true);
                capabilities.setCapability("requireWindowFocus", false);
                capabilities.setCapability("enablePersistentHover", false);
                capabilities.setCapability("ignoreProtectedModeSettings", true);
                capabilities.setCapability(InternetExplorerDriver.ENABLE_ELEMENT_CACHE_CLEANUP, true);
                capabilities.setCapability(InternetExplorerDriver.IE_ENSURE_CLEAN_SESSION, true);
                driver = new InternetExplorerDriver(capabilities);
                break;
            default:
                capabilities = DesiredCapabilities.firefox();
                driver = new FirefoxDriver(capabilities);
        }

        try {
            driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
            navigateToHome();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @AfterMethod
    public void after(Method method) {
        driver.close();
    }

    private void navigateToHome() {
        String BASE_URL = properties.getString("BASE_URL");
        driver.get(BASE_URL);
    }
}
