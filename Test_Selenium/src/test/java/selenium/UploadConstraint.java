package selenium;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class UploadConstraint {
  public static final int TIMEOUT_MILLIS = 250;
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
    wait(TIMEOUT_MILLIS*5);
    //driver.findElement(By.cssSelector("div:nth-child(4) > .btn")).click();
    //driver.findElement(By.cssSelector("#constraintTimeRoom0 > td:nth-child(2)")).click();
    //driver.findElement(By.id("constraintTimeRoom0")).click();
    wait(5000);
  }

  private void wait(int timeoutMillis) throws InterruptedException {
    synchronized (driver) {
      driver.wait(timeoutMillis);
    }
  }
}
