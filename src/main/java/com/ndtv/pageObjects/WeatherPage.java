package com.ndtv.pageObjects;

import java.awt.AWTException;
import java.awt.HeadlessException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.text.WordUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import com.ndtv.testUtils.TestUtil;


public class WeatherPage {

	WebDriver driver;
	public static String string_tempInCelsius;
	public static String string_tempInFahrenheit;
	public static String string_humidity; 
	
	public WeatherPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath = "//span[text()='Pin your City']")
	private WebElement txt_pin_your_city;
	
	@FindBy(xpath = "//*[@id='searchBox']")
	private WebElement input_searchBox;
	
	@FindBy(xpath = "/html/body/div[1]/img[@src='https://social.ndtv.com/static/Weather/report/images/ndtv_logo.png']")
	public WebElement ndtv_logo;
	
	@FindBy(xpath = "//div[text()='Current weather conditions in your city.']")
	private WebElement txt_under_logo;
	
	@FindBy(xpath = "//*[@id=\"map_canvas\"]//b[contains(text(),'Temp in Degrees: ')]/..")
	public WebElement tempInCelsius;
	
	@FindBy(xpath = "//*[@id=\"map_canvas\"]//b[contains(text(),'Temp in Fahrenheit: ')]/..")
	public WebElement tempInFahrenheit;
	
	@FindBy(xpath = "//*[@id=\"map_canvas\"]//b[contains(text(),'Humidity:')]/..")
	public WebElement humidity;
	
	
	
	
	public void type_searchBox(String city) throws HeadlessException, IOException, AWTException, InterruptedException {
		
		TestUtil.Wait(input_searchBox, 20);
		Assert.assertTrue(input_searchBox.isDisplayed(),input_searchBox+" is not displayed on the screen");
		input_searchBox.click();
		city = WordUtils.capitalizeFully(city);
		input_searchBox.sendKeys(city);
		String s = "//*[@id='messages']//label[@for='"+ city +"']";
		WebElement dropDown = driver.findElement(By.xpath(s));
		TestUtil.wait(5);
		TestUtil.Wait(dropDown, 20);
		TestUtil.click(dropDown);
		
		String cities_on_map = "//*[@id='map_canvas']//div[@class=\"cityText\"]";
		List<WebElement> list = driver.findElements(By.xpath(cities_on_map));
		int no_of_cities_on_map = list.size();
		
		ArrayList<String> cities = new ArrayList<String>();
		for (int i = 0; i < no_of_cities_on_map; i++) {
			cities.add(list.get(i).getText());
		}
		
		if(!(cities.contains(city))) {
			TestUtil.click(dropDown);
		}
		
		String mapCanvas = "//*[@id='map_canvas']//div[text()='"+city+"']";
		WebElement e = driver.findElement(By.xpath(mapCanvas));
		TestUtil.Wait(e, 20);		
		TestUtil.click(e);
		TestUtil.log("====================================================================================");
		TestUtil.log("==============================WEB WEATHER DETAIL ON MAP=============================");
		TestUtil.log("====================================================================================");
		string_tempInCelsius = TestUtil.getTextByRemoveElement(tempInCelsius.getText().trim());
		string_tempInFahrenheit = TestUtil.getTextByRemoveElement(tempInFahrenheit.getText().trim());
		string_humidity = TestUtil.getTextByRemoveElement(humidity.getText()).replaceAll("%","").trim();
		
		List<String> web_weather_detail = new ArrayList<String>();
		web_weather_detail.add(string_tempInCelsius);
		web_weather_detail.add(string_tempInFahrenheit);
		web_weather_detail.add(string_humidity);
		TestUtil.log("Website weather detail : " + web_weather_detail);
		
		
	}
	
	
	
	
	
	
	

}