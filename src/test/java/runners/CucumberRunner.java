package runners;

import helper.DataProviderFactory;
import helper.SeleniumHelper;
import com.cucumber.listener.ExtentCucumberFormatter;
import com.google.common.io.Files;
import cucumber.api.CucumberOptions;
import cucumber.api.testng.AbstractTestNGCucumberTests;
import dataProvider.ConfigDataProvider;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.*;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.*;
import org.testng.ITestResult;
import org.testng.annotations.*;
import org.yaml.snakeyaml.Yaml;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.TimeUnit;

@CucumberOptions
		(
				plugin = {"json:target/positive/cucumber.json", "pretty", "html:target/positive/cucumber.html","com.cucumber.listener.ExtentCucumberFormatter"},
				features = "src/test/",
				glue = {"workbench/steps"},
				tags = { "@blankImage"}
		)

public class CucumberRunner extends AbstractTestNGCucumberTests {


	public static ConfigDataProvider config = null;
	public static WebDriver driver;
	public static Logger logger;

	@BeforeSuite
	public void generateHTMLReports() {

		//static report name
		String fileName = System.getProperty("user.dir")+"\\ExtentReport\\extentreports.html";
		File newFile = new File(fileName);
		ExtentCucumberFormatter.initiateExtentCucumberFormatter(newFile,true);
		ExtentCucumberFormatter.loadConfig(new File("src/test/resources/extent-config.xml"));

		ExtentCucumberFormatter.addSystemInfo("Browser Name", "Chrome ");
		ExtentCucumberFormatter.addSystemInfo("Browser version", "89.0.45");
		ExtentCucumberFormatter.addSystemInfo("Selenium version", "v2.53.0");

	}

	@AfterClass
	public void quit() throws IOException, InterruptedException {
		driver.quit();
	}

	@BeforeClass
	public void setUp() throws Exception {
		openBrowser();
		maximizeWindow();
		implicitWait(15);
	}
	@AfterMethod
	public void tearDown(ITestResult result) throws IOException {
		if (result.getStatus() == ITestResult.FAILURE) {
			File imageFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
			String failureImageFileName = result.getMethod().getMethodName()
					+ new SimpleDateFormat("MM-dd-yyyy_HH-ss").format(new GregorianCalendar().getTime()) + ".png";
			File failureImageFile = new File(System.getProperty("user.dir") + "//screenshots//" + failureImageFileName);
			failureImageFile.getParentFile().mkdir();
			failureImageFile.createNewFile();
			Files.copy(imageFile, failureImageFile);
		}
	}

	public WebDriver openBrowser() throws Exception {
		logger = Logger.getLogger("Automation Test"); //Added logger
		PropertyConfigurator.configure("Log4j.properties");//Added logger

		config= new ConfigDataProvider();
		SeleniumHelper.configureDriverPath();
		driver= SeleniumHelper.startApplication(driver, config.getBrowser());
		return driver;
	}

	public void maximizeWindow() {
		driver.manage().window().maximize();
	}

	public void implicitWait(int time) {
		driver.manage().timeouts().implicitlyWait(time, TimeUnit.SECONDS);
	}

	public void explicitWait(WebElement element) {
		try{
			WebDriverWait wait = new WebDriverWait(driver, 60);
			wait.until(ExpectedConditions.visibilityOf(element));
		}
		catch (Exception e){
			System.out.println("exception catch");
			e.printStackTrace();
		}
	}

	public void setEnv(String url) throws Exception {
		String baseUrl = DataProviderFactory.getConfig().getApplicationUrl(url);
		driver.get(baseUrl);
		logger.info("******** Launching Browser*********");
	}

	public static WebDriver getDriver() {
		return driver;
	}

	//todo Methods reusable
	public void jsClick(WebElement element) throws InterruptedException {
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		Thread.sleep(3000);
		jse.executeScript("arguments[0].click();", element);
	}
	public void selectDropDownValueByValue(WebElement element,String Value ) throws Exception {
		implicitWait(120);
		Select dropdown = new Select((element));
		dropdown.selectByVisibleText(Value);
		logger.info("******** DropDown Value Selected *********");

	}
	public void closeDriver() {
		driver.close();
	}

	public void tabSwitching(int index){
		ArrayList<String> tabs2 = new ArrayList<String> (driver.getWindowHandles());
		driver.switchTo().window(tabs2.get(index));
	}

}




	
	
	






