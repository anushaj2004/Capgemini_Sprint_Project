package ObjRepo;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CheckoutPage3 {
WebDriver driver;

public CheckoutPage3(WebDriver driver) {
	this.driver = driver;
	PageFactory.initElements(driver,this);
}

@FindBy(xpath = "//h2[text()='Thank you for your order!']")
private WebElement confirmationMessage;

public String getConfirmationMessage() {
	return confirmationMessage.getText();
}


}
