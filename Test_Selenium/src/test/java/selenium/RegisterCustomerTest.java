package selenium;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

class RegisterCustomerTest extends AbstractTest {

    @Test
    void registerOK() {
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

        // Mockito.when(customerRepository.findById("sgolouboff")).thenReturn(Optional.of(new
        // Customer()));
        // assertTrue(cs.getById("sgolouboff").isPresent());

        // assertEquals("URL attendue",driver.getCurrentUrl(),"Must go to logged
        // paged"); TODO
    }

    @Test
    void registerInvalid() throws InterruptedException {

        driver.get("http://localhost:4200/register");
        driver.findElement(By.cssSelector(".btn")).click();
        driver.findElement(By.cssSelector(".form-group")).click();

        List<WebElement> invalidFields = (driver.findElements(By.cssSelector(".is-invalid")));

        assertTrue(invalidFields.size() > 3);
        assertEquals("http://localhost:4200/register", driver.getCurrentUrl(),
                "Must not change its current page since it failed");

        // Mockito.when(customerRepository.findById("sgolouboff")).thenReturn(Optional.of(new
        // Customer()));
        // assertTrue(cs.getById("sgolouboff").isPresent());

        // assertTrue(driver.getCurrentUrl().contains("admin")); TODO
    }
}