import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.time.Duration;
import java.util.List;

public class CheckboxesTest {
    WebDriver driver;

    @BeforeMethod
    public void setup() {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();
    }

    @Test
    public void test() {
        driver.get("https://the-internet.herokuapp.com/checkboxes");

        WebElement form = driver.findElement(By.xpath("//form[@id='checkboxes']"));
        List<WebElement> checkboxes = form.findElements(By.xpath("//input[@type='checkbox']"));

        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(checkboxes.get(0).isSelected(), false);
        checkboxes.get(0).click();
        softAssert.assertEquals(checkboxes.get(0).isSelected(), true);

        softAssert.assertEquals(checkboxes.get(1).isSelected(), true);
        checkboxes.get(1).click();
        softAssert.assertEquals(checkboxes.get(0).isSelected(), false);
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        driver.quit();
    }
}
