package base;

import java.io.FileInputStream;

import java.time.Duration;
import java.util.HashMap;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import GenericUtitlity.PropertiesUtitlity;
import ObjRepo.HomePage;
import ObjRepo.LoginPage;

public class BaseClass {

   public WebDriver driver = null;
   PropertiesUtitlity p=new PropertiesUtitlity();
	@Parameters("browser")
	@BeforeMethod(alwaysRun = true)
	public void bc(@Optional("chrome") String browser) throws Exception
	{
		ChromeOptions options = new ChromeOptions();

		options.addArguments("--disable-notifications");

		options.setExperimentalOption("prefs", new HashMap<String, Object>() {{
		    put("credentials_enable_service", false);
		    put("profile.password_manager_enabled", false);
		    put("profile.password_manager_leak_detection", false);
		}});

		if(browser.equalsIgnoreCase("chrome"))
			driver = new ChromeDriver(options);
		else if(browser.equalsIgnoreCase("firefox"))
			driver = new FirefoxDriver();
		else if(browser.equalsIgnoreCase("edge"))
			driver = new EdgeDriver();
		
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
		System.out.println("launch the browser");
		driver.get(p.readDatafromPropertyFile("url"));
		LoginPage l=new LoginPage(driver);
		l.loginDetails(p.readDatafromPropertyFileForLogin("un1"), p.readDatafromPropertyFileForLogin("password"));
		Reporter.log("Login successfull",true);
	}
	
	
	
	
	@AfterMethod(alwaysRun = true)
	public void ac() throws InterruptedException
	{
		HomePage h=new HomePage(driver);
		h.getHamburger();
		Thread.sleep(2000);
		h.getLogout();
		System.out.println("logout executed");
		driver.quit();
		System.out.println("close the browser");
	}

}
