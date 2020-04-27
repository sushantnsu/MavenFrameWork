package com.learnautomation.testcases;

import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.learnautomation.pages.BaseClass;
import com.learnautomation.pages.LoginPage;
import com.learnautomation.utility.ExcelDataProvider;
import com.learnautomation.utility.Helper;


public class LoginTestCRM extends BaseClass {
	
	public static ExtentTest logger;
		
	@Test(priority = 1)
	public void loginApp()
	{
		
		 logger = report.createTest("Login to CRM");
		
		  //initializing LoginPage 
		  LoginPage loginPage = PageFactory.initElements(driver, LoginPage.class);
		  
		  logger.log(Status.INFO, "Starting Application");
		  
		  loginPage.loginToHomePage(excel.getStringData("Login", 0, 0), excel.getStringData("Login", 0, 1));
		  
		  
		  logger.log(Status.PASS, "Login Success");	  
		  
		 		 
	}
	
	@Test(priority = 2)
		public void loginApp1() {
			
			ExtentTest logger2 = report.createTest("Logout");
			logger2.log(Status.FAIL, "error is code");
		}
	
	}
	
	
