import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import utils.BaseTest;
import utils.ExcelUtils;
import java.io.IOException;

public class LoginTest extends BaseTest {

    @BeforeMethod
    public void setupTest() {
        driver.get("https://www.saucedemo.com/"); // Use inherited driver
    }

    @Test(dataProvider = "loginData")
    public void loginTest(String username, String password) {
        driver.findElement(By.id("user-name")).sendKeys(username);
        driver.findElement(By.id("password")).sendKeys(password);
        driver.findElement(By.id("login-button")).click();

        // Add validation
        System.out.println("Login attempted with: " + username + " | " + password);
    }

    @DataProvider(name = "loginData")
    public Object[][] getLoginData() throws IOException {
        String filePath = "D:\\Selenium-Automation-Framework\\Selenium_Automation_Framework\\TestData\\Book1.xlsx";
        ExcelUtils.loadExcel(filePath, "Sheet1");

        int rowCount = ExcelUtils.getRowCount();
        Object[][] data = new Object[rowCount - 1][2];

        for (int i = 1; i < rowCount; i++) { // Skip header row
            data[i - 1][0] = ExcelUtils.getCellData(i, 0); // Username
            data[i - 1][1] = ExcelUtils.getCellData(i, 1); // Password
        }

        ExcelUtils.closeExcel();
        return data;
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) { // Prevent NullPointerException
            driver.quit();
        }
    }
}
