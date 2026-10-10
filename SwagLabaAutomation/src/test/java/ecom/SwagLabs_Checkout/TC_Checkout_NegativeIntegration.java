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

public class TC_Checkout_NegativeIntegration extends BaseClass {
	@DataProvider(name = "checkoutData")
	public Object[][] checkoutData() throws IOException {
		ExcelUtility e=new ExcelUtility();
		return e.getMultipleData();
	}
	@Test(dataProvider = "checkoutData",priority=1 , groups ="{integration,negative}")
	public void verifyRemovedProductIsNotTransferredToCheckout(String fn,String ln,String pc) throws InterruptedException, IOException
	{
		Reporter.log("Starting test: Verify removed product is not transferred to Checkout", true);
		ProductPage p=new ProductPage(driver);
		Reporter.log("Adding the first product to the cart", true);
		p.getAddToCart(0);
		Reporter.log("Adding the second product to the cart", true);
		p.getAddToCart(1);
		HomePage h=new HomePage(driver);
		 Reporter.log("Navigating to the Cart page", true);
		h.getCart();
		Thread.sleep(2000);
		CartPage c=new CartPage(driver);
		Reporter.log("Verifying that two products are present in the Cart", true);
		Assert.assertEquals(c.getProductCount(), 2,"Two products are not present in Cart");
		Reporter.log("PASS: Two products are present in the Cart", true);
		Reporter.log("Removing the first product from the Cart", true);
		Reporter.log("Clicking the Checkout button", true);
		c.getRemoveBt(0);
		c.getCheckoutbt();
		Thread.sleep(2000);
		CheckoutPage1 c1=new CheckoutPage1(driver);
		Reporter.log("Entering checkout details", true);
		c1.CheckoutDetails(fn, ln, pc);
		CheckoutPage2 c2=new CheckoutPage2(driver);
		Reporter.log("Taking screenshot of Checkout Overview", true);
		ScreenshotUtility.takeScreenshot(driver, "TC_ProductRemoved_FromCart");
		Reporter.log("Verifying that only one product is present in Checkout Overview", true);
		Assert.assertEquals(c2.getProductCount(),1, "Removed product was incorrectly transferred to Checkout");
		Reporter.log("PASS: Removed product was not transferred to Checkout", true);
	    Reporter.log("Test completed successfully", true);
	}
}
