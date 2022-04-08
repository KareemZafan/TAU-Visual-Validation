package base;

import com.google.common.io.Files;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import the_internet.herkuapp.pages.HomePage;
import utils.CookieManager;
import utils.EyesManager;
import utils.WindowManager;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class BaseTests {
    protected static Properties properties;
    protected static WebDriver driver;
    protected static EyesManager eyesManager;
    protected HomePage homePage;

    @BeforeClass
    public void setUp() {
        try {
            WebDriverManager.firefoxdriver().setup();
            driver = new FirefoxDriver(getFirefoxOptions());
            properties = System.getProperties();
            properties.load(new FileInputStream(new File("./src/main/resources/config.properties")));
            eyesManager = new EyesManager(driver, "Automation Book Store");

        } catch (IOException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
    }

    @BeforeMethod
    public void goHome() {
        driver.get(properties.getProperty("sites.invoices.url"));
        homePage = new HomePage(driver);
    }

    @AfterClass
    public void tearDown() {
        driver.quit();
        eyesManager.abort();
    }

    @AfterMethod
    public void recordFailure(ITestResult result) {
        if (ITestResult.FAILURE == result.getStatus()) {
            var camera = (TakesScreenshot) driver;
            File screenshot = camera.getScreenshotAs(OutputType.FILE);
            try {
                Files.move(screenshot, new File("resources/screenshots/" + result.getName() + ".png"));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public WindowManager getWindowManager() {
        return new WindowManager(driver);
    }

    private FirefoxOptions getFirefoxOptions() {
        FirefoxOptions options = new FirefoxOptions();
        options.addArguments("disable-infobars");
        //options.setHeadless(true);
        return options;
    }

    public CookieManager getCookieManager() {
        return new CookieManager(driver);
    }


}
