package ecom.SwagLabs_Cart;



import java.io.IOException;

import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

import ObjRepo.CartPage;
import ObjRepo.HomePage;
import ObjRepo.ProductPage;
import base.BaseClass;

public class TC_Cart_Negative extends BaseClass {
	
	@Test(priority = 1, groups = {"Integration","Negative"})
	public void tc_004() throws IOException, InterruptedException
	{
		Reporter.log("Step 1: Navigate to the cart page without adding any product.", true);
		HomePage h=new HomePage(driver);
	    Thread.sleep(2000);
		h.getCart();
		CartPage c=new CartPage(driver);
		Thread.sleep(2000);
		Reporter.log("Step 2: Verify that the cart is empty and displays zero products.", true);
		Assert.assertEquals(c.getProductCount(),0,"cart is displayeing a product item even though no product was added");
		Reporter.log("Test Case Passed: Cart is empty and displays zero products.", true);
	}
	@Test(priority = 2, groups = {"functional","Negative"})
	public void tc_002() throws IOException, InterruptedException
	{
		ProductPage p=new ProductPage(driver);
		Reporter.log("Step 1: Add one product to the cart.", true);
		p.getAddToCart(0);
		HomePage h=new HomePage(driver);
		h.getCart();
		CartPage c=new CartPage(driver);
		Reporter.log("Step 2: Navigate to the cart page and verify that one product is displayed.", true);
		Assert.assertEquals(c.getProductCount(),1,"Product is not added to cart");
		Reporter.log("Step 3: Click the Remove button to remove the product from the cart.", true);
		c.getRemoveBt(0);
		Reporter.log("Step 4: Verify that the cart contains zero products after removal.", true);
		Assert.assertEquals(c.getProductCount(),0,"Product is still in cart after removal");
		Reporter.log("Test Case Passed: Product was successfully removed from the cart, and the cart is now empty.", true);
	}
}
