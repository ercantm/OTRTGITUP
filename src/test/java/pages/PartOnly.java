package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import base.TestBase;

public class PartOnly {

	By quote = By.xpath("//span[normalize-space()='Quotes']");
	By add = By.xpath(
			"//body/anms-root[1]/main[1]/pages[1]/div[2]/div[2]/div[1]/div[2]/quotes[1]/div[2]/div[1]/div[1]/div[1]/div[1]/button[1]");
	
	
	
	
	
	By addCostemer = By.xpath("//div[@class='col-sm-4 form-group inputbutton customerListDropDown float-left']//select[@class='select-dropdown ng-untouched ng-pristine ng-valid ng-star-inserted']");
	By projectName = By.xpath(
			"//input[@id='curtainCost']");
	
	
	public static By type = By.xpath(
			"//body/anms-root[1]/main[1]/pages[1]/div[2]/div[2]/div[1]/div[2]/add-quotes[1]/div[1]/div[1]/div[1]/form[1]/div[1]/div[4]/div[1]/dropdrown[1]/div[1]/select[1]");
	
	
	
	
	
	
	
	
	
	
	By curvesAdd = By.xpath("//div[@class='curvesHeadingContainer']//button[@type='button']");
	By curve = By.xpath(
"//body/anms-root[1]/main[1]/pages[1]/div[2]/div[2]/div[1]/div[2]/add-quotes[1]/div[1]/div[1]/div[1]/form[1]/div[2]/div[1]/div[1]/div[1]/div[1]/div[2]/quote-curve[1]/div[1]/div[3]/ngx-smart-modal[1]/div[1]/div[1]/div[1]/div[1]/div[2]/add-quote-curve[1]/div[1]/div[1]/div[1]/form[1]/div[1]/div[1]/dropdrown[1]/div[1]/select[1]");
	By quantity = By.xpath("//input[@id='linearLengthFeet']");
	By curveCreate = By.xpath(
			"/html[1]/body[1]/anms-root[1]/main[1]/pages[1]/div[2]/div[2]/div[1]/div[2]/add-quotes[1]/div[1]/div[1]/div[1]/form[1]/div[2]/div[1]/div[1]/div[1]/div[1]/div[2]/quote-curve[1]/div[1]/div[3]/ngx-smart-modal[1]/div[1]/div[1]/div[1]/div[1]/div[2]/add-quote-curve[1]/div[1]/div[1]/div[1]/form[1]/div[1]/div[5]/input[2]");
	public static By verify = By.xpath("//i[@class='btn btn-danger btn-xs fa fa-trash-o']");
	WebDriver driver;

	public PartOnly(WebDriver driver) {
		this.driver = driver;
	}

	public WebElement quotebtm() {
		return driver.findElement(quote);
	}

	public WebElement addbtm() {
		return driver.findElement(add);
	}
	public WebElement toaddCostemer() {
		return driver.findElement(addCostemer);
	}
	public WebElement chooseCostemer() {
		return driver.findElement(type);
	}

	public WebElement chooseProjectName() {
		return driver.findElement(projectName);
	}

	public WebElement chooseProjectType() {
		return driver.findElement(type);
	}

	public WebElement addcurve() {
		return driver.findElement(curvesAdd);
	}

	public WebElement chooseurve() {
		return driver.findElement(curve);
	}

	public WebElement AddCurvequantity() {
		return driver.findElement(quantity);
	}

	public WebElement addedcurve() {
		return driver.findElement(curveCreate);
	}

	public WebElement verifyCurve() {
		return driver.findElement(verify);
	}

}
