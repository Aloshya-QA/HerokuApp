import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class SortableDataTablesTest {
    WebDriver driver;

    @BeforeMethod
    public void setup() {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();
    }

    @Test
    public void checkTableCells() {
        driver.get("https://the-internet.herokuapp.com/tables");

        List<WebElement> rows = driver.findElements(
                By.xpath("//table[@id='table1']/tbody/tr"));
        List<WebElement> cells = new ArrayList<>();

        for (WebElement row : rows) {
            cells = row.findElements(By.xpath("//td"));
        }

        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(cells.get(23).getText(), "edit delete");
        softAssert.assertEquals(cells.get(0).getText(), "Smith");
        softAssert.assertEquals(cells.get(6).getText(), "Bach");
        softAssert.assertEquals(cells.get(12).getText(), "Doe");
        softAssert.assertEquals(cells.get(18).getText(), "Conway");
        softAssert.assertEquals(cells.get(1).getText(), "John");
        softAssert.assertAll();
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        driver.quit();
    }
}
