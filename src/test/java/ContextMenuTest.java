import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;

import static org.testng.Assert.assertEquals;

public class ContextMenuTest {
    WebDriver driver;

    @BeforeMethod
    public void setup() {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();
    }

    @Test
    public void checkAlertText() {
        Actions actions = new Actions(driver);
        driver.get("https://the-internet.herokuapp.com/context_menu");
        actions.contextClick(driver.findElement(By.id("hot-spot"))).build().perform();
        String alertMessage = driver.switchTo().alert().getText();
        assertEquals(alertMessage, "You selected a context menu");
        driver.switchTo().alert().accept();
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        driver.quit();
    }
}
