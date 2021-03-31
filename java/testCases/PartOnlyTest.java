package testCases;

import java.util.Hashtable;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.Test;

import base.TestBase;

import pages.PartOnly;
import utilities.TestUtil;

public class PartOnlyTest extends TestBase {
	PartOnly p;

	@Test()
	public void partOnlyTest() throws InterruptedException {
		
		
		
		p=new PartOnly(TestBase.driver);
		//click(p.quotebtm());
		waitClickable(p.addbtm());
		click(p.addbtm());
		waitVisible(p.chooseCostemer());
		selectbyValue(p.chooseCostemer(), "219: Test");
		type(p.chooseProjectName(),"PageObjectDataDriven");
		
		Assert.assertTrue(isElementPresent(p.type));
		selectbyValue(p.chooseProjectType(),"2: Parts Only");
		click(p.addcurve());
		selectbyIndex(p.chooseurve(), 2);
		type(p.AddCurvequantity(), "10");
		click(p.addedcurve());
		waitVisible(p.verifyCurve());
		Assert.assertTrue(isElementPresent(p.verify));
		

	}
}