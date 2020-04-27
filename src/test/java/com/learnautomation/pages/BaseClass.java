/**
 * 
 */
package com.learnautomation.pages;

import java.io.IOException;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Parameters;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.beust.jcommander.Parameter;
import com.learnautomation.testcases.LoginTestCRM;
import com.learnautomation.utility.BrowserFactory;
import com.learnautomation.utility.ConfigDataProvider;
import com.learnautomation.utility.ExcelDataProvider;
import com.learnautomation.utility.Helper;

/**
 * @author hp
 *
 */
public class BaseClass {

	public WebDriver driver;

	public ExcelDataProvider excel;
	public ConfigDataProvider config;
	public ExtentHtmlReporter extent;
	public ExtentReports report;
	public ExtentTest logger;

	@BeforeSuite
	public void setUpSuite() 
	{
		Reporter.log("Setting up reports and Test Started", true);
		
		
		excel = new ExcelDataProvider();
		config = new ConfigDataProvider();

		extent = new ExtentHtmlReporter("./Reports/"+ Helper.getCurrentDateTime()+ "CrmAutomation.html");
		report = new ExtentReports();

		report.attachReporter(extent);
		
		Reporter.log("Before Suite data has been approved", true);
	}

	@Parameters({"browser", "urlToBeTested"})  // for passign argument
	@BeforeClass
	public void setUp(String browser, String url) { // for passing argument
	
		//public void setUp() { //for testng file
		
		Reporter.log("Browser data is started", true);
		// selection of the broswer

		//driver = BrowserFactory.startApplication(driver, config.getBrowser(), config.getUrl());
		
		driver = BrowserFactory.startApplication(driver, browser, url);
		
		Reporter.log("Browser data loading has been completed", true);
	}

	@AfterMethod
	public void tearDownMethod(ITestResult result) // as soon as your test case gets complete this result variable
	// will have all the information
	{
		Reporter.log("Report is starting", true);

		if (result.getStatus() == ITestResult.FAILURE) {

			try {
				LoginTestCRM.logger.fail("Test failed",
						MediaEntityBuilder.createScreenCaptureFromPath(Helper.captureScreenshot(driver)).build());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		else if (result.getStatus() == ITestResult.SUCCESS) {
			try {
				LoginTestCRM.logger.pass("Test passed",
						MediaEntityBuilder.createScreenCaptureFromPath(Helper.captureScreenshot(driver)).build());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		report.flush();
		System.out.println("flush is ok ");
		Reporter.log("Extent report has been completed ", true);
	}
	
	@AfterClass
	public void closeBroswer() {
		driver.close(); 
	}

}
