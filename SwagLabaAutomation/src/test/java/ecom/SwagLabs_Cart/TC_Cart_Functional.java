package ecom.SwagLabs_Cart;

import java.io.IOException;

import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

import GenericUtitlity.ScreenshotUtility;
import ObjRepo.CartPage;
import ObjRepo.HomePage;
import ObjRepo.ProductPage;
import base.BaseClass;

public class TC_Cart_Functional extends BaseClass{

	@Test(groups = "functional")
	public void tc_001() throws IOException, InterruptedException
	{
		Reporter.log("Verify that the added product is displayed in the cart with a quantity of 1.",true);
		ProductPage p=new ProductPage(driver);
		p.selectingSorting("Price (low to high)");
		p.getAddToCart(0);
		Reporter.log("Step 1: Add one product to the cart.", true);
		HomePage h=new HomePage(driver);
		h.getCart();
		Thread.sleep(2000);
		CartPage c=new CartPage(driver);
		Assert.assertEquals(c.getProductCount(),1,"Product is not added to cart");
		Reporter.log("Step 2: Open the cart page and verify the product count.", true);
		ScreenshotUtility.takeScreenshot(driver, "TC_Cart_ProductAdded");
		Reporter.log("Test case passes:Product added to cart and verified successfully",true);
	}
}
