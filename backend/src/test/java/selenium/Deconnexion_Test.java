package selenium;



import org.drools.core.command.assertion.AssertEquals;
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

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Deconnexion_Test {
    public static final int TIMEOUT_MILLIS = 1000;
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
    public void testDeconnexion() {
        driver.get("http://localhost:4200/login");
        //driver.manage().window().setSize(new Dimension(1262, 662));
        driver.findElement(By.id("username")).click();
        driver.findElement(By.id("username")).sendKeys("sdfsdfdsfsdf");
        driver.findElement(By.id("password")).click();
        driver.findElement(By.id("password")).sendKeys("sdfsdfdsfsdfdsfdsfs");
        driver.findElement(By.cssSelector(".btn")).click();
        driver.findElement(By.linkText("Déconnexion")).click();
        assertEquals(driver.getCurrentUrl(),"http://localhost:4200/login");
    }
}

