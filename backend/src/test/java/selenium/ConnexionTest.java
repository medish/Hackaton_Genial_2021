package selenium;


import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertTrue;

public class ConnexionTest {
    private WebDriver driver;
    private Map<String, Object> vars;
    JavascriptExecutor js;

    @Before
    public void setUp() {
        String os = System.getProperty("os.name").toLowerCase();
        if(os.contains("win"))
            System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver.exe");
        else if (os.contains("nux")){
            System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver");
        }else if(os.contains("mac")){
            //Rajouter le webdriver mac
        }
        driver = new ChromeDriver();
        js = (JavascriptExecutor) driver;
        vars = new HashMap<String, Object>();
    }

    @After
    public void tearDown() {
        driver.quit();
    }
    @Test
    public void connexion() {
        driver.manage().deleteAllCookies();
        driver.get("http://localhost:4200/login");
        driver.manage().window().setSize(new Dimension(1280, 680));
        driver.findElement(By.id("username")).click();
        driver.findElement(By.id("username")).sendKeys("sdsdfsdfd");
        driver.findElement(By.id("password")).click();
        driver.findElement(By.id("password")).sendKeys("sdfsdfsdfsdfsdfsdf");
        driver.findElement(By.cssSelector(".btn")).click();
        assertTrue(driver.getCurrentUrl().contains("admin"));
    }
}
