package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import base.TestBase;
public class LoginPage {
	By emailAdress = By.cssSelector("#inputEmail3");
	By passv = By.cssSelector("#inputPassword3");
	By sigIn = By.cssSelector("input[value='SIGN IN']");
	By doNotR = By.cssSelector("input[value='SIGN IN']");
	public static By verify=By.xpath("//div[@id='toast-container']");

	WebDriver driver;

	public LoginPage(WebDriver driver) {
		this.driver = driver;
	}

	public WebElement email() {
		return driver.findElement(emailAdress);
	}

	public WebElement password() {
		return driver.findElement(passv);
	}

	public WebElement signup() {
		return driver.findElement(sigIn);
	}

	public WebElement doNotrememberPassword() {
		return driver.findElement(doNotR);
	}
	public WebElement verifyLogin() {
		return driver.findElement(verify);
	}
}
