package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.Page_Home;
import pageObjects.Page_Login;
import pageObjects.Page_MyAccount;
import testBase.BaseTest_Class;
import utilities.DataProviders;

public class TC003_LoginDDT extends BaseTest_Class {
	
	
	@Test(dataProvider="LoginData", dataProviderClass=DataProviders.class,groups={"DataDriven","Master"}	)
	public void verifyDDT (String email, String pwd, String exp) {
		
		logger.info("**********Starting Execution of TC002_LoginDDT**********");
		
		try {
		Page_Home hp = new Page_Home(driver);
		hp.clickMyaccount();;
		hp.clickLogin();
		
		Page_Login lp = new Page_Login(driver);
		lp.enterEmailAddress(email);
		lp.enterPassword(pwd);
		lp.clickLogin();
		
		Page_MyAccount mac = new Page_MyAccount(driver);
		Boolean targetPage = mac.doesMyaccountPageExists();
		
		if(exp.equalsIgnoreCase("Valid")) {
			
			if(targetPage==true) {
				mac.clickLogout();
				Assert.assertTrue(true);
			}
			else {
				Assert.fail();
			}
		}
		
		if(exp.equalsIgnoreCase("inValid")) {
			
			if(targetPage==true) {
				mac.clickLogout();
				Assert.fail();
			}
			else {
				Assert.assertTrue(true);
			}
		}
		} catch(Exception e) {
			Assert.fail();
		}
		
		logger.info("**********Completed Execution of TC003_LoginDDT**********");
	}	
}
