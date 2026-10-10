package ecom.SwagLabs_Login;

import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

import GenericUtitlity.PropertiesUtitlity;
import GenericUtitlity.ScreenshotUtility;
import ObjRepo.LoginPage;
import base.BaseClassForLogin;

public class TC_Login_Negative extends BaseClassForLogin {
	@Test(priority = 1, groups = {"integration","negative"})
	   public void tc_004() throws Exception
	   {
		   LoginPage l=new LoginPage(driver);
		   Reporter.log("Entering a valid username and invalid password.", true);
		   PropertiesUtitlity p=new PropertiesUtitlity();
		   l.loginDetails(p.readDatafromPropertyFileForLogin("un1"), p.readDatafromPropertyFileForLogin("invalidPass"));
		   Thread.sleep(2000);
		   Assert.assertFalse(driver.getCurrentUrl().contains("/inventory.html"),"Invalid Login should not naviagte to product page");
		   ScreenshotUtility.takeScreenshot(driver, "TC_InValidLogin_NotTransferred");
		   
	   }
	
	@Test(priority = 2, groups = {"functional","Negative"})
	   public void tc_002() throws Exception
	   {
		   LoginPage l=new LoginPage(driver);
		   PropertiesUtitlity p=new PropertiesUtitlity();
		   Thread.sleep(2000);
		   l.loginDetails(p.readDatafromPropertyFileForLogin("un1"), p.readDatafromPropertyFileForLogin("invalidPass"));
		   Thread.sleep(2000);
		   Assert.assertTrue(l.isErrorDispalyed(), "Login error message is not displayed");
		   ScreenshotUtility.takeScreenshot(driver, "TC_LoginError");
	   }
	   
}
