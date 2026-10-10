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

public class TC_Checkout_Integration extends BaseClass { 
	
	@DataProvider(name = "checkoutData")
	  public Object[][] checkoutData() throws IOException {
	      ExcelUtility e=new ExcelUtility();
	      return e.getMultipleData();
	  }
	
	
	@Test(dataProvider = "checkoutData", priority = 1, groups = "integration")
	public void verifyCartProductMatchesCheckoutOverview(String fn,String ln,String pc) throws IOException, InterruptedException
	{
		ProductPage p=new ProductPage(driver);
		Reporter.log("Selecting sorting option: Price (low to high)", true);
		p.selectingSorting("Price (low to high)");
		Reporter.log("Adding the first product to the cart", true);
		p.getAddToCart(0);
		Reporter.log("Navigating to the Cart page", true);
		HomePage h=new HomePage(driver);
		h.getCart();
		CartPage c=new CartPage(driver);
		String cartProduct=c.getProductItem(0);
		Reporter.log("Product added to Cart: " + cartProduct, true);
		Reporter.log("Clicking on the Checkout button", true);
		c.getCheckoutbt();
		CheckoutPage1 c1=new CheckoutPage1(driver);
		Reporter.log("Entering checkout details", true);
		c1.CheckoutDetails(fn, ln, pc);
		CheckoutPage2 c2=new CheckoutPage2(driver);
		String overviewProduct=c2.getProductItem(0);
		Reporter.log("Product in Checkout Overview: " + overviewProduct, true);
		Reporter.log("Verifying that the Cart product matches the Checkout Overview product", true);
		Assert.assertEquals(overviewProduct,cartProduct,"Product in checkout overview doesnot match the product in Cart" );
		Reporter.log("PASS: Cart product matches Checkout Overview product: " + cartProduct, true);
	}
	
	@Test(dataProvider = "checkoutData", priority = 2, groups = "integration")
	public void tverifyMultipleProductsTransferredToCheckoutOverview(String fn,String ln,String pc) throws InterruptedException, IOException {
		ProductPage p=new ProductPage(driver);
		Reporter.log("Sorting products by Price (low to high)", true);
		p.selectingSorting("Price (low to high)");
		Reporter.log("Adding the first product to the cart", true);
		p.getAddToCart(0);
		Reporter.log("Adding the second product to the cart", true);
		p.getAddToCart(1);
		Reporter.log("Adding the third product to the cart", true);
		p.getAddToCart(2);
		HomePage h=new HomePage(driver);
		Reporter.log("Navigating to the Cart page", true);
		h.getCart();
		CartPage c=new CartPage(driver);
		String product2=c.getProductItem(1);
		Reporter.log("Second product in Cart: " + product2, true);
		Reporter.log("Clicking the Checkout button", true);
		c.getCheckoutbt();
		CheckoutPage1 c1=new CheckoutPage1(driver);
		Reporter.log("Entering checkout details", true);
		Thread.sleep(2000);
		c1.CheckoutDetails(fn, ln, pc);
		CheckoutPage2 c2=new CheckoutPage2(driver);
		String overviewProduct = c2.getProductItem(1);
		Reporter.log("Second product in Checkout Overview: " + overviewProduct, true);
		Reporter.log("Verifying the second product in Cart and Checkout Overview", true);
	    Assert.assertEquals(c2.getProductItem(1),product2,"Second product is not transferred correctly");
	    Reporter.log("PASS: Second product is transferred correctly: " + overviewProduct, true);
	    
	}
}
