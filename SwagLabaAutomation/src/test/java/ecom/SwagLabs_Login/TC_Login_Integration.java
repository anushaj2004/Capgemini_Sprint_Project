package ecom.SwagLabs_Login;

import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

import GenericUtitlity.PropertiesUtitlity;
import GenericUtitlity.ScreenshotUtility;
import ObjRepo.HomePage;
import ObjRepo.LoginPage;
import base.BaseClassForLogin;

public class TC_Login_Integration extends BaseClassForLogin {
	@Test(priority = 1, groups = {"integration","positive"})
	   public void tc_003() throws Exception
	   {
		   LoginPage l=new LoginPage(driver);
		   Reporter.log("Step 1: Read the username and password from the property file.", true);
		   PropertiesUtitlity p=new PropertiesUtitlity();
		   Reporter.log("Step 2: Enter the credentials and log in to the application.", true);
		   l.loginDetails(p.readDatafromPropertyFileForLogin("un1"), p.readDatafromPropertyFileForLogin("password"));
		   Thread.sleep(2000);
		   Reporter.log("Step 3: Verify that the user is logged in successfully and the product page is displayed.", true);
		   Assert.assertTrue(driver.getCurrentUrl().contains("/inventory.html"),"Product page is not displayed after sucessfull login");
		   Reporter.log("Test Case Passed: User logged in successfully and the product page is displayed.", true);
	   }
	
	
}
