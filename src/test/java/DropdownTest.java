import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.time.Duration;

public class DropdownTest {
    WebDriver driver;

    @BeforeMethod
    public void setup() {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();
    }

    @Test
    public void checkDropdowns() {
        driver.get("https://the-internet.herokuapp.com/dropdown");
        SoftAssert softAssert = new SoftAssert();

        Select select = new Select(driver.findElement(
                By.xpath("//select[@id='dropdown']")));
        softAssert.assertEquals(select.getOptions().size(), 3);
        select.getOptions().get(1).click();
        softAssert.assertEquals(select.getOptions().get(1).isSelected(), true);
        select.getOptions().get(2).click();
        softAssert.assertEquals(select.getOptions().get(2).isSelected(), true);
        softAssert.assertAll();
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        driver.quit();
    }
}
