package utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import java.io.File;
import java.io.IOException;
import java.time.Duration;

public class BaseTest {
    public static ExtentReports extent;
    public static ExtentTest test;
    public WebDriver driver;  // Shared driver instance

    @BeforeSuite
    public void setUpReport() {
        ExtentSparkReporter htmlReporter = new ExtentSparkReporter("test-output/ExtentReport.html");
        extent = new ExtentReports();
        extent.attachReporter(htmlReporter);
    }

    @BeforeMethod
    public void setup() {
        driver = new ChromeDriver(); // Initialize once per test
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    @AfterMethod
    public void tearDown(ITestResult result) {
        if (driver != null) { // Ensure driver is not null before quitting
            if (result.getStatus() == ITestResult.FAILURE) {
                File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
                String screenshotPath = "test-output/screenshots/" + result.getName() + ".png";
                try {
                    FileUtils.copyFile(screenshot, new File(screenshotPath));
                    test.fail("Test Failed - Screenshot attached: " + test.addScreenCaptureFromPath(screenshotPath));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            driver.quit();
        }
        extent.flush();
    }
}
