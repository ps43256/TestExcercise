package testcases;


import org.testng.annotations.Test;



import org.testng.AssertJUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;

import data.DataProviders;
import driver.DriverFactory;


public class testcase {
	
	private WebDriver driver;
	private DriverFactory driverFactory;
	private WebDriverWait wait;
	
	
	
	@Parameters({"browser"})
	@BeforeMethod
	public void LaunchBrowser(String browser)
	{
		driverFactory = new DriverFactory();
		driver = driverFactory.CreateWebDriver(browser);
		driver.manage().window().maximize();
		driver.get("http://demoaut.katalon.com");
		
		wait  = new WebDriverWait(driver, 30);
	}
	
	@AfterMethod
	public void CloseBrowser()
	{
		driver.quit();
	}
	
	@Test(dataProviderClass = DataProviders.class, dataProvider="dp")
	public void test(String facility, String healthCareProgram, String Date, String comments )
	{
		Login();
		
		BookAppointment(facility,healthCareProgram,Date,comments);
		
		Verification(facility,healthCareProgram,Date,comments);
		
		Logout();
	}
	
	public void Login()
	{
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@id=\"menu-toggle\"]//i"))).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[text()=\"Login\"]"))).click();
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("txt-username"))).sendKeys("John Doe");
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("txt-password"))).sendKeys("ThisIsNotAPassword");
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("btn-login"))).click();
		
	}
	
	
	public void BookAppointment(String facility, String program, String date, String Comments )
	{
		Select facilityDropdown = new Select(wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("combo_facility"))));
		facilityDropdown.selectByValue(facility);
		String locator = "//input[@type= \"radio\"and @value=\""+ program +"\"]"; 
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(locator))).click();
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("txt_visit_date"))).sendKeys(date);
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("txt_comment"))).sendKeys(Comments);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("btn-book-appointment"))).click();
		
	}
	
	public void Verification(String facility, String program,String date, String Comments)
	{
		String Confirmationtext = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h2[text()=\"Appointment Confirmation\"]"))).getText();
		AssertJUnit.assertEquals(Confirmationtext, "Appointment Confirmation");
		String selectedFacility = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("facility"))).getText();
		AssertJUnit.assertEquals(selectedFacility, facility);
		
		String selectedDate = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("visit_date"))).getText();
		AssertJUnit.assertEquals(selectedDate, date);
		
		String enteredProgram = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("program"))).getText();
		AssertJUnit.assertEquals(enteredProgram, program);
		
		String enteredComments = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("comment"))).getText();
		AssertJUnit.assertEquals(enteredComments, Comments);
	}
	
	public void Logout()
	{
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("menu-toggle"))).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[text()=\"Logout\"]"))).click();
		
	}


}
