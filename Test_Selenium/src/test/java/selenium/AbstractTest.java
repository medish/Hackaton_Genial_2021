package selenium;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class AbstractTest {

    public static final int TIMEOUT_MILLIS = 1000;
    protected WebDriver driver;
    protected Map<String, Object> vars;
    protected JavascriptExecutor js;

    @BeforeEach
    public void setUp() {
        String os = System.getProperty("os.name").toLowerCase();
        if (os.contains("win"))
            System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver.exe");
        else if (os.contains("nux")) {
            System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver");
        } else if (os.contains("mac")) {
            System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver_mac");
        }
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--headless");
        driver = new ChromeDriver(options);
        js = (JavascriptExecutor) driver;
        vars = new HashMap<String, Object>();
    }

    @AfterEach
    public void tearDown() {
        driver.quit();
    }
}
