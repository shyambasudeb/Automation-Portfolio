import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage {
WebDriver driver;
public LoginPage(WebDriver driver) {
	this.driver=driver;
	
}

private By username=By.id("user-name");
private By passWord=By.id("password");
private By login=By.id("login-button");

public void enterusername(String name) {
	driver.findElement(username).sendKeys(name);
}
public void enterPassword(String password) {
	driver.findElement(passWord).sendKeys(password);
}
public void clickLogin() {
	driver.findElement(login).click();
}
}
