import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.time.Duration;

public class TyposTest {
    WebDriver driver;

    @BeforeMethod
    public void setup() {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();
    }

    @Test
    public void checkParagraphSpelling() {
        driver.get("https://the-internet.herokuapp.com/typos");
        SoftAssert softAssert = new SoftAssert();

        for (int i = 0; i < 10; i++) {
            String textActual = driver.findElement(By.xpath("//p[2]")).getText();
            String textExpected = "Sometimes you'll see a typo, other times you won't.";
            softAssert.assertEquals(textActual, textExpected);
            driver.navigate().refresh();
        }

        softAssert.assertAll();
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        driver.quit();
    }
}
