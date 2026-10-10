package base;

import java.time.Duration;
import java.util.HashMap;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import GenericUtitlity.PropertiesUtitlity;
import ObjRepo.HomePage;

public class BaseClassForLogin {
		public WebDriver driver;
		PropertiesUtitlity p=new PropertiesUtitlity();
		@Parameters("browser")
		@BeforeClass(alwaysRun = true)
		public void launchBrowser(@Optional("chrome") String browser) throws Exception {
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
			
		}
		
		@AfterClass(alwaysRun = true)
		public void closeBrowser() {
			driver.quit();
		}
}
