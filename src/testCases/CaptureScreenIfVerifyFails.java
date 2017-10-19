package testCases;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import java.io.*;

public class CaptureScreenIfVerifyFails
{
    public WebDriver driver;
    @Test
    public void searchItemsFailure() throws Exception {
        driver = new FirefoxDriver();

        WebDriverWait wait = new WebDriverWait(driver,120);
        driver.get("https://www.daraz.com.bd/");
        driver.findElement(By.xpath("html/body/div[2]/div/div[1]/div/i")).click();
        driver.findElement(By.id("header-search-input")).sendKeys("Iphone 8");
        driver.findElement(By.id("header-search-submit")).click();
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("html/body/main/section/header/h1/span")));
        WebElement element = driver.findElement(By.xpath("html/body/main/section/header/h1/span"));
        String getText = element.getText();
        System.out.println(getText);
        Assert.assertEquals(getText, " 55 products found");

    }
    @AfterMethod
    public void takeScreenShotOnFailure(ITestResult testResult) throws IOException
    {
        EventFiringWebDriver eDriver = new EventFiringWebDriver(driver);

        if (testResult.getStatus() == ITestResult.FAILURE)
        {
            File srcFile = eDriver.getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(srcFile, new File("ErrorScereenShot.jpg"));
        }
        driver.close();
    }


}