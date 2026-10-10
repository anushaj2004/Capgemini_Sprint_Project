package ObjRepo;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import GenericUtitlity.ExplicitWaitUtility;

public class CheckoutPage1 {
WebDriver driver;

public CheckoutPage1(WebDriver driver) {
	this.driver = driver;
	PageFactory.initElements(driver,this);
}

@FindBy(id = "first-name")
private WebElement firstName;

@FindBy(id = "last-name")
private WebElement lastName;

@FindBy(id = "postal-code")
private WebElement postalCode;

@FindBy(id = "continue")
private WebElement continuebt;

@FindBy(xpath = "//h3[text()='Error: First Name is required']")
private WebElement errorMessage;

@FindBy(id = "cancel")
private WebElement cancel;


@FindBy(className = "title")
private WebElement checkoutInformation;

public void getFirstName(String value) {
	firstName.sendKeys(value);
}

public void getLastName(String value) {
	lastName.sendKeys(value);
}

public void getPostalCode(String value) {
	postalCode.sendKeys(value);
}

public void getContinuebt() {
	continuebt.click();
}



public String getErrorMessage() {
	return errorMessage.getText();
}

public WebElement getCancel() {
	return cancel;
}

public WebElement isCheckoutInformationDisplayed() {
    return checkoutInformation;
}

public WebElement isFirstNameDisplayed() {
	return firstName;
}

public WebElement isLastNameNameDisplayed() {
	return lastName;
}

public WebElement isPostalCodeDisplayed() {
	return postalCode;
}

public WebElement isContinueDisplayed() {
	return continuebt;
}

public WebElement isCancelDisplayed() {
	return cancel;
	
}

public WebElement isErrorDisplayed()
{
	return errorMessage;
}

public void CheckoutDetails(String fn, String ln, String pc) throws InterruptedException {
	// TODO Auto-generated method stub
	ExplicitWaitUtility.waitForElementToVisible(driver, firstName).sendKeys(fn);
	ExplicitWaitUtility.waitForElementToVisible(driver, lastName).sendKeys(ln);
	ExplicitWaitUtility.waitForElementToVisible(driver, postalCode).sendKeys(pc);
	continuebt.click();
	Thread.sleep(2000);
}

}
