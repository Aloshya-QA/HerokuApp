import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.time.Duration;

public class DynamicControlsTest {
    WebDriver driver;

    @BeforeMethod
    public void setup() {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();
    }

    @Test
    public void testCheckboxRemovalAndInputEnabling() {
        SoftAssert softAssert = new SoftAssert();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        driver.get("https://the-internet.herokuapp.com/dynamic_controls");
        driver.findElement(By.xpath("//button[text()='Remove']")).click();
        wait.until(ExpectedConditions.textToBe(By.id("message"), "It's gone!"));
        softAssert.assertFalse(driver.findElement(By.id("checkbox-example"))
                .getText()
                .contains("A checkbox"));
        softAssert.assertFalse(driver.findElement(By.xpath(
                "//form[@id='input-example']//input[@type='text']")).isEnabled());
        driver.findElement(By.xpath("//button[text()='Enable']")).click();
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath(
                "//*[text()=\"It's enabled!\"]"))));
        softAssert.assertTrue(driver.findElement(By.xpath(
                "//form[@id='input-example']//input[@type='text']")).isEnabled());
        softAssert.assertAll();
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        driver.quit();
    }
}
