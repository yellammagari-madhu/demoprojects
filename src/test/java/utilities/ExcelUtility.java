package utilities;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import pages.LoginPage;
import pages.LoginTest;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ExcelUtility {
	private WebDriver driver;
    private static final Logger LOGGER = Logger.getLogger(LoginTest.class.getName());

    @BeforeClass
    public void setUp() {
        // Set up WebDriver
        System.setProperty("webdriver.chrome.driver", "drivers/windows/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }
    @DataProvider(name = "loginData")
    public Object[][] getLoginData() throws IOException {
        // Read data from Excel file
        FileInputStream fis = new FileInputStream("C:/Users/Admin/eclipse-workspace/com.myframework.seliniumtest/src/test/resources/testdata.xlsx");
        Workbook workbook = new XSSFWorkbook(fis);
        Sheet sheet = workbook.getSheetAt(0);

        int rowCount = sheet.getPhysicalNumberOfRows();
        Object[][] data = new Object[rowCount - 1][2];

        for (int i = 1; i < rowCount; i++) {
            Row row = sheet.getRow(i);
            data[i - 1][0] = row.getCell(0).getStringCellValue(); // Username
            data[i - 1][1] = row.getCell(1).getStringCellValue(); // Password
        }
        workbook.close();
        fis.close();
        return data;
    }

    @Test(dataProvider = "loginData")
    public void testLogin(String username, String password) {
        // Test login functionality with provided credentials
        LOGGER.log(Level.INFO, "Navigating to login page...");
        driver.get("https://demowebshop.tricentis.com/login");
        LOGGER.log(Level.INFO, "Entering username: " + username);
        driver.findElement(LoginPage.usernameField).sendKeys(username);
        LOGGER.log(Level.INFO, "Entering password: " + password);
        driver.findElement(LoginPage.passwordField).sendKeys(password);
        LOGGER.log(Level.INFO, "Clicking login button...");
        driver.findElement(LoginPage.loginButton).click();

        // Add assertions for successful login
        // For demonstration purposes, let's assume successful login is verified here
        LOGGER.log(Level.INFO, "Logged in successfully with username: " + username);
    }

    @AfterClass
    public void tearDown() {
        // Quit WebDriver
        LOGGER.log(Level.INFO, "Quitting WebDriver...");
        driver.close();;
    }

}