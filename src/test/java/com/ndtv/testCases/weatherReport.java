package com.ndtv.testCases;

import java.awt.AWTException;
import java.awt.HeadlessException;
import java.io.IOException;

import org.apache.commons.lang3.text.WordUtils;
import org.json.simple.parser.ParseException;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.ndtv.BaseClass.BaseClass;
import com.ndtv.comparator.Comparator;
import com.ndtv.getAPI.GetAPI;
import com.ndtv.pageObjects.HomePage;
import com.ndtv.pageObjects.WeatherPage;
import com.ndtv.testUtils.TestUtil;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;


public class weatherReport extends BaseClass{
	
	@Test(priority = 1)
	@Parameters({"cityName"})
	public void verifyWeatherOnUI(String cityName) throws HeadlessException, IOException, AWTException, InterruptedException {
		TestUtil.log("====================================================================================");
		TestUtil.log("==================================WEB WEATHER REPORT================================");
		TestUtil.log("====================================================================================");
		cityName = WordUtils.capitalizeFully(cityName);
		String url = "https://www.ndtv.com";
		TestUtil.log("====================================================================================");
		TestUtil.log("Navigating to url");
		driver.get(url);
		TestUtil.wait(5);
		HomePage hPage = new HomePage(driver);
//		TestUtil.pageLoadTime();
		hPage.click_submenu();
		hPage.click_weather_link();
		TestUtil.log("====================================================================================");
		TestUtil.log("Navigating to Weather Page");
		WeatherPage wPage = new WeatherPage(driver);
		wPage.type_searchBox(cityName);
		
	}
	
	@Test(priority = 2)
	@Parameters({"cityName"})
	public void verifyWeatherOnAPI(String cityName) throws ParseException {
		TestUtil.log("====================================================================================");
		TestUtil.log("================================API WEATHER REPORT==================================");
		TestUtil.log("====================================================================================");
		cityName = WordUtils.capitalizeFully(cityName);
		RestAssured.baseURI ="http://api.openweathermap.org/data/2.5"; 
			RequestSpecification request = RestAssured.given();
			
			Response response = request.queryParam("q", cityName) 
					                   .queryParam("appid", "7fe67bf08c80ded756e598d6f8fedaea") 
					                   .get("/weather");
			
			String jsonString = response.asString();
			TestUtil.log("====================================================================================");
			Assert.assertEquals(response.getStatusCode(), 200,"Status code is not 200.");
			TestUtil.log("====================================================================================");
			Assert.assertEquals(jsonString.contains(cityName), true,cityName + " is not in the API response");
			
			GetAPI.GetApi(jsonString);
	}
	
	@Test(priority = 3)
	public void ComparingAPIAndWebInCelsius() throws IOException {
		
		boolean b = Comparator.comparingWebAndAPIInCelsius();
		TestUtil.log("========================================================================================");
		Assert.assertTrue(b, "The temperature is different on api and ui.");
	}
	
	@Test(priority = 4)
	public void ComparingAPIAndWebInFahrenheit() throws IOException {
		
		boolean b = Comparator.comparingWebAndAPIInFahrenheit();
		TestUtil.log("========================================================================================");
		Assert.assertTrue(b, "The temperature is different on api and ui.");
	}
	
	@Test(priority = 5)
	public void ComparingAPIAndWebInHumidity() throws IOException {
		
		boolean b = Comparator.comparingWebAndAPIInHumidity();
		TestUtil.log("========================================================================================");
		Assert.assertTrue(b, "The temperature is different on api and ui.");
	}
}