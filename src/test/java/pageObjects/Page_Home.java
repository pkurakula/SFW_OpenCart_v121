package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class Page_Home extends Page_Base {
	
	public Page_Home(WebDriver driver) {
		super(driver);
	}
	
@FindBy(xpath = "//span[normalize-space()='My Account']") 
WebElement lnkMyaccount;

@FindBy(xpath = "//a[normalize-space()='Register']") 
WebElement lnkRegister;

@FindBy(linkText = "Login") 
WebElement lnkLogin;
	
	public void clickMyaccount() {
		lnkMyaccount.click();
	}
	
	public void clickRegister() {
		lnkRegister.click();
	}

	public void clickLogin() {
		lnkLogin.click();
	}
}
