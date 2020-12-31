package com.ndtv.testUtils;

import java.awt.AWTException;
import java.awt.HeadlessException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class TestUtil {

	static WebDriver driver;
	public static Integer PAGE_LOAD_TIMEOUT = 40;
	public static Integer IMPLICIT_WAIT = 40;
	
	public static void log(String msg) {
		System.out.println(msg);
		File destTxtLog = new File("/home/info/my-workspace/com.ndtv/TestReports/log.txt");
		try {
			FileWriter fw = new FileWriter(destTxtLog, true); // the true will
			// append the new data
			fw.write(msg + "\n");// appends the string to the file
			fw.close();
		} catch (IOException ioe) {
			System.err.println("IOException: " + ioe.getMessage());
		}
	}
	
	public static void wait(int numOfSeconds) throws InterruptedException {
		log("Waiting for " + numOfSeconds + " seconds...");
		Thread.sleep(numOfSeconds * 1000);
	}
	
	public static void Wait(WebElement element, int timeoutInSeconds)
			throws HeadlessException, IOException, AWTException {
		log("Waiting for " + element + " for up to " + timeoutInSeconds + " seconds.");
		try {
			WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
			wait.until(ExpectedConditions.visibilityOf(element));
//			wait.until(ExpectedConditions.elementToBeClickable(element));
			// Thread.sleep(2000);
			log("Element found.");
		} 
		catch (Exception e) {
//			log("*** Unable to find element");
//			log(e.getMessage());
//			log("***");
			// Fail the test

		}
	}
	
	public static void Wait(WebElement element, WebDriver browser, int timeoutInSeconds)
			throws HeadlessException, IOException, AWTException {
		try {
			WebDriverWait wait = new WebDriverWait(browser, timeoutInSeconds);
			wait.until(ExpectedConditions.visibilityOf(element));
			wait.until(ExpectedConditions.elementToBeClickable(element));
			// Thread.sleep(2000);
			log("Waiting for " + element + " for up to " + timeoutInSeconds + " seconds.");
		} catch (Exception e) {
//			log(e.getMessage());
			// Fail the test

		}
	}
		
	public static void click(String linkText) {
		driver.findElement(By.linkText(linkText)).click();
		log("Clicked on " + linkText + " link");
	}
	
	public static void clickJS(WebElement element) {
		try {
			element.click();
			log("Clicked on " + element);
		} catch (Exception e) {
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
			log("Clicked using JavaScript on element : " + element);
		}
	}
	
	public static void click(WebElement element) {
		element.click();
		log("Clicked on " + element);
	}


	public static String getTextByRemoveElement(String text) {
		try {
			int posOfColon = text.indexOf(":");
			text = text.substring(posOfColon + 1, text.length()).trim();
		} catch (Exception e) {
			text = null;
		}
		return text;
	}
	
	public static String getTextByRemoveElementLastColon(String text) {
		try {
			int posOfColon = text.lastIndexOf(":");
			text = text.substring(posOfColon + 1, text.length()).trim();
		} catch (Exception e) {
			text = null;
		}
		return text;
	}

	public static void pageLoadTime() {
		driver.manage().timeouts().pageLoadTimeout(TestUtil.PAGE_LOAD_TIMEOUT, TimeUnit.SECONDS);
	}

	public static Properties readFromPropertyFile() throws IOException {
		Properties prop = new Properties();
		prop = new Properties();
		FileInputStream ip = new FileInputStream(System.getProperty("user.dir")+ "/src/main/java/com/ndtv/configs/config.properties");
		prop.load(ip);
		return prop;
	}

}
