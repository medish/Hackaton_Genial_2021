package selenium;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;

class ConnexionTest extends AbstractTest {

    public static final int TIMEOUT_MILLIS = 1000;

    @Test
    void connexion() throws InterruptedException {
        driver.manage().deleteAllCookies();
        driver.get("http://localhost:4200/login");
        // driver.manage().window().setSize(new Dimension(1280, 680));
        driver.findElement(By.id("username")).click();
        driver.findElement(By.id("username")).sendKeys("admin@u-paris.fr");
        driver.findElement(By.id("password")).click();
        driver.findElement(By.id("password")).sendKeys("admin1");
        driver.findElement(By.cssSelector(".btn")).click();

        synchronized (driver) {
            driver.wait(TIMEOUT_MILLIS);
        }
        assertTrue(driver.getCurrentUrl().contains("admin"));
    }
}
