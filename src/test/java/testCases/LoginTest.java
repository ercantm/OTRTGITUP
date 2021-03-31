package testCases;

import java.util.Hashtable;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.SkipException;
import org.testng.annotations.Test;

import base.TestBase;
import utilities.TestUtil;

public class LoginTest extends TestBase {
	@Test(dataProviderClass=TestUtil.class,dataProvider="dp")
	public void loginAsWorker(Hashtable<String,String> data)throws InterruptedException {
		if(!data.get("runmode").equalsIgnoreCase("y")){
		throw new SkipException( "Test is skipping  bacouse of run mode ");
		}
		
		
		
		type(By.xpath("//input[@id='inputEmail3']"), data.get("email"));

		Reporter.log(" valid email is entered");
		type(By.cssSelector("#inputPassword3"), data.get("password"));
		Reporter.log("valid passwords is entering");
		click(By.xpath("//input[@value='SIGN IN']"));
		Reporter.log("Succefully entering main page.");
		Assert.assertTrue(isElementPresent(By.xpath("//div[@id='toast-container']")));

		
		Assert.assertTrue(isElementPresent(By.xpath(OR.getProperty("ADD_XPATH"))));

	
		
		
	
	}

}
