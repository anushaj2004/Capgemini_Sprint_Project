package ObjRepo;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import GenericUtitlity.ExplicitWaitUtility;

public class LoginPage {

	WebDriver driver;

	public LoginPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements( driver,this);
	}
	
	@FindBy(id = "user-name")
	private WebElement username;
	
	@FindBy(id = "password")
	private WebElement password;
	
	@FindBy(id = "login-button")
	private WebElement loginbt;

	@FindBy(xpath = "//h3[text()='Epic sadface: Username and password do not match any user in this service']")
	private WebElement errorMessage;
	
	public String getErrorMessage() {
		return errorMessage.getText();
	}
	
	public boolean isErrorDispalyed() {
		return errorMessage.isDisplayed();
	}
	
	public void loginDetails(String un,String pw) throws InterruptedException
	{
	ExplicitWaitUtility.waitForElementToVisible(driver, username).clear();
		username.sendKeys(un);
		Thread.sleep(2000);
		password.clear();
		password.sendKeys(pw);
		Thread.sleep(2000);
		loginbt.click();
	}
	
}
