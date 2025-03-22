import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import utils.BaseTest;

public class AmazonSearchTest extends BaseTest {
	@BeforeTest
	public void before() {
		 // Initialize WebDriver
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        test = extent.createTest("Google Test", "Verifying Google Home Page");
	}
	 @Test
	    public void testAmazonSearch() {
	        driver.get("https://www.amazon.in");

	        // Locate search box and enter query
	        WebElement searchBox = driver.findElement(By.id("twotabsearchtextbox"));
	        searchBox.sendKeys("iPhone 15");
	        searchBox.sendKeys(Keys.ENTER);

	        // Validate search results
	        WebElement firstResult = driver.findElement(By.xpath("//span[contains(text(), 'iPhone 15')]"));
	        Assert.assertTrue(firstResult.isDisplayed(), "Search result not found!");
	    }
}
