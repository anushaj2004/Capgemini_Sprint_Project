package ecom.SwagLabs_Checkout;

import java.io.FileInputStream;

import java.io.IOException;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.WebElement;
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

public class TC_Checkout_Functional extends BaseClass {
	
  @Test(priority = 1, groups ="functional")
  public void verifyCheckoutPage() throws InterruptedException
  {
	  Reporter.log("Step 1:Navigate & sort Products",true);
	  ProductPage p=new ProductPage(driver);
	  p.selectingSorting("Price (low to high)");
	  Reporter.log("Step 2:Add First product to Cart",true);
	  p.getAddToCart(0);
	  Reporter.log("Navigate to Home Page",true);
	  HomePage h=new HomePage(driver);
	  h.getCart();
	  Reporter.log("Step 2: navigate to Cart Page and verify product Name in Cart");
	  CartPage c=new CartPage(driver);
	 String actualProduct= c.getProductItem(0);
	 Assert.assertEquals(actualProduct, "Sauce Labs Onesie");
	 Reporter.log("Step 3:Verified product in cart is :"+actualProduct,true);
	 Reporter.log("Step 4:Navigate to Checkout page by clicking checkout button and verify that checkout page is displayed",true);
	 c.getCheckoutbt();
	 CheckoutPage1 c1=new CheckoutPage1(driver);
	 String actualUrl=driver.getCurrentUrl();
	 Assert.assertTrue(actualUrl.contains("https://www.saucedemo.com/checkout-step-one.html"));  
	 Reporter.log("Test Case Passed: verified Checkout page",true);
  }
  
  @DataProvider(name = "checkoutData")
  public Object[][] checkoutData() throws IOException {
      ExcelUtility e=new ExcelUtility();
      return e.getMultipleData();
  }
  @Test(dataProvider = "checkoutData" ,  priority=2 , groups ="functional")
  public void verifyProductCheckout(String fn,String ln,String pc) throws InterruptedException, IOException{
	  Reporter.log("Step 1:Naviagte to Product page and Sort products",true);
	  ProductPage p=new ProductPage(driver);
	  p.selectingSorting("Price (low to high)");
	  Reporter.log("Step 2:Store the selected product name and add to cart",true);
	  String selectedProduct=p.getProductName(0).getText();
	  p.getAddToCart(0);
	  Reporter.log("Step 3:Navigate to home page and click on view cart",true);
	  HomePage h=new HomePage(driver);
	  h.getCart();
	  Reporter.log("Step 4:Navigate to Cart page and click on Checkout button",true);
	  CartPage c=new CartPage(driver);
	 c.getCheckoutbt();
	 Reporter.log("Step 5:Enter the firstname, lastname and postalcode",true);
	 CheckoutPage1 c1=new CheckoutPage1(driver);
	 c1.CheckoutDetails(fn,ln,pc);
	 Reporter.log("Step 6:Navigate to Checkout overview page and store the product item name ",true);
	 CheckoutPage2 c2=new CheckoutPage2(driver);
	 String actualProduct=c2.getProductItem(0);
	 Reporter.log("Step 7:verify the selected product from product to the actual product in checkout overview",true);
	 Assert.assertEquals(actualProduct, selectedProduct); 
	 Reporter.log("Test Case Passed: verified the product on checkout page matches"+selectedProduct+":"+actualProduct,true);
  }
}
