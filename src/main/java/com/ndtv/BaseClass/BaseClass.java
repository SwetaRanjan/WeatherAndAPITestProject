package com.ndtv.BaseClass;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.support.events.WebDriverEventListener;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import com.ndtv.testUtils.TestUtil;


public class BaseClass {
	
	public static WebDriver driver;
	public static Properties prop;
	public  static EventFiringWebDriver e_driver;
	public static WebDriverEventListener eventListener;
	
	public BaseClass(){
		try {
			prop = new Properties();
			FileInputStream ip = new FileInputStream(System.getProperty("user.dir")+ "/src/main/java/com/ndtv/configs/config.properties");
			prop.load(ip);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@BeforeClass
	public static void initialization(){

		String browserName = prop.getProperty("browser");
		
		if(browserName.equals("chrome")){
			
			System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "/Drivers/chromedriver");
			ChromeOptions chrome_options = new ChromeOptions();
			chrome_options.addArguments("--disable-notifications");
			
			driver = new ChromeDriver(chrome_options);
		}
		else if(browserName.equals("FF")){
			
			System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir") + "/Drivers/geckodriver");
			FirefoxOptions options = new FirefoxOptions();
			options.setProfile(new FirefoxProfile());
			options.addPreference("dom.webnotifications.enabled", false);
			
			driver = new FirefoxDriver(options);
 
		}
		
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().pageLoadTimeout(TestUtil.PAGE_LOAD_TIMEOUT, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(TestUtil.IMPLICIT_WAIT, TimeUnit.SECONDS);
		
	}
	@AfterClass
	public static void close(){
		
		driver.quit();
	}
}
