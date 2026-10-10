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
import ObjRepo.HomePage;
import ObjRepo.ProductPage;
import base.BaseClass;

public class TC_Checkout_Smoke extends BaseClass {
	
	
	@DataProvider(name = "checkoutData")
	public Object[][] checkoutData() throws IOException {
		ExcelUtility e=new ExcelUtility();
		return e.getMultipleData();
	}
@Test(priority = 1, groups = "smoke")
public void verifyCheckoutInformationPageElements() throws InterruptedException, IOException {
	Reporter.log("Starting smoke test: Verify Checkout Information page and mandatory fields", true);
	ProductPage p=new ProductPage(driver);
	Reporter.log("Sorting products by Price (low to high)", true);
	p.selectingSorting("Price (low to high)");
	Reporter.log("Adding the third product to the cart", true);
	p.getAddToCart(2);
	HomePage h=new HomePage(driver);
	Reporter.log("Navigating to the Cart page", true);
	h.getCart();
	CartPage c=new CartPage(driver);
	Reporter.log("Clicking the Checkout button", true);
	c.getCheckoutbt();
	Thread.sleep(2000);
	CheckoutPage1 c1= new CheckoutPage1(driver);
	Reporter.log("Verifying Checkout Information page URL", true);
	Assert.assertTrue(driver.getCurrentUrl().contains("checkout-step-one.html"), "Checkout Information page is not displayed");
	Reporter.log("PASS: Checkout Information page is displayed", true);
	
	//verify mandatory fields
	 Reporter.log("Verifying First Name field,Last name field, Postal code field, Continue button, Cancel button is displayed", true);
	Assert.assertTrue(c1.isFirstNameDisplayed().isDisplayed(), "First Name field is not dispalyed");
	Assert.assertTrue(c1.isLastNameNameDisplayed().isDisplayed(), "Last Name field is not dispalyed");
	Assert.assertTrue(c1.isPostalCodeDisplayed().isDisplayed(), "Postal Code field is not dispalyed");
	Assert.assertTrue(c1.isContinueDisplayed().isDisplayed(), "Continue button is not dispalyed");
	Assert.assertTrue(c1.isCancelDisplayed().isDisplayed(), "Cancel button field is not dispalyed");
	Reporter.log("PASS: Cancel button is displayed", true);
    Reporter.log("Smoke test completed successfully", true);
	
}

@Test(dataProvider = "checkoutData",  priority = 2, groups = "smoke")
public void verifyCheckoutOverviewAndCompleteOrder(String fn,String ln,String pc) throws InterruptedException, IOException {
	Reporter.log("Starting smoke test: Verify Checkout Overview and complete the order", true);
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
	Thread.sleep(2000);
	CheckoutPage1 c1= new CheckoutPage1(driver);
	Reporter.log("Entering checkout details", true);
	c1.CheckoutDetails(fn, ln, pc);
	CheckoutPage2 c2=new CheckoutPage2(driver);
	Reporter.log("Verifying Checkout Overview page is displayed", true);
	Assert.assertTrue(c2.isCheckoutOverviewDisplayed());
	Reporter.log("PASS: Checkout Overview page is displayed", true);
    Reporter.log("Verifying Quantity,ProductPrice,PaymentInformation,ShippingInformation,ItemTotal,Tax,Total price,Finsih button,Cancel button is displayed", true);
	Assert.assertTrue(c2.isQuantityDisplayed());
	Assert.assertTrue(c2.isProductPriceDisplayed());
	Assert.assertTrue(c2.isPaymentInformationDisplayed());
	Assert.assertTrue(c2.isShippingInformationDisplayed());
	Assert.assertTrue(c2.isItemTotalDisplayed());
	Assert.assertTrue(c2.isTaxDisplayed());
	Assert.assertTrue(c2.isTotalDisplayed());
	Assert.assertTrue(c2.isFinishDisplayed());
	Assert.assertTrue(c2.isCancelDisplayed());
	Reporter.log("Clicking Finish to complete the order", true);
	c2.getFinishBt();
	Reporter.log("Verifying order completion URL", true);
	Assert.assertTrue(driver.getCurrentUrl().contains("checkout-complete.html"),"Order was not completed successfully");
	Reporter.log("PASS: Order completed successfully", true);
    Reporter.log("Smoke test completed successfully", true);
}
}
