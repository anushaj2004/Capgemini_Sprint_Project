package ecom.SwagLabs_Checkout;

import java.io.IOException;

import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import GenericUtitlity.ExcelUtility;
import ObjRepo.CartPage;
import ObjRepo.CheckoutPage1;
import ObjRepo.CheckoutPage2;
import ObjRepo.CheckoutPage3;
import ObjRepo.HomePage;
import ObjRepo.ProductPage;
import base.BaseClass;

public class TC_Checkout_System extends BaseClass{
	
	@DataProvider(name = "checkoutData")
	public Object[][] checkoutData() throws IOException {
		ExcelUtility e=new ExcelUtility();
		return e.getMultipleData();
	}
	
@Test(dataProvider = "checkoutData", priority = 1, groups = "system")
public void verifySuccessfulOrderPlacement(String fn,String ln,String pc) throws InterruptedException, IOException {
	Reporter.log("Starting test: Verify successful order placement", true);
	ProductPage p=new ProductPage(driver);
	Reporter.log("Sorting products by Price (low to high)", true);
	p.selectingSorting("Price (low to high)");
	 Reporter.log("Adding the first product to the cart", true);
	p.getAddToCart(0);
	HomePage h=new HomePage(driver);
	Reporter.log("Navigating to the Cart page", true);
	h.getCart();
	CartPage c=new CartPage(driver);
	Reporter.log("Clicking the Checkout button", true);
	c.getCheckoutbt();
	CheckoutPage1 c1=new CheckoutPage1(driver);
	Reporter.log("Entering Checkout details", true);
	c1.CheckoutDetails(fn, ln, pc);
	CheckoutPage2 c2=new CheckoutPage2(driver);
	Reporter.log("Clicking Finish to place the order", true);
	c2.getFinishBt();
	CheckoutPage3 c3=new CheckoutPage3(driver);
	Reporter.log("Retrieving order confirmation message", true);
	String actualMessage=c3.getConfirmationMessage();
	Reporter.log(actualMessage,true);
	Reporter.log("Verifying the order confirmation message", true);
	Assert.assertEquals(actualMessage, "Thank you for your order!");
	Reporter.log("PASS: Order placed successfully and confirmation message verified", true);
}

   @Test(dataProvider = "checkoutData" ,priority = 2, groups = "system")
   public void verifyCancelButtonOnCheckoutOverview(String fn,String ln,String pc) throws InterruptedException, IOException {
	   Reporter.log("Starting test: Verify Cancel button on Checkout Overview", true);
	   ProductPage p=new ProductPage(driver);
	   Reporter.log("Sorting products by Price (low to high)", true);
		p.selectingSorting("Price (low to high)");
		Reporter.log("Adding the second product to the cart", true);
		p.getAddToCart(1);
		Thread.sleep(2000);
		HomePage h=new HomePage(driver);
		Reporter.log("Navigating to the Cart page", true);
		h.getCart();
		Thread.sleep(2000);
		CartPage c=new CartPage(driver);
		Reporter.log("Clicking the Checkout button", true);
		c.getCheckoutbt();
		CheckoutPage1 c1=new CheckoutPage1(driver);
		Reporter.log("Entering Checkout details", true);
		c1.CheckoutDetails(fn, ln, pc);
		Reporter.log("Verifying Checkout Overview page URL", true);
		Assert.assertTrue(driver.getCurrentUrl().contains("checkout-step-two.html"),"Checkout Overview page was not displayed");
		Reporter.log("PASS: Checkout Overview page is displayed", true);
		CheckoutPage2 c2=new CheckoutPage2(driver);
		Reporter.log("Clicking Cancel on Checkout Overview", true);
		c2.getCancel();
		Thread.sleep(2000);
		Reporter.log("Verifying navigation back to the Products page", true);
		Assert.assertTrue(driver.getCurrentUrl().contains("inventory.html"), "User was not returned to the Products page after cancel");
		Reporter.log("PASS: User returned to the Products page after clicking Cancel", true);
	    Reporter.log("Test completed successfully", true);
   }

}
