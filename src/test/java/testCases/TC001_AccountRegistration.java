package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.Page_AcctRegn;
import pageObjects.Page_Home;
import testBase.BaseTest_Class;

public class TC001_AccountRegistration extends BaseTest_Class {

	@Test(groups={"Regression","Master"})
	public void verifyAccountRegn() throws InterruptedException {
		
		logger.info("**********Starting Execution of TC001_Registration**********");
		
		try {
		Page_Home hp = new Page_Home(driver);
		hp.clickMyaccount();
		logger.info("**********clicked on Account Link**********");
		
		hp.clickRegister();
		logger.info("**********Clicked on Account Link**********");
		
		Page_AcctRegn ap = new Page_AcctRegn(driver);
		logger.info("**********Provideng Customer Information**********");
		ap.setFirstname(randomName());
		ap.setLastname(randomName());
		ap.setEmail(randomEmail());
		ap.setTelephone(randomNumber());
		String pass = randomPassword();
		ap.setPassword(pass);
		ap.setPasswordconfirm(pass);
		ap.clickAgree();
		ap.clickContinue();
		
		logger.info("**********Validating Expected Message**********");
		String confmMsg = ap.getConfirmationMsg();
		Assert.assertEquals(confmMsg, "Your Account Has Been Created!");
		}
		catch(Exception e) {
			logger.error("Test failed");
			logger.debug("Debug logs....");
			Assert.fail();
		}
		
		logger.info("**********Completed Execution of TC001_Registration**********");
	}
}
