package ecom.SwagLabs_Cart;

import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

import ObjRepo.CartPage;
import ObjRepo.HomePage;
import ObjRepo.ProductPage;
import base.BaseClass;

public class TC_Cart_Integration extends BaseClass{
@Test(groups = "integration")
public void tc_003(){
	Reporter.log("Verify that the selected product is correctly added to the cart.", true);
	ProductPage p=new ProductPage(driver);
	String selectedProduct=p.getProductName(0).getText();
	p.getAddToCart(0);
	Reporter.log("Step 1: Select a product and add it to the cart.", true);
	HomePage h=new HomePage(driver);
	h.getCart();
	CartPage c=new CartPage(driver);
	String cartProduct=c.getProductItem(0);
	Reporter.log("Step 2: Open the cart page and verify that the selected product is displayed correctly.", true);
	Assert.assertEquals(cartProduct,selectedProduct,"Selected product is not correctly transferred to cart");
	Reporter.log("Test Case Passed: The selected product is correctly transferred to the cart.", true);
}
}
