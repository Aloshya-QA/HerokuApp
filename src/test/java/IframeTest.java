import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.time.Duration;

import static org.testng.Assert.assertEquals;

public class IframeTest {
    WebDriver driver;

    @BeforeMethod
    public void setup() {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();
    }

    @Test
    public void checkTextFromIFrame() {
        driver.get("https://the-internet.herokuapp.com/iframe");
        WebDriver iFrame = driver.switchTo().frame(driver.findElement(By.id("mce_0_ifr")));
        assertEquals(iFrame.findElement(
                        By.tagName("p")).getText(),
                "Your content goes here.");
    }

    @Test
    public void checkTextFromFourIFrame() {
        SoftAssert softAssert = new SoftAssert();
        driver.get("https://the-internet.herokuapp.com/nested_frames");

        WebDriver topFrame = driver.switchTo().frame(driver.findElement(
                By.xpath("//frameset" +
                        "//frame[@name='frame-top']")));
        WebDriver leftFrame = topFrame.switchTo().frame(driver.findElement(
                By.xpath("//frameset[@name='frameset-middle']" +
                        "//frame[@name='frame-left']")));
        softAssert.assertEquals(leftFrame.findElement(By.tagName("body")).getText(), "LEFT");
        driver.switchTo().parentFrame();

        WebDriver middleFrame = topFrame.switchTo().frame(driver.findElement(
                By.xpath("//frameset[@name='frameset-middle']" +
                        "//frame[@name='frame-middle']")));
        softAssert.assertEquals(middleFrame.findElement(By.tagName("body")).getText(), "MIDDLE");
        driver.switchTo().parentFrame();

        WebDriver rightFrame = topFrame.switchTo().frame(driver.findElement(
                By.xpath("//frameset[@name='frameset-middle']" +
                        "//frame[@name='frame-right']")));
        softAssert.assertEquals(rightFrame.findElement(By.tagName("body")).getText(), "RIGHT");
        driver.switchTo().defaultContent();

        WebDriver bottomFrame = driver.switchTo().frame(driver.findElement(
                By.xpath("//frameset" +
                        "//frame[@name='frame-bottom']")));
        softAssert.assertEquals(bottomFrame.findElement(By.tagName("body")).getText(), "BOTTOM");
        softAssert.assertAll();
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        driver.quit();
    }
}
