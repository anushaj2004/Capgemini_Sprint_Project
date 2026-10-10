package ecom.SwagLabs_Checkout;

import java.io.IOException;

import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import GenericUtitlity.ExcelUtility;
import GenericUtitlity.ScreenshotUtility;
import ObjRepo.CartPage;
import ObjRepo.CheckoutPage1;
import ObjRepo.CheckoutPage2;
import ObjRepo.HomePage;
import ObjRepo.ProductPage;
import base.BaseClass;

public class TC_Checkout_Negative extends BaseClass {
	@DataProvider(name = "checkoutData")
	  public Object[][] checkoutData() throws IOException {
	      ExcelUtility e=new ExcelUtility();
	      return e.getMultipleData();
	  }
	@Test(priority=1 , groups ="functional")
	  public void tc_NegverifyCheckoutErrorWhenFirstNameIsBlankative1() throws InterruptedException, IOException {
		 Reporter.log("Starting negative test: First Name field left blank", true);
		  ProductPage p=new ProductPage(driver);
		  Reporter.log("Sorting products by Price (low to high)", true);
			p.selectingSorting("Price (low to high)");
			Reporter.log("Adding a product to the cart", true);
			p.getAddToCart(2);
			HomePage h=new HomePage(driver);
			Reporter.log("Navigating to the Cart page", true);
			h.getCart();
			CartPage c=new CartPage(driver);
			Reporter.log("Clicking the Checkout button", true);
			c.getCheckoutbt();
			CheckoutPage1 c1=new CheckoutPage1(driver);
			Reporter.log("Opening Excel file to retrieve test data", true);
			ExcelUtility e=new ExcelUtility();
			e.openExcel();
			String fn=e.getData("Sheet1", 2, 0);
			String ln=e.getData("Sheet1", 2, 1);
			String postalcode=e.getData("Sheet1", 2, 2).toString();
			Reporter.log("Leaving First Name field blank", true);
//			c1.getFirstName(fn);
			Reporter.log("Entering Last Name", true);
			c1.getLastName(ln);
			Reporter.log("Entering Postal Code", true);
			c1.getPostalCode(postalcode);
			 Reporter.log("Clicking Continue without entering First Name", true);
			c1.getContinuebt();
			Reporter.log("Taking screenshot of the First Name validation error", true);
			ScreenshotUtility.takeScreenshot(driver, "TC_FirstNameBlank_Error");
			String error=c1.getErrorMessage();
			Reporter.log("Actual error message: " + error, true);
			Reporter.log("Verifying the expected First Name validation message", true); 
			Assert.assertEquals(error, "Error: First Name is required");
			Reporter.log("PASS: First Name required validation message displayed correctly", true);
	  }
	
	
	
	@Test(dataProvider = "checkoutData",priority=2 , groups ="system")
	public void verifyBrowserBackFromCheckoutOverview(String fn,String ln,String pc) throws InterruptedException, IOException {
		Reporter.log("Starting test: Verify Browser Back navigation from Checkout Overview", true);
		ProductPage p=new ProductPage(driver);
		Reporter.log("Adding first product to the cart", true);
		p.getAddToCart(0);
		Reporter.log("Adding second product to the cart", true);
		p.getAddToCart(1);
		HomePage h=new HomePage(driver);
		Reporter.log("Navigating to the Cart page", true);
		h.getCart();
		CartPage c=new CartPage(driver);
		Reporter.log("Clicking the Checkout button", true);
		c.getCheckoutbt();
		CheckoutPage1 c1=new CheckoutPage1(driver);
		Reporter.log("Entering Checkout Details", true);
		c1.CheckoutDetails(fn, ln, pc);
		Reporter.log("Clicking Continue to proceed to Checkout Overview", true);
		CheckoutPage2 c2=new CheckoutPage2(driver);
		Reporter.log("Verifying Checkout Overview page is displayed", true);
		Assert.assertTrue(c2.isCheckoutOverviewDisplayed(),"Checkout overview is not displayed");
		Reporter.log("PASS: Checkout Overview page is displayed", true);
		Reporter.log("Clicking browser Back button", true);
		driver.navigate().back();
		Reporter.log("Verifying navigation back to Checkout Information page", true);
		Assert.assertTrue(c1.isCheckoutInformationDisplayed().isDisplayed(),"Checkout Information page is not displayed after Browser Back");
		ScreenshotUtility.takeScreenshot(driver, "TC_BrowserBack_CheckoutInformation");
		Reporter.log("PASS: Checkout Information page is displayed after Browser Back", true);
	    Reporter.log("Taking screenshot after browser Back navigation", true);
	    ScreenshotUtility.takeScreenshot(driver,"TC_BrowserBack_CheckoutInformation");
	    Reporter.log("Test completed: Browser Back navigation verified successfully", true);
		
	}
	
	@Test(priority = 3,groups = "smoke")
	public void verifyCheckoutErrorWhenAllFieldsAreBlank() throws InterruptedException, IOException {
		 Reporter.log("Starting negative test: All checkout fields left blank", true);
		ProductPage p=new ProductPage(driver);
		Reporter.log("Adding a product to the cart", true);
		p.getAddToCart(0);
		HomePage h=new HomePage(driver);
		Reporter.log("Navigating to the Cart page", true);
		h.getCart();
		CartPage c=new CartPage(driver);
		Reporter.log("Clicking the Checkout button", true);
		c.getCheckoutbt();
		CheckoutPage1 c1=new CheckoutPage1(driver);
		Reporter.log("Leaving all checkout fields blank", true);
	    Reporter.log("Clicking Continue without entering any details", true);
		c1.getContinuebt();
		 Reporter.log("Taking screenshot of the validation error", true);
		ScreenshotUtility.takeScreenshot(driver, "TC_AllFieldsBlank_Error");
		String actualError=c1.getErrorMessage();
		Reporter.log("Actual validation error: " + actualError, true);

		Reporter.log("Verifying that the validation error is displayed", true);
		Assert.assertTrue(c1.isErrorDisplayed().isDisplayed(),"Validation error is not displayed");
		//verify user remains on checkout information page
		Assert.assertTrue(c1.isCheckoutInformationDisplayed().isDisplayed(),"Checkout Information page is not displayed");
		Reporter.log("PASS: Validation error is displayed", true);

	    Reporter.log("Verifying the user remains on Checkout Information page", true);
	    Assert.assertTrue(c1.isCheckoutInformationDisplayed().isDisplayed(),"Checkout Information page is not displayed");
	}
	
}
