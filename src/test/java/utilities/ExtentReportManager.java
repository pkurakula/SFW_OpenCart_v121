package utilities;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.ImageHtmlEmail;
import org.apache.commons.mail.resolver.DataSourceUrlResolver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import testBase.BaseTest_Class;

public class ExtentReportManager implements ITestListener {
	
	public ExtentSparkReporter sparkReporter;   //UI of the Report
	public ExtentReports extent;   //Populate common info(OS, Module, User/name, browser)
	public ExtentTest test;   //creating test case entries, updating test status
	
	String repName;
	
	public void onStart(ITestContext testContext) {
		
		/*
		SimpleDateFormat df = new SimpleDateFormat("yyyy.MM.dd.HH.ss");
		Date dt = new Date();
		String currentdatetimestamp = df.format(dt);
		*/
								//OR
		String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date()); //time stamp
		
		repName = "Test-Report-"+timeStamp+".html";
		sparkReporter = new ExtentSparkReporter(".\\reports\\" + repName); //specific location of the report
		//sparkReporter = new ExtentSparkReporter("user.dir")+ "/reports/myReports.html"); //specific location of the report
		
		sparkReporter.config().setDocumentTitle("Automation Report");  //Title of Report
		sparkReporter.config().setReportName("OpenCart Framework"); //Name of the Report
		sparkReporter.config().setTheme(Theme.STANDARD);
		
		extent = new ExtentReports();
		extent.attachReporter(sparkReporter);
		
		extent.setSystemInfo("Application", "OpenCart");
		extent.setSystemInfo("Module", "Admin");
		extent.setSystemInfo("Sub Module", "Customer");
		extent.setSystemInfo("User Name", System.getProperty("user.name"));
		extent.setSystemInfo("Environment", "QA");
		
		String os = testContext.getCurrentXmlTest().getParameter("os");
		extent.setSystemInfo("Browser", os);
		
		String browser = testContext.getCurrentXmlTest().getParameter("browser");
		extent.setSystemInfo("Operating System", browser);
		
		List<String> includedGroups = testContext.getCurrentXmlTest().getIncludedGroups();
		if(!includedGroups.isEmpty()) {
		extent.setSystemInfo("Groups", includedGroups.toString());	
		}
	}
	
	public void onTestSuccess(ITestResult result) {
		
		test = extent.createTest(result.getTestClass().getName());
		test.assignCategory(result.getMethod().getGroups());
		//test.createNode(result.getName());
		test.log(Status.PASS, "Test "+ result.getName()+" is Passed");
	}
	
	public void onTestFailure(ITestResult result) {
		
		test = extent.createTest(result.getTestClass().getName());
		//test.createNode(result.getName());
		test.assignCategory(result.getMethod().getGroups());
		
		test.log(Status.FAIL, "Test "+ result.getTestClass().getName()+" is Failed");
		test.log(Status.INFO, "Reason: "+ result.getThrowable());
		
		try {
			String imgPath = new BaseTest_Class().captureScreen(result.getName());
			test.addScreenCaptureFromPath(imgPath);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}
	
	public void onTestSkipped(ITestResult result) {
		test = extent.createTest(result.getTestClass().getName());
		//test.createNode(result.getName());
		test.assignCategory(result.getMethod().getGroups());
		test.log(Status.SKIP, "Test "+ result.getName()+" Skipped");
		test.log(Status.INFO, result.getThrowable().getMessage());
		
	}
	
	public void onFinish(ITestContext context) {
		extent.flush();
		
		String pathOfExtentReport = System.getProperty("user.dir")+"\\reports\\"+repName;
		//String pathOfExtentReport = System.getProperty(".\\reports+repName");
		File extentReport = new File(pathOfExtentReport);
		
		try {
			Desktop.getDesktop().browse(extentReport.toURI());
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		/*
		try {
			URL url = new URL("file:///"+System.getProperty("user.dir")+"\\reports\\"+repName);
				
		//Create email message
		ImageHtmlEmail email = new ImageHtmlEmail();
		email.setDataSourceResolver(new DataSourceUrlResolver(url));
		email.setHostName("smtp.googlemail.com");
		email.setSmtpPort(465);
		email.setAuthenticator(new DefaultAuthenticator("prabhu.kurakula@gmail.com","srilakshmi1"));
		email.setSSLOnConnect(true);
		email.setFrom("prabhu.kurakula@gmail.com");
		email.setSubject("Opencart Automation Test Results");
		email.setMsg("Please find attached Automation Testing Results Report.....");
		email.addTo("prabhu.kurakula@gmail.com");
		email.attach(url, "extent report", "please check report...");
		email.send();
		} catch (Exception e) {
			e.printStackTrace();
		} */
	}

}
