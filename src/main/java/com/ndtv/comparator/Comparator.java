package com.ndtv.comparator;

import java.io.IOException;
import java.util.Properties;

import com.ndtv.getAPI.GetAPI;
import com.ndtv.pageObjects.WeatherPage;
import com.ndtv.testUtils.TestUtil;

public class Comparator {
	
	public static boolean comparingWebAndAPIInCelsius() throws IOException {
		
		Properties prop = TestUtil.readFromPropertyFile();
		String magnitude_of_variation = prop.getProperty("magnitude_of_variation_temperature");
		double expectedRange = Double.parseDouble(magnitude_of_variation);
		double actualRange = Math.abs(Double.parseDouble((WeatherPage.string_tempInCelsius)) - (Converter.KelvinToCelsius(GetAPI.api_temp)));
		TestUtil.log("====================================================================================");
		TestUtil.log("==============================Temperature in Celsius================================");
		TestUtil.log("====================================================================================");
		if(Double.compare(expectedRange, actualRange) < 0) {
			TestUtil.log("====================================================================================");
			TestUtil.log("Expected Range is greater than Actual range by " + actualRange);
			return true;
		}
		else if(Double.compare(expectedRange, actualRange) == 0) {
			TestUtil.log("====================================================================================");
			TestUtil.log("Expected range is equal to actual range and it is " + expectedRange);
			return true;
		}
		else {
			TestUtil.log("====================================================================================");
			TestUtil.log("Actual Range is more than expected range");
			return false;
		}
	}
	
	
public static boolean comparingWebAndAPIInFahrenheit() throws IOException {
		
		Properties prop = TestUtil.readFromPropertyFile();
		String magnitude_of_variation = prop.getProperty("magnitude_of_variation_temperature");
		double expectedRange = Double.parseDouble(magnitude_of_variation);
		double actualRange = Math.abs(Double.parseDouble((WeatherPage.string_tempInFahrenheit)) - (Converter.Kelvin_to_Fahrenheit(GetAPI.api_temp)));
		TestUtil.log("====================================================================================");
		TestUtil.log("==========================Temperature in Fahrenheit=================================");
		TestUtil.log("====================================================================================");
		if(Double.compare(expectedRange, actualRange) < 0) {
			TestUtil.log("====================================================================================");
			TestUtil.log("Expected Range is greater than Actual range by " + actualRange);
			return true;
		}
		else if(Double.compare(expectedRange, actualRange) == 0) {
			TestUtil.log("====================================================================================");
			TestUtil.log("Expected range is equal to actual range and it is " + expectedRange);
			return true;
		}
		else {
			TestUtil.log("====================================================================================");
			TestUtil.log("Actual Range is more than expected range");
			return false;
		}
	}

public static boolean comparingWebAndAPIInHumidity() throws IOException {
	
	Properties prop = TestUtil.readFromPropertyFile();
	String magnitude_of_variation = prop.getProperty("magnitude_of_variation_humidity");
	double expectedRange = Double.parseDouble(magnitude_of_variation);
	double actualRange = Math.abs(Double.parseDouble(WeatherPage.string_humidity) - (Converter.Kelvin_to_Fahrenheit(GetAPI.api_humidity)));
	
	TestUtil.log("====================================================================================");
	TestUtil.log("====================================Humidity========================================");
	TestUtil.log("====================================================================================");
	if(Double.compare(expectedRange, actualRange) < 0) {
		TestUtil.log("====================================================================================");
		TestUtil.log("Expected Range is greater than Actual range by " + actualRange);
		return true;
	}
	else if(Double.compare(expectedRange, actualRange) == 0) {
		TestUtil.log("====================================================================================");
		TestUtil.log("Expected range is equal to actual range and it is " + expectedRange);
		return true;
	}
	else {
		TestUtil.log("====================================================================================");
		TestUtil.log("Actual Range is more than expected range");
		return false;
	}
	
}
} 