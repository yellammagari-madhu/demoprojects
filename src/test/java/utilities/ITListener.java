package utilities;

import static org.testng.Assert.assertEquals;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ITListener {
	
	
	
	WebDriver driver;
	ExtentSparkReporter htmlReporter;
	ExtentReports extentReports;
	ExtentTest test1,test2;
	
	String siteUrl="https://demowebshop.tricentis.com/";
	
	@BeforeClass
	public void beforeClass() {

		// create the htmlReporter object
		htmlReporter = new ExtentSparkReporter("extentReport.html");

		// create ExtentReport and attach this reports
		extentReports = new ExtentReports();
		extentReports.attachReporter(htmlReporter);
		
		driver=new ChromeDriver();
		
		driver.manage().window().maximize();
		
		driver.get(siteUrl);
	
	}
	
	
	@Test(description = "Search for computers")
	public void testSearch1() throws InterruptedException {
		// create a test and add logs
		test1 = extentReports.createTest("demoworkshop search for computers", "Test fdemoworkshop search  computers");
		test1.log(Status.INFO, "Starting test case");
		
		WebElement searchBox = driver.findElement(By.id("small-searchterms"));
		test1.pass("Find search box for computers");
		
		searchBox.sendKeys("computers");
		test1.pass("Enter input data 'computers' ");
		
		searchBox.submit();
		test1.pass("Click Submit");
		
		// add delay
		Thread.sleep(2000);

		String expectedTitle = "Demo Web Shop. Search";
		String actualTitle = driver.getTitle();

		assertEquals(actualTitle, expectedTitle);
		test1.pass("After search the title are matched");
		
		test1.log(Status.INFO, "End test case");
	}
	
	
	@Test(description = "Search for Electronics")
	public void testSearch2() throws InterruptedException {
		// create a test and add logs
		test2 = extentReports.createTest("demoworkshop Search Electronics", "Test demoworkshop Search Electronics");
		test2.log(Status.INFO, "Starting test case");
		
		WebElement searchBox = driver.findElement(By.id("small-searchterms"));
		test2.pass("Find search box on www.demowebshop.tricentis.com");
		
		searchBox.clear();
		test2.pass("Clear search box for previous searches");
		
		searchBox.sendKeys("Electronics");
		test2.pass("Enter input data 'Electronics camera photos' ");
		
		searchBox.submit();
		test2.pass("Click Submit");
		
		// add delay
		Thread.sleep(2000);

		String expectedTitle = "Demo Web Shop. Search";
		String actualTitle = driver.getTitle();

		assertEquals(actualTitle, expectedTitle);
		test2.pass("After search the title are matched");
		
		test2.log(Status.INFO, "End test case");
	}
	
	@AfterClass
	public void tearDown() {
		driver.quit();
		extentReports.flush();
	}

}