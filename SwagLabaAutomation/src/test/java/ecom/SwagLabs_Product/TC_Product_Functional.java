package ecom.SwagLabs_Product;

import static org.testng.Assert.assertTrue;

import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

import GenericUtitlity.PropertiesUtitlity;
import ObjRepo.CartPage;
import ObjRepo.HomePage;
import ObjRepo.LoginPage;
import ObjRepo.ProductPage;
import ObjRepo.ProductPage2;
import base.BaseClass;
import base.BaseClassForLogin;

public class TC_Product_Functional extends BaseClassForLogin{
   @Test(priority=1,groups = "functional")
   public void tc_verifyProductPageDisplayed() throws InterruptedException, Exception {
	   Reporter.log("Step 1:Login to the application");
	   LoginPage l=new LoginPage(driver);
	   Reporter.log("Step 2:Enter username and password",true);
	   PropertiesUtitlity p=new PropertiesUtitlity();
	   l.loginDetails(p.readDatafromPropertyFile("username"), p.readDatafromPropertyFile("password"));
	   Reporter.log("Step 3:Navigate to the  product page",true);
	   ProductPage p1=new ProductPage(driver);
	   Assert.assertTrue(driver.getCurrentUrl().contains("/inventory.html"),"Product page is not displayed");
	   Reporter.log("Test Case Passed: Product page was successfully displayed,true");
	   HomePage h=new HomePage(driver);
	   h.getHamburger();
	   h.getLogout();
   }
   
   @Test(priority = 2,groups = "{functional,integration}")
   public void verifyProductDisplayedInCart() throws InterruptedException, Exception {
	   Reporter.log("Step 1:Login to the application");
	   LoginPage l=new LoginPage(driver);
	   Reporter.log("Step 2:Enter username and password",true);
	   PropertiesUtitlity p=new PropertiesUtitlity();
	   l.loginDetails(p.readDatafromPropertyFile("username"), p.readDatafromPropertyFile("password"));
	   Reporter.log("Step 3:Navigate to the  product page",true);
	   ProductPage p1=new ProductPage(driver);
	   Reporter.log("Step 3:select on product name ",true);
	   Thread.sleep(4000);
	   System.out.println(p1.getProductName(0).getText());
	   p1.getProductName(0).click(); 
	   Thread.sleep(2000);
	   Reporter.log("Step 4:store the product name and click add to cart on the inventry item page ",true);
	   ProductPage2 p2=new ProductPage2(driver);
	   String selectedProduct =p2.getProductName().getText();
	   System.out.println(selectedProduct);
	   p2.getAddToCartInventry().click();
	   Reporter.log("Step 4:Click on view cart");
	   HomePage h=new HomePage(driver);
	   h.getCart();
	   Reporter.log("veirfy that the selected product is correctly displayed in the cart page,true");
	   CartPage c=new CartPage(driver);
	   String cartProd= c.getProductItem(0);
	   Assert.assertEquals(cartProd, selectedProduct,"Selected product is not displayed correctly in the cart");
	   Reporter.log("Test Case Passed: Selected product is correctly displayed in the cart ");
	   h.getHamburger();
       h.getLogout();
   }
   
}
