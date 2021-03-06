package selenium;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;

class TestModifUser extends AbstractTest {
    public static final int TIMEOUT_MILLIS = 1000;

    @Test
    // Modifie la valeur du firstname + username + email du 2e utilisateur de la
    // liste en rajoutant 1
    // A son ancienne valeur

    void testmodifuser() throws InterruptedException {
        driver.get("http://localhost:4200/login");
//        driver.manage().window().setSize(new Dimension(1262, 662));
        driver.findElement(By.id("username")).click();
        driver.findElement(By.id("username")).sendKeys("admin@u-paris.fr");
        driver.findElement(By.id("password")).click();
        driver.findElement(By.id("password")).sendKeys("admin1");
        driver.findElement(By.cssSelector(".btn")).click();
        synchronized (driver) {
            driver.wait(TIMEOUT_MILLIS);
        }
        driver.findElement(By.linkText("Utilisateurs")).click();
        driver.findElement(By.cssSelector("tr:nth-child(2) .btn-warning")).click();
        synchronized (driver) {
            driver.wait(TIMEOUT_MILLIS);
        }
        String[] old_values = new String[3];
        driver.findElement(By.id("username")).click();
        String tmp = driver.findElement(By.id("username")).getAttribute("value");
        System.out.println(tmp);
        // Ancienne valeur de username
        old_values[0] = tmp;

        driver.findElement(By.id("username")).sendKeys("1");
        driver.findElement(By.id("firstname")).click();
        tmp = driver.findElement(By.id("firstname")).getAttribute("value");
        // Ancienne valeur de firstname
        old_values[1] = tmp;

        System.out.println(tmp);
        driver.findElement(By.id("firstname")).sendKeys("1");

        driver.findElement(By.id("email")).click();
        tmp = driver.findElement(By.id("email")).getAttribute("value");
        System.out.println(tmp);

        // Ancienne valeur de l'email
        old_values[2] = tmp;

        driver.findElement(By.id("email")).sendKeys("1");
        driver.findElement(By.cssSelector(".btn-primary")).click();

        synchronized (driver) {
            driver.wait(TIMEOUT_MILLIS * 2);
        }
        driver.findElement(By.cssSelector("tr:nth-child(2) .btn-warning")).click();
        synchronized (driver) {
            driver.wait(TIMEOUT_MILLIS * 2);
        }

        // Verification
        assertEquals(driver.findElement(By.id("username")).getAttribute("value"), old_values[0] + "1");
        assertEquals(driver.findElement(By.id("firstname")).getAttribute("value"), old_values[1] + "1");
        assertEquals(driver.findElement(By.id("email")).getAttribute("value"), old_values[2] + "1");

        synchronized (driver) {
            driver.wait(TIMEOUT_MILLIS);
        }
    }
}
