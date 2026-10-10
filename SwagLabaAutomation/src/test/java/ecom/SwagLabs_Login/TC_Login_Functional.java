package ecom.SwagLabs_Login;



import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

import GenericUtitlity.PropertiesUtitlity;

import ObjRepo.HomePage;
import ObjRepo.LoginPage;
import base.BaseClassForLogin;

public class TC_Login_Functional extends BaseClassForLogin {
   @Test(priority = 1, groups = "functional")
   public void tc_001() throws Exception
   {
	   LoginPage l=new LoginPage(driver);
	   Reporter.log("Step 1: Read the username and password from the property file.", true);
	   PropertiesUtitlity p=new PropertiesUtitlity();
	   Reporter.log("Step 2: Enter the username and password and log in to the application.", true);
	   l.loginDetails(p.readDatafromPropertyFileForLogin("un1"), p.readDatafromPropertyFileForLogin("password"));
	   Reporter.log("Step 3: Verify that the user has logged in successfully.", true);
	   Assert.assertTrue(driver.getCurrentUrl().contains("inventory.html"), "Unsuccessful login");
	   Thread.sleep(2000);
	   Reporter.log("Step 4: Log out of the application.", true);
	   HomePage h=new HomePage(driver);
		h.getHamburger();
		Thread.sleep(2000);
		h.getLogout();
		Assert.assertTrue(driver.getCurrentUrl().contains("https://www.saucedemo.com/"), null);
		Reporter.log("Test Case Passed: Login using credentials from the property file and logout completed successfully.", true);
   }
}
