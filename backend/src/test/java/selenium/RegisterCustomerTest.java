package selenium;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import server.repositories.UserRepository;
import server.services.UserService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


public class RegisterCustomerTest {
    private WebDriver driver;
    private Map<String, Object> vars;
    JavascriptExecutor js;

    @Mock
    UserRepository customerRepository;

    @InjectMocks
    private UserService cs;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        String os = System.getProperty("os.name").toLowerCase();
        if (os.contains("win"))
            System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver.exe");
        else if (os.contains("nux")) {
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
    public void registerOK() {
        driver.get("http://localhost:4200/register");
        assertTrue(driver.findElement(By.id("username")).isDisplayed());
        assertTrue(driver.findElement(By.id("username")).isEnabled());
        driver.findElement(By.id("username")).sendKeys("golouboff");
        assertTrue(driver.findElement(By.id("firstname")).isDisplayed());
        assertTrue(driver.findElement(By.id("firstname")).isEnabled());
        driver.findElement(By.id("firstname")).sendKeys("seb");
        assertTrue(driver.findElement(By.id("email")).isDisplayed());
        assertTrue(driver.findElement(By.id("email")).isEnabled());
        driver.findElement(By.id("email")).sendKeys("seb@seb.com");
        assertTrue(driver.findElement(By.id("password")).isDisplayed());
        assertTrue(driver.findElement(By.id("password")).isEnabled());
        driver.findElement(By.id("password")).sendKeys("sdgsdgsdgsdgsdgsdgsd");
        driver.findElement(By.cssSelector(".btn")).click();
        driver.findElement(By.cssSelector(".form-group")).click();
        
        //Mockito.when(customerRepository.findById("sgolouboff")).thenReturn(Optional.of(new Customer()));
        //assertTrue(cs.getById("sgolouboff").isPresent());

        //assertEquals("URL attendue",driver.getCurrentUrl(),"Must go to logged paged"); TODO
    }

    @Test
    public void registerInvalid() throws InterruptedException {


        driver.get("http://localhost:4200/register");
        driver.findElement(By.cssSelector(".btn")).click();
        driver.findElement(By.cssSelector(".form-group")).click();

        List<WebElement> invalidFields = (driver.findElements(By.cssSelector(".is-invalid")));

        assertTrue(invalidFields.size()>3);
        assertEquals("http://localhost:4200/register",driver.getCurrentUrl(),"Must not change its current page since it failed");

        //Mockito.when(customerRepository.findById("sgolouboff")).thenReturn(Optional.of(new Customer()));
        //assertTrue(cs.getById("sgolouboff").isPresent());

        //assertTrue(driver.getCurrentUrl().contains("admin")); TODO
    }
}