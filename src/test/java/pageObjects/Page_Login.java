package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class Page_Login extends Page_Base {
	
	public Page_Login(WebDriver driver) {
		super(driver);
	}
	
	@FindBy(xpath = "//*[@id='input-email']")
	WebElement txtEmailAddress;

	@FindBy(xpath = "//*[@id='input-password']") 
	WebElement txtPassword;

	@FindBy(xpath = "//input[@value='Login']") 
	WebElement btnLogin;
		
		public void enterEmailAddress(String email) {
			txtEmailAddress.sendKeys(email);
		}
		
		public void enterPassword(String pwd) {
			txtPassword.sendKeys(pwd);
		}

		public void clickLogin() {
			btnLogin.click();
		}
}
