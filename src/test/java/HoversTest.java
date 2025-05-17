import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.time.Duration;
import java.util.List;

public class HoversTest {
    WebDriver driver;

    @BeforeMethod
    public void setup() {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();
    }

    @Test
    public void checkHovers() {
        driver.get("https://the-internet.herokuapp.com/hovers");
        Actions actions = new Actions(driver);
        SoftAssert softAssert = new SoftAssert();

        List<WebElement> arrayUsers = driver.findElements(By.cssSelector(
                "#content > div > div"));
        String[] expectedValues = new String[]{"user1", "user2", "user3"};

        for (int i = 0; i < arrayUsers.size(); i++) {
            actions.moveToElement(arrayUsers.get(i)).perform();
            String text = arrayUsers.get(i).findElement(By.tagName("h5")).getText();
            softAssert.assertEquals(
                    text.substring(text.indexOf(' ') + 1), expectedValues[i]);

            WebElement link = arrayUsers.get(i).findElement(By.tagName("a"));
            actions.click(link).perform();
            String errorText = driver.getPageSource();
            Assert.assertNotNull(errorText);
            softAssert.assertEquals(
                    errorText.contains("Not Found"), false);
            driver.navigate().back();
        }

        softAssert.assertAll();
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        driver.quit();
    }
}
