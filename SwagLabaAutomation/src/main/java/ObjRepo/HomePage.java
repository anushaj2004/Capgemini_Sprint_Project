package ObjRepo;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import GenericUtitlity.ExplicitWaitUtility;

public class HomePage {
	WebDriver driver;
	public HomePage(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(id = "react-burger-menu-btn")
	private WebElement hamburger;
	
	@FindBy(linkText = "All Items")
	private WebElement allItems;
	
	@FindBy(linkText = "About")
	private WebElement about;
	
	@FindBy(linkText = "Logout")
	private WebElement logout;
	
	@FindBy(linkText = "Reset App State")
	private WebElement reset;
	
	@FindBy(xpath = "//a[@class='shopping_cart_link']")
	private WebElement cart;

	public void getHamburger() {
		hamburger.click();
	}

	public WebElement getAllItems() {
		return allItems;
	}

	public void getAbout() {
		about.click();
	}

	public void getLogout() {
		 logout.click();
	}

	public void getReset() {
		 reset.click();
	}
	
	public void getCart() {
		ExplicitWaitUtility.waitForElementToVisible(driver, cart).click();
	}
}
