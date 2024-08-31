package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.Page_Home;
import pageObjects.Page_Login;
import pageObjects.Page_MyAccount;
import testBase.BaseTest_Class;

public class TC002_Login extends BaseTest_Class {

	@Test(groups={"Sanity","Master"})
	public void verifyLogin() {
		
		logger.info("**********Starting Execution of TC002_Login**********");
		
		try {
		Page_Home hp = new Page_Home(driver);
		hp.clickMyaccount();;
		hp.clickLogin();
		
		Page_Login lp = new Page_Login(driver);
		lp.enterEmailAddress(p.getProperty("email"));
		lp.enterPassword(p.getProperty("password"));
		lp.clickLogin();
		
		Page_MyAccount mac = new Page_MyAccount(driver);
		Boolean targetPage = mac.doesMyaccountPageExists();
		
		Assert.assertTrue(targetPage);    //Assert.assertEquals(targetPage, true, "Login Failed");
		}
		catch(Exception e) {
			
			Assert.fail();
		}
		
		logger.info("**********Completed Execution of TC002_Login**********");
	}
}
