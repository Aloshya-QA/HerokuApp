import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.time.Duration;

public class CheckboxesTest {
    WebDriver driver;

    @BeforeMethod
    public void setup() {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();
    }

    @Test
    public void toggleCheckboxes() {
        driver.get("https://the-internet.herokuapp.com/checkboxes");
        SoftAssert softAssert = new SoftAssert();

        WebElement inputFirst = driver.findElement(By.xpath(
                "//form[@id='checkboxes']/input[1]"));
        WebElement inputSecond = driver.findElement(By.xpath(
                "//form[@id='checkboxes']/input[2]"));

        softAssert.assertEquals(inputFirst.isSelected(), false);
        inputFirst.click();
        softAssert.assertEquals(inputFirst.isSelected(), true);

        softAssert.assertEquals(inputSecond.isSelected(), true);
        inputSecond.click();
        softAssert.assertEquals(inputSecond.isSelected(), false);
        softAssert.assertAll();
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        driver.quit();
    }
}
