package testCases;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Reporter;
import org.testng.annotations.Test;

import base.TestBase;

public class CreateNewQuoteTest extends TestBase {
	// @Test(dataProviderClass = utilities.TestUtil.class, dataProvider = "dp")
	// public void CurtainTrackOnly(Hashtable<String,String> data) throws
	// InterruptedException {
	@Test
	public void CurtainTrackOnly() {
		click(By.xpath("//span[normalize-space()='Quotes']"));
// add
		waitClickable(By.xpath(
				"//body/anms-root[1]/main[1]/pages[1]/div[2]/div[2]/div[1]/div[2]/quotes[1]/div[2]/div[1]/div[1]/div[1]/div[1]/button[1]"));

		click(By.xpath(
				"//body/anms-root[1]/main[1]/pages[1]/div[2]/div[2]/div[1]/div[2]/quotes[1]/div[2]/div[1]/div[1]/div[1]/div[1]/button[1]"));
		
		
		WebElement s = driver.findElement(By.xpath(
				"//div[@class='col-sm-4 form-group inputbutton customerListDropDown float-left']//select[@class='select-dropdown ng-untouched ng-pristine ng-valid ng-star-inserted']"));

		waitVisible(s);
		WebElement ProName = driver.findElement(By.xpath(
				"//div[@class='col-sm-4 form-group inputbutton customerListDropDown float-left']//select[@class='select-dropdown ng-untouched ng-pristine ng-valid ng-star-inserted']"));

		select(ProName, "Test");

		type(By.xpath("//input[@id='curtainCost']"), "Test_KeyDRiven");

		Assert.assertTrue(isElementPresent(By.cssSelector(
				".col-sm-12.float-left > .col-sm-4.form-group.inputbutton.curtainTypeDropDown.float-left")));

		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		WebElement dp = driver.findElement(By.xpath(
				"//body/anms-root[1]/main[1]/pages[1]/div[2]/div[2]/div[1]/div[2]/add-quotes[1]/div[1]/div[1]/div[1]/form[1]/div[1]/div[4]/div[1]/dropdrown[1]/div[1]/select[1]"));
		selectbyIndex(dp, 1);
		Reporter.log("Dropdown has been selected!");

		click(By.cssSelector("input[formcontrolname='includeLaborCost']"));

		type(By.cssSelector("#laborCost"), "1500");

		WebElement costrail = driver.findElement(By.xpath(
				"//body/anms-root[1]/main[1]/pages[1]/div[2]/div[2]/div[1]/div[2]/add-quotes[1]/div[1]/div[1]/div[1]/form[1]/div[1]/div[4]/div[4]/div[5]/input[1]"));

		waitVisible(costrail);

		// creatin room trak
		type(By.cssSelector("#quantity"), "2");

		type(By.cssSelector("#area_floor"), "ShowerRoom");

		type(By.xpath("//input[@id='room']"), "124B");

		type(By.cssSelector("#track_designation"), "124-BCS");

		type(By.xpath("//input[@id='ceiling_height_feet']"), "9");

		type(By.xpath("//input[@id='ceiling_height_inches']"), "6");

		click(By.xpath("//input[@formcontrolname='ninety_degree_wall_touches']"));

		WebElement conect = driver.findElement(By.cssSelector(
				"body > anms-root:nth-child(1) > main:nth-child(1) > pages:nth-child(3) > div:nth-child(2) > div:nth-child(2) > div:nth-child(1) > div:nth-child(2) > add-quotes:nth-child(2) > div:nth-child(1) > div:nth-child(1) > div:nth-child(1) > form:nth-child(2) > div:nth-child(2) > div:nth-child(1) > div:nth-child(1) > div:nth-child(1) > div:nth-child(1) > div:nth-child(2) > div:nth-child(1) > screen2:nth-child(1) > div:nth-child(1) > div:nth-child(1) > div:nth-child(1) > div:nth-child(1) > add-track-details:nth-child(1) > div:nth-child(1) > div:nth-child(1) > div:nth-child(1) > form:nth-child(1) > div:nth-child(1) > div:nth-child(8) > dropdrown:nth-child(2) > div:nth-child(1) > select:nth-child(1)"));
		selectbyValue(conect, "1: ACT");
		WebElement ShapeType = driver.findElement(By.xpath(
				"//div[@class='col-sm-3 form-group fabricPatternListDropDown float-left']//select[@class='select-dropdown ng-untouched ng-pristine ng-valid ng-star-inserted']"));
		selectbyValue(ShapeType, "1: 1 Curve");
		type(By.xpath("//input[@id='first_feet']"), "12");
		type(By.xpath("//input[@id='first_inches']"), "6");
		type(By.xpath("//input[@id='second_feet']"), "4");
		type(By.xpath("//input[@id='second_inches']"), "8");
		WebElement Curvetype = driver.findElement(By.xpath(
				"//body/anms-root[1]/main[1]/pages[1]/div[2]/div[2]/div[1]/div[2]/add-quotes[1]/div[1]/div[1]/div[1]/form[1]/div[2]/div[1]/div[1]/div[1]/div[1]/div[2]/div[1]/screen2[1]/div[1]/div[1]/div[1]/div[1]/add-track-details[1]/div[1]/div[1]/div[1]/form[1]/div[1]/div[13]/div[1]/dropdrown[1]/div[1]/select[1]"));
		selectbyValue(Curvetype, "2: 90° 1 ft. Radius Bend");
		Assert.assertTrue(isElementPresent(By.xpath("//input[@value='Add Track']")));

	}

}