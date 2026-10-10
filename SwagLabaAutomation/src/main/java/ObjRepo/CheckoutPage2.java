package ObjRepo;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

public class CheckoutPage2 {
WebDriver driver;

public CheckoutPage2(WebDriver driver) {
	this.driver = driver;
	PageFactory.initElements(driver,this);
}

@FindBy(className = "inventory_item_name")
private List<WebElement> productItem;

@FindBy(id = "finish")
private WebElement finishbt;

@FindBy(id = "cancel")
private WebElement cancel;

@FindBy(className = "title")
private WebElement checkoutOverview;

@FindBy(className = "cart_quantity")
private WebElement quantity;

@FindBy(className = "inventory_item_price")
private WebElement productPrice;

@FindBy(xpath = "//div[text()='Payment Information:']")
private WebElement paymentInformation;

@FindBy(xpath = "//div[text()='Shipping Information:']")
private WebElement shippingInformation;

@FindBy(className = "summary_subtotal_label")
private WebElement itemTotal;

@FindBy(className = "summary_tax_label")
private WebElement tax;

@FindBy(className = "summary_total_label")
private WebElement total;

@FindBy(xpath = "//div[text()='Payment Information']/following-sibling::div")
private WebElement paymentValue;

@FindBy(xpath = "//div[text()='Shipping Information']/following-sibling::div")
private WebElement shippingValue;

public String getProductItem(int index) {
	 
	return productItem.get(index).getText();

 }

public int getProductCount()
{
	return productItem.size();
}

public void getFinishBt() {
	finishbt.click();
}

public void getCancel() {
	cancel.click();
}

public boolean isCheckoutOverviewDisplayed() {
    return checkoutOverview.isDisplayed();
}

//public boolean isProductNameDisplayed() {
//    return productItem.isDisplayed();
//}


public boolean isQuantityDisplayed() {
    return quantity.isDisplayed();
}


public boolean isProductPriceDisplayed() {
    return productPrice.isDisplayed();
}


public boolean isPaymentInformationDisplayed() {
    return paymentInformation.isDisplayed();
}


public boolean isShippingInformationDisplayed() {
    return shippingInformation.isDisplayed();
}


public boolean isItemTotalDisplayed() {
    return itemTotal.isDisplayed();
}


public boolean isTaxDisplayed() {
    return tax.isDisplayed();
}


public boolean isTotalDisplayed() {
    return total.isDisplayed();
}


public boolean isFinishDisplayed() {
    return finishbt.isDisplayed();
}


public boolean isCancelDisplayed() {
    return cancel.isDisplayed();
}


public String getPaymentInformation() {
    return paymentValue.getText();
}

public String getShippingInformation() {
    return shippingValue.getText();
}




}
