package driver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.safari.SafariDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class DriverFactory {

	

public WebDriver CreateWebDriver(String browser) {

   WebDriver driver = null;
	
	switch (browser) {
	
	
	case "chrome":
	default:
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		break;
		
    case "firefox":
    	WebDriverManager.firefoxdriver().setup();
		driver = new FirefoxDriver();
		break;
    
    case "ie":
    	WebDriverManager.iedriver().setup();
		driver = new InternetExplorerDriver();
		break;
		
    case "Safari":
    	driver = new SafariDriver();
    	break;
	
	}
	
	driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	driver.manage().window().maximize();
	return driver;
	
	
	}
}
 