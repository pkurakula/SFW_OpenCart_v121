package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class Page_AcctRegn extends Page_Base {
	
	public Page_AcctRegn(WebDriver driver) {
		super(driver);
	}
	
	@FindBy(xpath = "//input[@id='input-firstname']") 
	WebElement txtFirstname;

	@FindBy(xpath = "//input[@id='input-lastname']") 
	WebElement txtLastname;
	
	@FindBy(xpath = "//input[@id='input-email']") 
	WebElement txtemail;

	@FindBy(xpath = "//input[@id='input-telephone']") 
	WebElement txtTelephone;
	
	@FindBy(xpath = "//input[@id='input-password']") 
	WebElement txtPwd;

	@FindBy(xpath = "//input[@id='input-confirm']") 
	WebElement txtPwdconfirm;
	
	@FindBy(xpath = "//input[@name='agree']") 
	WebElement chkAgree;

	@FindBy(xpath = "//input[@value='Continue']") 
	WebElement btnContinue;

	@FindBy(xpath = "//h1[normalize-space()='Your Account Has Been Created!']") 
	WebElement msgConfirmation;
	
	public void setFirstname(String fName) {
		txtFirstname.sendKeys(fName);
	}
	
	public void setLastname(String lName) {
		txtLastname.sendKeys(lName);
	}
	
	public void setEmail(String eMail) {
		txtemail.sendKeys(eMail);
	}
	
	public void setTelephone(String tel) {
		txtTelephone.sendKeys(tel);
	}
	
	public void setPassword(String pwd) {
		txtPwd.sendKeys(pwd);
	}
	
	public void setPasswordconfirm(String cpwd) {
		txtPwdconfirm.sendKeys(cpwd);
	}
	
	public void clickAgree() {
		chkAgree.click();
	}
	
	public void clickContinue() {
		btnContinue.click();
	}
		
	public String getConfirmationMsg() {
		try {
			return (msgConfirmation.getText());
		} catch (Exception e) {
			return (e.getMessage());
		}
	}
}
