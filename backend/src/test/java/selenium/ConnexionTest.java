package selenium;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertTrue;


public class ConnexionTest {
    private WebDriver driver;
    private Map<String, Object> vars;
    JavascriptExecutor js;

    @BeforeEach
    public void setUp() {
        String os = System.getProperty("os.name").toLowerCase();
        if(os.contains("win"))
            System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver.exe");
        else if (os.contains("nux")){
            System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver");
        }else if(os.contains("mac")){
            System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver_mac");
        }
        driver = new ChromeDriver();
        js = (JavascriptExecutor) driver;
        vars = new HashMap<String, Object>();
    }

    @AfterEach
    public void tearDown() {
        driver.quit();
    }
    @Test
    public void connexion() throws InterruptedException {
        driver.manage().deleteAllCookies();
        driver.get("http://localhost:4200/login");
        driver.manage().window().setSize(new Dimension(1280, 680));
        driver.findElement(By.id("username")).click();
        driver.findElement(By.id("username")).sendKeys("sdsdfsdfd");
        driver.findElement(By.id("password")).click();
        driver.findElement(By.id("password")).sendKeys("sdfsdfsdfsdfsdfsdf");
        driver.findElement(By.cssSelector(".btn")).click();

        synchronized (driver){
            driver.wait(2000);
        }
        assertTrue(driver.getCurrentUrl().contains("admin"));
    }
}
