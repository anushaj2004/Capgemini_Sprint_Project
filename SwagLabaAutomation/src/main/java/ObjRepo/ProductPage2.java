package ObjRepo;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import GenericUtitlity.ExplicitWaitUtility;

public class ProductPage2 {
 WebDriver driver;

 public ProductPage2(WebDriver driver) {
	this.driver = driver;
	PageFactory.initElements(driver,this);
 }
 
 @FindBy(xpath = "//div[@class='inventory_details_name large_size']")
 private WebElement productName;

 @FindBy(id = "add-to-cart")
 private WebElement addToCartInventry;

 public WebElement getProductName() {
	return ExplicitWaitUtility.waitForElementToVisible(driver, productName) ;
 }

 public WebElement getAddToCartInventry() {
	return addToCartInventry;
 }
 
}
