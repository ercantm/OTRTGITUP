package base;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;

import org.openqa.selenium.By;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.opera.OperaDriver;
import org.openqa.selenium.opera.OperaOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.asserts.SoftAssert;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

//import com.relevantcodes.extentreports.LogStatus;

import io.github.bonigarcia.wdm.WebDriverManager;
import pages.TopMenu;
import utilities.ExcelReader;
import utilities.ExtentManager;
import utilities.TestUtil;

public class TestBase {

	private static final String False = null;
	/*
	 * webdriver propertiresd logs extentreports DP Excel Mail
	 * 
	 * 
	 */
	public static TopMenu menu;
	public static SoftAssert soft = new SoftAssert();
	public static ExtentReports rep = ExtentManager.getInstance();
	public static ExtentTest test;
	public static String browser;
	public static WebDriver driver;
	public static Properties config;
	public static Properties OR;
	public static Logger log = Logger.getLogger("devpinoyLogger");
	public static ExcelReader excel = new ExcelReader(
			"C:\\Users\\ercan\\Documents\\workspaces\\eclipse-ide-for-java-developers\\PageobjectAproach\\src\\test\\resources\\excel\\data.xlsx");
	public static WebDriverWait wait;

	@SuppressWarnings("deprecation")
	@BeforeSuite

