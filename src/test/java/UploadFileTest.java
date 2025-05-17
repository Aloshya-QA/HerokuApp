import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.File;
import java.time.Duration;

import static org.testng.Assert.assertEquals;

public class UploadFileTest {
    WebDriver driver;

    @BeforeMethod
    public void setup() {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();
    }

    @Test
    public void checkUploadedFileName() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        File file = new File("src/test/java/TyposTest.java");
        driver.get("https://the-internet.herokuapp.com/upload");
        driver.findElement(By.xpath(
                "//input[@type='file']")).sendKeys(file.getAbsolutePath());
        driver.findElement(By.xpath("//input[@type='submit']")).click();
        wait.until(ExpectedConditions.textToBe(
                By.xpath("//div[@class='example']//h3"), "File Uploaded!"));
        assertEquals(driver.findElement(By.id("uploaded-files")).getText(), "TyposTest.java");
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        driver.quit();
    }
}
