import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class NewLoginTest {
WebDriver driver;
LoginPage loginpage;
@BeforeClass
public void setup() {
	driver=new 	ChromeDriver();
	driver.get("https://www.saucedemo.com/");
	loginpage=new LoginPage(driver);
	
}
@Test
public void test1() {
	loginpage.enterusername("standard_user");
	loginpage.enterPassword("secret_sauce");
	loginpage.clickLogin();
}

}
