package selenium;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

class ManualTest extends AbstractTest {

    public static final int TIMEOUT_MILLIS = 250;

    @Test
    void lol() throws InterruptedException {
        driver.get("http://localhost:4200/login");
        driver.findElement(By.id("username")).click();
        driver.findElement(By.id("username")).sendKeys("admin@u-paris.fr");
        driver.findElement(By.id("password")).click();
        driver.findElement(By.id("username")).click();
        driver.findElement(By.id("password")).click();
        driver.findElement(By.id("password")).sendKeys("admin1");
        driver.findElement(By.id("password")).sendKeys(Keys.ENTER);
        wait(TIMEOUT_MILLIS);
        driver.findElement(By.cssSelector(".ms-5")).click();
        wait(TIMEOUT_MILLIS);
        {
            WebElement element = driver.findElement(By.cssSelector(".card-creneau"));
            Actions builder = new Actions(driver);
            builder.moveToElement(element).clickAndHold().perform();
        }
        {
            WebElement element = driver.findElement(By.cssSelector(".row:nth-child(3)"));
            Actions builder = new Actions(driver);
            builder.moveToElement(element).perform();
            builder.moveToElement(element).release().perform();
        }

        driver.findElement(By.cssSelector(".row:nth-child(3)")).click();

        wait(TIMEOUT_MILLIS);

        driver.findElement(By.id("department")).click();
        {
            WebElement element = driver.findElement(By.id("department"));
            Actions builder = new Actions(driver);
            builder.moveToElement(element).release().perform();
        }
        {
            WebElement dropdown = driver.findElement(By.id("department"));
            dropdown.findElement(By.xpath("//option[. = ' Informatique ']")).click();
        }
        driver.findElement(By.id("department")).click();

        wait(TIMEOUT_MILLIS);

        driver.findElement(By.id("degree")).click();
        {
            WebElement element = driver.findElement(By.id("degree"));
            Actions builder = new Actions(driver);
            builder.moveToElement(element).release().perform();
        }
        {
            WebElement dropdown = driver.findElement(By.id("degree"));
            dropdown.findElement(By.xpath("//option[. = ' Master GENIAL M2 ']")).click();
        }

        wait(TIMEOUT_MILLIS);

        driver.findElement(By.id("degree")).click();

        wait(TIMEOUT_MILLIS);

        driver.findElement(By.id("class")).click();
        {
            WebElement element = driver.findElement(By.id("class"));
            Actions builder = new Actions(driver);
            builder.moveToElement(element).release().perform();
        }
        {
            WebElement dropdown = driver.findElement(By.id("class"));
            dropdown.findElement(By.xpath("//option[. = ' Algorithmique ']")).click();
        }

        wait(TIMEOUT_MILLIS);

        driver.findElement(By.id("class")).click();

        wait(5000);
        /*
        */
    }

    private void wait(int timeoutMillis) throws InterruptedException {
        synchronized (driver) {
            driver.wait(timeoutMillis);
        }
    }
}