	public void setUP() throws IOException {
		if (driver == null) {
			
			// menu= new TopMenu(driver); using consantraints
			config = new Properties();
			OR = new Properties();
			File f = new File(
					System.getProperty("user.dir") + "\\src\\test\\resources\\propereties\\Config.properties");
			log.debug(" file are lounching");
			FileInputStream fis = new FileInputStream(f);
			config.load(fis);

			File f2 = new File(System.getProperty("user.dir") + "\\src\\test\\resources\\propereties\\OR.properties");
			fis = new FileInputStream(f2);
			OR.load(fis);
            // this is for jenkin envrimonment 
			if (System.getenv("browser") != null && (!System.getenv("browser").isEmpty())) {
				browser = System.getenv("browser");

			}

			else {
				browser = config.getProperty("browser");
			}

			config.setProperty("browser", browser);

			if (browser.toString().equalsIgnoreCase("firefox")) {
				// System.setProperty("webdriver.gecko.driver",
				// "C:\\Users\\ercan\\Driver\\Gecodriver\\geckodriver.exe");
				WebDriverManager.firefoxdriver().setup();
				FirefoxProfile customProfile = new FirefoxProfile();
				customProfile.setPreference("dom.disable_beforeunload", true);
				// customProfile.set_preference("dom.disable_open_during_load", False);
				driver = new FirefoxDriver((Capabilities) customProfile);
				// driver = new FirefoxDriver();
				log.debug(" firefox starting");
			}
			if (browser.toString().equalsIgnoreCase("ei")) {
				// log.debug("inter explorer is startting ");
				DesiredCapabilities capabilities = DesiredCapabilities.internetExplorer();
				capabilities.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS,
						true);
				capabilities.setCapability(InternetExplorerDriver.IGNORE_ZOOM_SETTING, true);
				WebDriverManager.iedriver().setup();
				// System.setProperty("webdriver.ie.driver",
				// "C:\\Users\\ercan\\Driver\\IEDriver\\IEDriverServer.exe");
				driver = new InternetExplorerDriver(capabilities);
				log.debug(" Internet Explorer  starting");
			}
			if (browser.toString().equalsIgnoreCase("chrome")) {

				WebDriverManager.chromedriver().setup();
				Map<String, Object> prefs = new HashMap<String, Object>();
				prefs.put("profile.default_content_setting_values.notifications", 2);
				prefs.put("credentials_enable_service", false);
				prefs.put("profile.password_manager_enabled", false);
				ChromeOptions options = new ChromeOptions();
				options.setExperimentalOption("prefs", prefs);
				options.addArguments("--disable-extensions");
				options.addArguments("--disable-infobars");

				driver = new ChromeDriver(options);

			}
			if (browser.toString().equalsIgnoreCase("edge")) {
				WebDriverManager.edgedriver().setup();
				// System.setProperty("webdriver.ei.driver",
				// "C:\\Users\\ercan\\Driver\\Edge\\MicrosoftWebDriver.exe");
				driver = new EdgeDriver();

			}
			if (browser.toString().equalsIgnoreCase("opera")) {
				DesiredCapabilities capabilities = new DesiredCapabilities();
				OperaOptions options = new OperaOptions();
				options.setBinary("C:\\Users\\ercan\\AppData\\Local\\Programs\\Opera\\72.0.3815.18\\Opera.exe");
				capabilities.setCapability(OperaOptions.CAPABILITY, options);
				WebDriverManager.operadriver().setup();
				// System.setProperty("webdriver.opera.driver",
				// "C:\\Users\\ercan\\Driver\\OperaDriver\\operadriver.exe");
				driver = new OperaDriver();

			}
			driver.get(config.getProperty("testsiteurl"));
			log.debug("Test URl is lauching");
			driver.manage().window().maximize();
			log.debug(" windows geting full screan get implemtting implicit wait ");
			driver.manage().timeouts().implicitlyWait(Integer.parseInt(config.getProperty("implicitWait")),
					TimeUnit.SECONDS);
			wait = new WebDriverWait(driver, 15);
			menu = new TopMenu();
		}
	}

	@AfterSuite
	public void tearDown() {
		if (driver != null) {
			driver.close();
			soft.assertAll();
		}
		log.debug(" Test execustion completed");
	}

	public static void type(String location, String value) {
		if (location.endsWith("CSS")) {
			driver.findElement(By.cssSelector(OR.getProperty(location))).sendKeys(value);
		} else if (location.endsWith("XPATH")) {
			driver.findElement(By.xpath(OR.getProperty(location))).sendKeys(value);
		} else if (location.endsWith("ID")) {
			driver.findElement(By.id(OR.getProperty(location))).sendKeys(value);
		}
		test.log(LogStatus.INFO, "typing in this : " + location + " enter value as the " + value);
		Reporter.log("typing in this : " + location + " enter value as the " + value);

	}

	public static void type(By by, String value) {

		driver.findElement(by).sendKeys(value);

		test.log(LogStatus.INFO, "typing in this  : " + by.toString() + "  enter value as the " + value);
		Reporter.log("typing in this : " + by.toString() + " enter value as the " + value);
	}

	public static void type(WebElement element, String value) {

		element.sendKeys(value);

		test.log(LogStatus.INFO, "typing in this  : " + element.toString() + "  enter value as the " + value);
		Reporter.log("typing in this : " + element.toString() + " enter value as the " + value);
	}

	public static void click(By by) {
		driver.findElement(by).click();
		test.log(LogStatus.INFO, "clicking  in this  : " + by.toString() + " bottom");
		Reporter.log("clicking  in this  : " + by.toString() + " bottom");

	}

	public static void click(WebElement element) {
		element.click();
		test.log(LogStatus.INFO, "clicking  in this  : " + element.toString() + " bottom");
		Reporter.log("clicking  in this  : " + element.toString() + " bottom");

	}

	public static void waitVisible(By by) {

		test.log(LogStatus.INFO, "waiting  in this  : " + by.toString() + " web elemet to be vissible ");
		Reporter.log(by.toString() + " web elemet to be vissible");
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(by)));

		test.log(LogStatus.INFO, "waiting for the visibility of   : " + by.toString() + " element ");

	}

	public static void waitVisible(WebElement element) {

		test.log(LogStatus.INFO, "waiting  in this  : " + element.toString() + " web elemet to be vissible ");
		Reporter.log("waiting  in this  : " + element.toString() + " web elemet to be vissible ");

		wait.until(ExpectedConditions.visibilityOf(element));

		test.log(LogStatus.INFO, "waiting for the visibility of   : " + element.toString() + " element ");

	}

	public static void waitClickable(By by) {
		test.log(LogStatus.INFO, "waiting  in this  : " + by.toString() + " web elemet to be clickable  ");
		Reporter.log("waiting  in this  : " + by.toString() + " web elemet to be clickable  ");
		wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(by)));

		test.log(LogStatus.INFO, "waiting  for the clickable  of   : " + by.toString() + " element ");

	}

	public static void waitClickable(WebElement element) {

		wait.until(ExpectedConditions.elementToBeClickable(element));

		test.log(LogStatus.INFO, "waiting  for the clickable  of   : " + element.toString() + " element ");
		Reporter.log("waiting  in this  : " + element.toString() + " web elemet to be clickable  ");
	}

	public static boolean isElementPresent(By by) {

		test.log(LogStatus.INFO, "waiting  in this  : " + by.toString() + " elemet to be peresent   ");
		Reporter.log("waiting  in this  : " + by.toString() + " elemet to be peresent   ");
		try {

			driver.findElement(by);
			return true;

		} catch (NoSuchElementException e) {
			System.out.println(e.getMessage());
			return false;
		}

	}

	public static void select(By by, String text) {
		test.log(LogStatus.INFO, "selecting  in this  : " + by.toString() + "  enter value as the " + text);
		Reporter.log("selecting in this : " + by.toString() + " enter value as the " + text);
		Select select = new Select(driver.findElement(by));
		select.selectByVisibleText(text);

	}

	public static void select(WebElement element, String text) {
		test.log(LogStatus.INFO, "selecting  in this  : " + element.toString() + "  enter value as the " + text);
		Reporter.log("selecting in this : " + element.toString() + " enter value as the " + text);
		Select select = new Select(element);
		select.selectByVisibleText(text);

	}

	public static void selectbyIndex(WebElement element, int text) {
		test.log(LogStatus.INFO,
				"selecting  in this  : " + element.toString() + "  enter index number  as the " + text);
		Reporter.log("selecting in this : " + element.toString() + " enter index number  as the " + text);
		Select select = new Select(element);
		select.selectByIndex(text);

	}

	public static void selectbyValue(WebElement element, String text) {
		test.log(LogStatus.INFO,
				"selecting  in this  : " + element.toString() + "  enter index number  as the " + text);
		Reporter.log("selecting in this : " + element.toString() + " enter index number  as the " + text);
		Select select = new Select(element);
		select.selectByValue(text);

	}

	public static void softAssert(String actual, String expected) {

		test.log(LogStatus.INFO, "typing in this  : " + expected + actual + " soft checking make sure they are same ");
		Reporter.log("typing in this  : " + expected + actual + " soft checking make sure they are same ");

		soft.assertEquals(expected, actual);
		Reporter.log("<br>");
		Reporter.log("Click to see Screenshot");
		Reporter.log("verification of these  " + expected + " and " + actual + "  failed and reason is ");
		Reporter.log("<a target=\"_blank\" href=" + TestUtil.screenshotName + ">Screenshot</a>");

		Reporter.log("<br>");
		Reporter.log("<a target=\"_blank\" href=" + TestUtil.screenshotName + "><img src=" + TestUtil.screenshotName
				+ " height=200 width=200></img></a>");
		// Extent Report
		test.log(LogStatus.SKIP, "verification of these  " + expected + " and " + actual + "  failed"
				+ " and   Failled with this exception:   ");
		test.log(LogStatus.FAIL, test.addScreenCapture(TestUtil.screenshotName));
		log.info(expected + " and " + actual + " is not mattching  ");

	}

	public static void verifyEquals(String actual, String expected) throws IOException {

		test.log(LogStatus.INFO, "typing in this  : " + expected + actual + " soft checking make sure they are same ");
		Reporter.log("typing in this  : " + expected + actual + " soft checking make sure they are same ");
		try {
			Assert.assertEquals(expected, actual);

		} catch (Throwable t) {
			TestUtil.captureScreenshot();
			// reportrNG
			Reporter.log("<br>");
			Reporter.log("Click to see Screenshot");
			Reporter.log("verification of these  " + expected + " and " + actual + "  failed and reason is "
					+ t.getMessage());
			Reporter.log("<a target=\"_blank\" href=" + TestUtil.screenshotName + ">Screenshot</a>");

			Reporter.log("<br>");
			Reporter.log("<a target=\"_blank\" href=" + TestUtil.screenshotName + "><img src=" + TestUtil.screenshotName
					+ " height=200 width=200></img></a>");
			// Extent Report
			test.log(LogStatus.SKIP, "verification of these  " + expected + " and " + actual + "  failed"
					+ " and   Failled with this exception:   " + t.getMessage());
			test.log(LogStatus.FAIL, test.addScreenCapture(TestUtil.screenshotName));
			log.info(expected + " and " + actual + " is not mattching  ");
		}

	}

}