package testCases;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;

import base.TestBase;

public class TestFailure extends TestBase{
	@Test
	public void failure() {
		
		driver.findElement(By.xpath("//span[normalize-space()='Quotes']")).click();
		wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//body/anms-root[1]/main[1]/pages[1]/div[2]/div[2]/div[1]/div[2]/quotes[1]/div[2]/div[1]/div[1]/div[1]/div[1]/button[1]"))));
	driver.findElement(By.xpath("//body/anms-root[1]/main[1]/pages[1]/div[2]/div[2]/div[1]/div[2]/quotes[1]/div[2]/div[1]/div[1]/div[1]/div[1]/button[1]")).click();
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//div[@class='col-sm-4 form-group inputbutton customerListDropDown float-left']//select[@class='select-dropdown ng-untouched ng-pristine ng-valid ng-star-inserted']"))));
		Select selectcostemer= new Select(driver.findElement(By.xpath("//div[@class='col-sm-4 form-group inputbutton customerListDropDown float-left']//select[@class='select-dropdown ng-untouched ng-pristine ng-valid ng-star-inserted']")));
		//selectcostemer.selectByIndex(0);
		
		selectcostemer.selectByVisibleText("Test2");
		
		
	}

}
