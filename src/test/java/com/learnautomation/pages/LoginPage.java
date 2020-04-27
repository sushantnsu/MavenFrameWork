/**
 * 
 */
package com.learnautomation.pages;

import java.util.NoSuchElementException;

import org.openqa.selenium.Keys;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.learnautomation.utility.Helper;

import freemarker.template.utility.CaptureOutput;

/**
 * @author hp
 *
 */
public class LoginPage {
	
	WebDriver driver;
	String expectedErrorLoginText = "Invalid login";
	
	public LoginPage(WebDriver ldriver)
	{
		this.driver = ldriver;
	}
	
	@FindBy(how = How.XPATH, using = "//input[@name= 'email']")
	@CacheLookup
	WebElement emailAddress;
	
	@FindBy(how = How.XPATH, using = "//input[@name= 'password']")
	@CacheLookup
	WebElement password;
	
	@FindBy(how = How.XPATH, using = "//*[@id=\"ui\"]/div/div/form/div/div[3]")
	@CacheLookup
	WebElement loginButton;
	
	@FindBy(how = How.XPATH, using = "//*[@id=\"ui\"]/div/div/form/div/div[3]/p")
	@CacheLookup
	WebElement loginErrorMsg;
	
	public void loginToHomePage(String uid, String pwd)
	{
		emailAddress.sendKeys(uid);
		
		password.sendKeys(pwd);
		loginButton.click();
		loginError();
		
		
	}
	
	public void loginError() {
		
		String errorLoginText;
		try {
			WebDriverWait wait = new WebDriverWait(driver, 20);
			wait.until(ExpectedConditions.visibilityOf(loginErrorMsg));
			
			
		} catch (NoSuchElementException e1) {
			// TODO Auto-generated catch block
			System.out.println("execetion for time out is " + e1.getMessage());
		}
		errorLoginText = loginErrorMsg.getText();
		System.out.println(errorLoginText);
		
		if(errorLoginText.equalsIgnoreCase(expectedErrorLoginText))
		{
			
			Helper.captureScreenshot(driver);
			
			try {
			emailAddress.sendKeys(Keys.chord(Keys.CONTROL,"a", Keys.DELETE));
			password.sendKeys(Keys.chord(Keys.CONTROL,"a", Keys.DELETE));
			
			loginToHomePage("sushant.spyderslab@gmail.com", "Fadgjmptw1m");
			}catch(StaleElementReferenceException e) {
				
			}
		}
	}

}
