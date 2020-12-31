package com.ndtv.getAPI;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.ndtv.testUtils.TestUtil;

public class GetAPI {
	
	public static Double api_temp;
	public static Long api_humidity;
	
	public static void GetApi(String jsonString) throws ParseException {
		    
	        JSONParser jsonParser = new JSONParser();
	        Object obj = jsonParser.parse(jsonString);
	        JSONObject jsonObject = (JSONObject) obj;
	        JSONObject mainObject = (JSONObject)jsonObject.get("main");
	        
	        api_temp = (Double) mainObject.get("temp");    
	        api_humidity = (Long) mainObject.get("humidity");  
	       
	        TestUtil.log("====================================================================================");
			TestUtil.log("==============================API WEATHER DETAIL ON MAP=============================");
			TestUtil.log("====================================================================================");
			TestUtil.log("API Weather Detail : " + api_temp + " " + api_humidity);
	}
	    
}