package selenium;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;

class Deconnexion_Test extends AbstractTest {

    public static final int TIMEOUT_MILLIS = 1000;

    @Test
    void testDeconnexion() {
        driver.get("http://localhost:4200/login");
        // driver.manage().window().setSize(new Dimension(1262, 662));
        driver.findElement(By.id("username")).click();
        driver.findElement(By.id("username")).sendKeys("admin@u-paris.fr");
        driver.findElement(By.id("password")).click();
        driver.findElement(By.id("password")).sendKeys("admin1");
        driver.findElement(By.cssSelector(".btn")).click();
        driver.findElement(By.linkText("Déconnexion")).click();
        assertEquals(driver.getCurrentUrl(), "http://localhost:4200/login");
    }
}
