package utilities;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners(utilities. MyListener.class)
public class TestNGListeners<TakeScreenshot> {
	static WebDriver driver;
	String siteUrl="https://demowebshop.tricentis.com/login";
	@BeforeMethod
	public void setup() {
		driver=new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.get(siteUrl);
	}
	
	@Test
	public void LoginFailTest() {
		driver.findElement(By.xpath("//*[@id=\"Email\"]")).sendKeys("Sample@gmail.com");
		driver.findElement(By.xpath("//*[@id=\"password\"]")).sendKeys("sample@12345");
		driver.findElement(By.xpath("//*[@id=\"login-form\"]/div[5]/input")).click();
		
		String expectedUrl = "Login | demowork shop";
		String actualUrl =  driver.getTitle();
		System.out.println(actualUrl);
		Assert.assertNotEquals(actualUrl, expectedUrl);
		
		
	}
	
	public  void captureScreenshot(String testName) {
		
		TakesScreenshot takesscreenshot = (TakesScreenshot) driver;
		File file = takesscreenshot.getScreenshotAs(OutputType.FILE);
		File destfile = new File("./Screenshots/"+testName+".png");
		
		try {
			FileUtils.copyFile(file, destfile);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Screenshot saved successfully");
	}
	@AfterMethod
	public void tearDown() {
		driver.quit();
	}
	
}