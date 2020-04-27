/**
 * 
 */
package com.learnautomation.utility;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.io.FileHandler;

/**
 * @author hp
 *
 *here we handle Screenshot, alerts, frame, windows, sync issues, javascript executor
 */
public class Helper {
	
	//if you don't want to make this funtion as static than you can create obj of Helper class and use that to call
	public static String captureScreenshot(WebDriver driver)
	{
		File src = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		
		String screenshotPath = System.getProperty("user.dir") + "./Screenshots/" + getCurrentDateTime() +" Login.png";
		
					//	(from, to)
		try {
			FileHandler.copy(src, new File("./Screenshots/" + getCurrentDateTime() +" Login.png"));
			System.out.println("Screeshot captured");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("Unable to capture screenshot " + e.getMessage());
		}
		//return the path so we can use it
		return screenshotPath;
	}
	
	public static String getCurrentDateTime()
	{
		DateFormat customFormat = new SimpleDateFormat("MM_dd_yyyy_HH_mm_ss");
		
		Date currentDate =  new Date();
		
		return customFormat.format(currentDate);
	}
	
	
	

}
