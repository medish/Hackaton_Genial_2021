package selenium;

import java.io.File;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;

class UploadConstraint extends AbstractTest {

    public static final int TIMEOUT_MILLIS = 250;

    @Test
    public void uploadConstraint() throws InterruptedException {
        driver.get("http://localhost:4200/login");
        driver.findElement(By.id("username")).click();
        driver.findElement(By.id("username")).sendKeys("admin@u-paris.fr");
        driver.findElement(By.id("password")).click();
        driver.findElement(By.id("password")).sendKeys("admin1");
        driver.findElement(By.cssSelector(".btn")).click();
        wait(TIMEOUT_MILLIS);
        driver.findElement(By.cssSelector(".ms-5")).click();
        driver.findElement(By.cssSelector("div:nth-child(1) > .btn-primary")).click();
        wait(TIMEOUT_MILLIS);

        File file = new File("src/test/resources/inp1_time_room.csv");
        driver.findElement(By.id("file")).sendKeys(file.getAbsolutePath());
        wait(TIMEOUT_MILLIS * 5);
        // driver.findElement(By.cssSelector("div:nth-child(4) > .btn")).click();
        // driver.findElement(By.cssSelector("#constraintTimeRoom0 >
        // td:nth-child(2)")).click();
        // driver.findElement(By.id("constraintTimeRoom0")).click();
        wait(5000);
    }

    private void wait(int timeoutMillis) throws InterruptedException {
        synchronized (driver) {
            driver.wait(timeoutMillis);
        }
    }
}
