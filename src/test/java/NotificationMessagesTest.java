import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.time.Duration;

public class NotificationMessagesTest {
    WebDriver driver;

    @BeforeMethod
    public void setup() {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();
    }

    @Test
    public void checkAlertMessage() {
        driver.get("https://the-internet.herokuapp.com/notification_message_rendered");
        Actions actions = new Actions(driver);

        actions.moveToElement(driver.findElement(By.linkText("Click here"))).click().perform();
        WebElement alertMassage = driver.findElement(By.cssSelector("#flash-messages"));
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(alertMassage.getText(),
                "Action unsuccesful, please try again\n" + "×");
        actions.moveToElement(driver.findElement(By.linkText("Click here"))).click().perform();
        WebElement secondAlertMassage = driver.findElement(By.cssSelector("#flash-messages"));
        softAssert.assertEquals(secondAlertMassage.getText(), "Action successful\n" + "×");
        softAssert.assertAll();
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        driver.quit();
    }
}
