/**
 * 
 */
package com.learnautomation.utility;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

/**
 * @author hp
 *
 */
public class BrowserFactory {
	
	 static WebDriver driver;
	
	public static WebDriver startApplication(WebDriver driver, String browserName, String appURL)
	{
		if(browserName.equalsIgnoreCase("Chrome"))
		{
			System.setProperty("webdriver.chrome.driver", "C:\\chromedriver.exe");
			driver  = new ChromeDriver();
			
		}
		else if(browserName.equalsIgnoreCase("firefox"))
		{ 
			System.setProperty("webdriver.gecko.driver", "C:\\Drivers\\geckodriver.exe");
			driver = new FirefoxDriver();
		}
		else if(browserName.equalsIgnoreCase("IE"))
		{
			
			driver = new InternetExplorerDriver();
		}
		else
		{
			System.out.println("This browser is not supported");
		}
		
		driver.manage().window().maximize();
		driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
		
		driver.get(appURL);
		
		return driver;
	}

	public static void quitBrowser()
	{
		driver.close();
		
	}
	
}
