package com.ndtv.comparator;

public class Converter {
	
	public static double FahreheitToCelsius(Double fahrenheit){
	    Double celsius = (fahrenheit-32)*(0.5556);
	    return celsius;
	}

	public static double KelvinToCelsius(Double Kelvin) {
		Double celsius = Kelvin - 273.15;
		return celsius;
	}

	public static double CelsiusToKelvin(Double Celsius) {
		Double kelvin = Celsius + 273.15;
		return kelvin;
	}
	
	public static double Fahrenheit_to_Kelvin( double F ) { 
	    return 273.5f + ((F - 32.0f) * (5.0f/9.0f)); 
	} 

	public static double Kelvin_to_Fahrenheit( double value ) {
		double farenheit = value * 1.8 - 459.67;
		return farenheit;
	} 

	  

}
